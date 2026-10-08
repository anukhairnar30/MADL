package com.triptales.app;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseAuthManager {
    public interface Callback { void success(String name,String email); void failure(String message); }
    private final Context context;
    public FirebaseAuthManager(Context c){context=c.getApplicationContext();}

    private boolean available(){
        try { FirebaseApp.initializeApp(context); return FirebaseApp.getApps(context).size()>0; }
        catch(Exception e){ return false; }
    }

    public void signIn(String email,String password,Callback cb){
        if(!available()){ cb.failure("Firebase is not configured. Add google-services.json and enable Email/Password Authentication."); return; }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(t->{
            if(t.isSuccessful()) {
                String name = t.getResult().getUser()!=null && t.getResult().getUser().getDisplayName()!=null
                        ? t.getResult().getUser().getDisplayName() : "Traveler";
                cb.success(name,email);
            } else cb.failure(t.getException()!=null?t.getException().getMessage():"Login failed");
        });
    }

    public void signUp(String name,String email,String password,Callback cb){
        if(!available()){ cb.failure("Firebase is not configured. Add google-services.json and enable Email/Password Authentication."); return; }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(t->{
            if(t.isSuccessful()) {
                if(t.getResult().getUser()!=null){
                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                    t.getResult().getUser().updateProfile(request).addOnCompleteListener(x -> cb.success(name,email));
                } else cb.success(name,email);
            } else cb.failure(t.getException()!=null?t.getException().getMessage():"Signup failed");
        });
    }
}
