package com.example.bangijan69.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrasi extends AppCompatActivity {
    EditText etNama;
    EditText etEmail;
    EditText etPassword;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        etNama = (EditText) findViewById(R.id.etNama_registrasi);
        etEmail = (EditText) findViewById(R.id.etEmail_registrasi);
        etPassword = (EditText) findViewById(R.id.etPassword_registrasi);

        mContext=this;

        mApiService = UtilsApi.getAPIService();

        btnRegister =(Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               requestRegister();
                                           }
                                       }


        );


    }



    public void  requestRegister(){

String nama = etNama.getText().toString();
String email =etEmail.getText().toString();
          String pass =etPassword.getText().toString();


         Log.d("pesan  inin ", nama+email+pass);

         mApiService.registerRequest(nama,email,pass).enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                 if (response.isSuccessful()){
                     Log.i("debug", "onResponse: BERHASIL");
                     try {
                         JSONObject jsonRESULTS = new JSONObject(response.body().string());
                         if (jsonRESULTS.getString("error").equals("false")){
                             Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();

                             startActivity(new Intent(mContext, MainActivity.class));
                         } else {
                             String error_message = jsonRESULTS.getString("error_msg");
                             Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 } else {
                     Log.i("debug", "onResponse: GA BERHASIL");
                     loading.dismiss();
                 }


             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                 Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
             }
         });






        



































        }








}
