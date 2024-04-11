package com.example.tp4;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CALL_LOG;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp4.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private LlamadaReceiver lr;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        ActivityLoginViewModel vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ActivityLoginViewModel.class);
        setContentView(binding.getRoot());
        vm.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!aBoolean){
                    Toast.makeText(LoginActivity.this,"Usuario o contraseña incorrecta",Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.autenticar(binding.etUsuario.getText().toString(),binding.etClave.getText().toString());
            }
        });
        solicitarPermisos();
        registrarReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.etClave.setText("");
        binding.etUsuario.setText("");
    }

    private void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},2000);
        }

    }
    private void registrarReceiver(){
        lr = new LlamadaReceiver();
        registerReceiver(lr,new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(lr);
    }
}