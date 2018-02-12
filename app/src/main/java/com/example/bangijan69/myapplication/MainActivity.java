package com.example.bangijan69.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
 EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //retrofit tambah
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);


    }


    public void Login(View view) {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        requestLogin();
    }

    public void Registrasi(View view) {
       startActivity(new Intent(mContext, Registrasi.class));


    }

        private void requestLogin(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
            mApiService.loginRequest(email, password).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("respon ", "ini if elsetes  isSuceesful ini ya");
                            if (response.isSuccessful()){
                                loading.dismiss();
                                Log.d("Loading Dimmis ya  ", "cek ini coba :)");
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    //Log.d("Ini respon ",response.body().string());
                                   when_respon(jsonRESULTS);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                            loading.dismiss();
                        }
                    });
        }

private void when_respon( JSONObject joss) throws JSONException {

    Log.d("otw running ", "ini if elsetes");
    if (joss.getString("error").equals("false")){
        Log.d("login", "login berhasil");
        // Jika login berhasil maka data nama yang ada di response API
        // akan diparsing ke activity selanjutnya.
        Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
        String nama = joss.getJSONObject("user").getString("nama");
        String email = joss.getJSONObject("user").getString("email");
        String password = joss.getJSONObject("user").getString("encrypted_password");
        Intent intent = new Intent(mContext, berandaActivity.class);
        intent.putExtra("result_email",email);
        intent.putExtra("result_pass",password);
        intent.putExtra("result_nama", nama);
        startActivity(intent);
    } else {
        // Jika login gagal
        String error_message = joss.getString("error_msg");
        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
    }
}

}




