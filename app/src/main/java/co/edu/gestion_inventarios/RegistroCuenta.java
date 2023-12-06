package co.edu.gestion_inventarios;
import android.os.Bundle;
import static co.edu.gestion_inventarios.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
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
    private EditText etRegistroContraseña;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        begin();
        this.btnRegistrarUsuario.setOnClickListener(this::processRegistration);
    }
    private void goTo(View view) {
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
    private void processRegistration(View view) {
        String email = etRegistroCorreo.getText().toString();
        String password = etRegistroContraseña.getText().toString();

        if (!validEmail(email) || password.length() <= 3) {
            alertView("Error en credenciales");
        } else {
            Loger loger = new Loger();
            loger.setUser_mail(email);
            loger.setUser_pss(md5(password));
            retrofit = ClienteRetrofit.getCliente(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);

            Call<ResponseCredentials> call = serviceLogin.createUser(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegistroCuenta.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        alertView("Registration failed. Response Code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    // Handle failure (e.g., network error)
                    alertView("Error in registration. Please try again.");
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
            System.out.println("El email ingresado es inválido.");
        }
        return false;
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
        this.etRegistroCorreo = findViewById(R.id.etRegistroCorreo);
        this.etRegistroContraseña = findViewById(R.id.etRegistroContraseña);
        this.btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
    }
}