package com.example.bangijan69.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Edit_Profil_Activity extends AppCompatActivity {
String ResultNama , ResultEmail, ResultPass;
EditText nama_editprof, email_editprof,pass_editprof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profil_);

        nama_editprof =(EditText)findViewById(R.id.nama_editprofil);
        email_editprof =(EditText)findViewById(R.id.email_editProf);
        pass_editprof =(EditText)findViewById(R.id.pass_editProf);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            //resultNama = extras.getString("result_nama");
            setResultNama(extras.getString("result_nama"));
        setResultEmail(extras.getString("result_email"));
        setResultPass(extras.getString("result_pass"));

        nama_editprof.setText(getResultNama());
        email_editprof.setText(getResultEmail());
        pass_editprof.setText(getResultPass());
    }

    public void setResultEmail(String resultEmail) {
        ResultEmail = resultEmail;
    }

    public void setResultPass(String resultPass) {
        ResultPass = resultPass;
    }

    public String getResultEmail() {
        return ResultEmail;
    }

    public String getResultPass() {
        return ResultPass;
    }

    public void setResultNama(String resultNama) {
        ResultNama = resultNama;
    }

    public String getResultNama() {
        return ResultNama;
    }

    public void get_data_user(){

}
}
