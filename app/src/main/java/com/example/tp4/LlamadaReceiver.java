package com.example.tp4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class LlamadaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean conectado = intent.getBooleanExtra("connected",false);
        if(conectado){
            Intent intentLlamada = new Intent(Intent.ACTION_CALL);
            intentLlamada.setData(Uri.parse("tel:" + "2664553747"));
            intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentLlamada);
        } else {
            Toast.makeText(context,"Wifi Desconectado",Toast.LENGTH_LONG).show();
        }

    }
}