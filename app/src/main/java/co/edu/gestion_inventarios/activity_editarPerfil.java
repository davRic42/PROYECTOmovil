package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import static co.edu.gestion_inventarios.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.gestion_inventarios.api.ServiceLogin;
import co.edu.gestion_inventarios.model.Loger;
import co.edu.gestion_inventarios.model.ResponseCredentials;
import co.edu.gestion_inventarios.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class activity_editarPerfil extends AppCompatActivity {
    private Button btnAtrasPerfil;
    private Button btnAplicarCambios;
    private EditText etNombreusuario;
    private EditText etContrasena;
    private EditText etConfirmarContrasena;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        Begin();
        this.btnAplicarCambios.setOnClickListener(this::updateProcess);
    }
    private void updateProcess(View view){
        String editarNombre = etNombreusuario.getText().toString();
        String editarContrasena = etContrasena.getText().toString();
        if (editarContrasena.length() <= 3 || validName() != true) {
            alertView("Los datos no son validos, intente de nuevo");
        } else {
            Loger loger = new Loger();
            loger.setUser_pss(md5(editarContrasena));
            loger.setUser_name(editarNombre);
            retrofit = ClienteRetrofit.getCliente(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);

            Call<ResponseCredentials> call = serviceLogin.updateUser(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    Log.i("resultado", response.toString());
                    if (response.isSuccessful()) {
                        ResponseCredentials body = response.body();
                        Log.i("body", body.toString());
                        if(validPassword()){
                            Toast.makeText(activity_editarPerfil.this, "El usuario se ha actualizado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity_editarPerfil.this, "Algo fallo en la actualización", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        alertView("Algo fallo en el proceso :(");
                    }
                }
                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    alertView("Error en el registro. Por favor intenta de nuevo.");
                }
            });
        }
    }
    public boolean validPassword(){
        boolean estado;
        String pass;
        String confirm;
        pass = etContrasena.getText().toString();
        confirm = etConfirmarContrasena.getText().toString();
        Log.i("pass", pass);
        Log.i("confirm", confirm);
        if(pass.matches(confirm)){
            estado = true;
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            estado = false;
        }
        return estado;
    }
    public boolean validName(){
        boolean estado;
        if (etNombreusuario.getText().toString().matches("^[A-Za-z1-9]{2,10}$")){
            estado = true;
        }else{
            estado = false;
        }
        return estado;
    }
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    private void alertView(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cuenta invalida");
        builder.setMessage(mensaje);
        builder.setPositiveButton("CERRAR", null);
        builder.create();
        builder.show();
    }
    @SuppressLint("WrongViewCast")
    private void Begin() {
        this.btnAtrasPerfil = findViewById(R.id.ibtnAtrasPerfil);
        this.etNombreusuario = findViewById(R.id.etNombreusuario);
        this.btnAplicarCambios = findViewById(R.id.btnAplicarCambios);
        this.etContrasena = findViewById(R.id.etRegistroContrasena);
        this.etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena);
    }
}