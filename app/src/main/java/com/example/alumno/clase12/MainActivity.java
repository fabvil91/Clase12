package com.example.alumno.clase12;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback, View.OnClickListener{
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.btnSend).setOnClickListener(this);
        h = new Handler(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d("m", msg.obj.toString());
        ((TextView)this.findViewById(R.id.txtVista)).append(msg.obj.toString() + "\n");
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSend){
            String texto = ((EditText)this.findViewById(R.id.txtMsg)).getText().toString();

            Hilo h = new Hilo(this.h);
            h.start();
            h.enviarMsg(texto);
        }
    }
}
