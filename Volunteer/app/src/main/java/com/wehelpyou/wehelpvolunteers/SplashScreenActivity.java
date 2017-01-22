package com.wehelpyou.wehelpvolunteers;

/**
 * Created by Sneh Pahilwani on 1/21/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
