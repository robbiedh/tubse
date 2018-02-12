package com.example.bangijan69.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class berandaActivity extends AppCompatActivity {
    TextView tvResultNama;
    String resultNama , resultEmail, resultPass;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        mContext= this;
        tvResultNama = (TextView) findViewById(R.id.tvResultNama);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            //resultNama = extras.getString("result_nama");
        setResultNama(extras.getString("result_nama"));
        setResultEmail(extras.getString("result_email"));
        setResultPass(extras.getString("result_pass"));
        tvResultNama.setText(getResultNama());
    }

    public void setResultEmail(String resultEmail) {
        this.resultEmail = resultEmail;
    }

    public void setResultPass(String resultPass) {
        this.resultPass = resultPass;
    }

    public String getResultPass() {
        return resultPass;
    }

    public String getResultEmail() {
        return resultEmail;
    }

    public void setResultNama(String resultNama) {
        this.resultNama = resultNama;
    }

    public String getResultNama() {
        return resultNama;
    }

    public void btn_edit_profil(View view) {
        Intent intent = new Intent(mContext, Edit_Profil_Activity.class);
        intent.putExtra("result_nama", getResultNama());
        intent.putExtra("result_pass",getResultPass());
        intent.putExtra("result_email",getResultEmail());
        startActivity(intent);

    }
}
