package co.edu.gestion_inventarios;
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


public class RegistroCuenta extends AppCompatActivity {
    private Button btnRegistrarUsuario;
    private EditText etRegistroCorreo;
    private EditText etRegistroContrasena;
    private EditText etRegistroNombre;
    private EditText etRegistroConfirmar;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        begin();
        this.btnRegistrarUsuario.setOnClickListener(this::processRegistration);
    }
    private void goTo() {
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
    private void processRegistration(View view) {
        String email = etRegistroCorreo.getText().toString();
        String password = etRegistroContrasena.getText().toString();
        String name = etRegistroNombre.getText().toString();

        if (!validEmail(email) || password.length() <= 3 || validName() != true) {
            alertView("Los datos no son validos, intente de nuevo");
        } else {
            Loger loger = new Loger();
            loger.setUser_mail(email);
            loger.setUser_pss(md5(password));
            loger.setUser_name(name);
            retrofit = ClienteRetrofit.getCliente(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);

            Call<ResponseCredentials> call = serviceLogin.createUser(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    Log.i("resultado", response.toString());
                    if (response.isSuccessful()) {
                        ResponseCredentials body = response.body();
                        Log.i("body", body.toString());
                        if (validPassword()){
                            Toast.makeText(RegistroCuenta.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            goTo();
                        }else{
                            Toast.makeText(RegistroCuenta.this, "Algo fallo, intente de nuevo", Toast.LENGTH_SHORT).show();
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
    public boolean validEmail(String data){
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$");
        Matcher mather = pattern.matcher(data);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado no es válido.");
        }
        return false;
    }
    public boolean validName(){
        boolean estado;
        if (etRegistroNombre.getText().toString().matches("^[A-Za-z1-9]{2,10}$")){
            estado = true;
        }else{
            estado = false;
        }
        return estado;
    }
    public boolean validPassword(){
        boolean estado;
        String pass;
        String confirm;
        pass = etRegistroContrasena.getText().toString();
        confirm = etRegistroConfirmar.getText().toString();
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
    private void begin(){
        this.etRegistroNombre = findViewById(R.id.etRegistroNombre);
        this.etRegistroCorreo = findViewById(R.id.etRegistroCorreo);
        this.etRegistroContrasena = findViewById(R.id.etRegistroContrasena);
        this.etRegistroConfirmar = findViewById(R.id.etRegistroConfirmar);
        this.btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
    }
}