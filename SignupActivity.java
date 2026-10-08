package com.triptales.app;
import android.app.*;import android.content.*;import android.os.*;import android.widget.*;
public class SignupActivity extends Activity{
 EditText name,email,password; FirebaseAuthManager auth;SessionManager session;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_signup);name=findViewById(R.id.nameEdit);email=findViewById(R.id.emailEdit);password=findViewById(R.id.passwordEdit);auth=new FirebaseAuthManager(this);session=new SessionManager(this);findViewById(R.id.signupButton).setOnClickListener(v->signup());}
 void signup(){String n=name.getText().toString().trim(),e=email.getText().toString().trim(),p=password.getText().toString();if(n.isEmpty()||e.isEmpty()||p.length()<6){Toast.makeText(this,"Enter all fields; password must be 6+ characters",Toast.LENGTH_LONG).show();return;}auth.signUp(n,e,p,new FirebaseAuthManager.Callback(){public void success(String nn,String ee){session.login(ee,nn);startActivity(new Intent(SignupActivity.this,MainActivity.class));finish();}public void failure(String m){Toast.makeText(SignupActivity.this,m,Toast.LENGTH_LONG).show();}});}
}
