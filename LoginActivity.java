package com.triptales.app;
import android.app.*;import android.content.*;import android.os.*;import android.widget.*;
public class LoginActivity extends Activity{
 EditText email,password; FirebaseAuthManager auth; SessionManager session;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_login);email=findViewById(R.id.emailEdit);password=findViewById(R.id.passwordEdit);auth=new FirebaseAuthManager(this);session=new SessionManager(this);findViewById(R.id.loginButton).setOnClickListener(v->login());findViewById(R.id.signupLink).setOnClickListener(v->startActivity(new Intent(this,SignupActivity.class)));}
 void login(){String e=email.getText().toString().trim(),p=password.getText().toString();if(e.isEmpty()||p.isEmpty()){toast("Enter email and password");return;}auth.signIn(e,p,new FirebaseAuthManager.Callback(){public void success(String n,String em){session.login(em,n);openMain();}public void failure(String m){toast(m);}});}
 void openMain(){startActivity(new Intent(this,MainActivity.class));finish();}void toast(String s){Toast.makeText(this,s,Toast.LENGTH_LONG).show();}
}
