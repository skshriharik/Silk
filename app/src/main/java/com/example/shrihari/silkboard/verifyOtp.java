package com.example.shrihari.silkboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class verifyOtp extends AppCompatActivity {
    protected EditText user_otp  ;
    private String user_otp_string ;
    private String  server_otp ,ph="" ;
    private static String URL_OTP = "http://172.17.1.145/back/submitphone1.php";
    private Button btn_submit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        getValues();
        onSubmitClick();


      //  checkOTP();
//        Log.d("chk","ph_no:\n"+ph);
//        Log.d("chk","Intent 2 otp "+ server_otp);

    }

    private void nextIntent() {
        Intent intent = new Intent(verifyOtp.this , PersonalDetails.class);
        intent.putExtra("phone",ph);
        startActivity(intent);
    }

    private void onSubmitClick() {

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserOtp();
                checkOTP();
            }
        });

    }

    private void getUserOtp() {
        user_otp_string = user_otp.getText().toString().trim();
        Log.d("chk","user OTP "+user_otp_string);

    }

    private void getValues() {
        Bundle extras = getIntent().getExtras();
        ph = extras.getString("phoneNumber");
        server_otp = extras.getString("serverotp");
        btn_submit = findViewById(R.id.otpSubmit);
        user_otp = findViewById(R.id.user_otp_x);


    }

    private void  checkOTP(){

        if (user_otp_string.equals(server_otp)) {
            Log.d("chk", "Correct OTP "+user_otp_string);
            Toast.makeText(verifyOtp.this, "Correct OTP "+user_otp_string, Toast.LENGTH_SHORT).show();
            nextIntent();

        }
        else {
            Log.d("chk","Wrong OTP "+user_otp_string);
            Toast.makeText(verifyOtp.this , "Wrong OTP "+user_otp_string,Toast.LENGTH_SHORT).show();
        }
    }

}
