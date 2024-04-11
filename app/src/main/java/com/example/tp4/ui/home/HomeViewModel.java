package com.example.tp4.ui.home;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> error;
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getError(){
        if(error==null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void llamar(String telefono){
        if(!telefono.equals("")){
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+telefono));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().getApplicationContext().startActivity(intent);
        } else {
            error.setValue(false);
        }
    }

}