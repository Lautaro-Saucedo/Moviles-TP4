package com.example.tp4;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ActivityLoginViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> error;
    public ActivityLoginViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Boolean> getError(){
        if(error==null){
            error=new MutableLiveData<>();
        }
        return error;
    }
    public void autenticar(String usuario,String clave){
        if(!usuario.equals("admin") || !clave.equals("admin")){
            error.setValue(false);
        } else {
            Context context = getApplication().getApplicationContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
