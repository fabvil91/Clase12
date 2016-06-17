package com.example.alumno.clase12;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


/**
 * Created by alumno on 16/06/2016.
 */
public class Hilo extends Thread{
    BufferedWriter bw = null;
    Handler h;

    public Hilo(Handler h){
        this.h = h;
    }

    @Override
    public void run() {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("192.168.2.67",4097);

            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            while(true) {
                String msgStr = br.readLine();

                if(msgStr==null)
                    break;
                Log.d("t", "Se recibio:" + msgStr);

                Message m = new Message();
                m.obj = msgStr;
                h.sendMessage(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMsg(String msg)
    {
        try {
            if(bw!=null) {
                bw.write(msg + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
