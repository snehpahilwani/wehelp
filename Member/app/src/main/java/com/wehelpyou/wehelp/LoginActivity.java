package com.wehelpyou.wehelp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Sneh Pahilwani on 1/21/2017.
 */

public class LoginActivity extends AppCompatActivity{
    public SharedPreferences login_context;
    public SharedPreferences.Editor editor;
    Intent intent;
    EditText ed1;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = (EditText)findViewById(R.id.EditTextEmail);
        Button btn = (Button)findViewById(R.id.btn_submit);
        //Intent intent;
        login_context = getSharedPreferences("login_member", Context.MODE_PRIVATE);
        String text = login_context.getString("login_member",null);
        if(text!=null){
            intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else{
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    editor = login_context.edit();
                    editor.putString("login_member",ed1.getText().toString());
                    editor.commit();
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            });

        }



    }




}
