package com.example.tp4.ui.slideshow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class Dialogo {
    public static void mostrardialogo(FragmentActivity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Desea salir?");
        builder.setMessage("este es el boton para salir");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }
}
