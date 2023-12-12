package co.edu.gestion_inventarios;

import android.os.Bundle;
import static co.edu.gestion_inventarios.api.ValuesApi.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.gestion_inventarios.api.ServiceLogin;
import co.edu.gestion_inventarios.model.Credentials;
import co.edu.gestion_inventarios.model.Loger;
import co.edu.gestion_inventarios.model.ResponseCredentials;
import co.edu.gestion_inventarios.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import androidx.appcompat.app.AppCompatActivity;

public class IngresoCuenta extends AppCompatActivity {
    private Retrofit retrofit;
    private EditText etIngresoUsuario;
    private EditText etIngresoContrasena;
    private Button btnIniciarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        begin();
        btnIniciarSesion.setOnClickListener(this::processLogin);
    }
    private void processLogin(View view) {
        String email = etIngresoUsuario.getText().toString();
        int pass = etIngresoContrasena.getText().length();
        if(!validEmail(email) && pass <= 3){
            alertView("Error en credenciales");
        }else{
            String password = md5(etIngresoContrasena.getText().toString());
            Loger loger = new Loger();
            loger.setUser_mail(etIngresoUsuario.getText().toString());
            loger.setUser_pss(password);

            retrofit = ClienteRetrofit.getCliente(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<ResponseCredentials> call = serviceLogin.accessLogin(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    //alertView("en el response"+response.toString());
                    if(response.isSuccessful()){
                        ResponseCredentials body = response.body();
                        String mensaje  = body.getMensaje();
                        Toast.makeText(IngresoCuenta.this, ""+mensaje, Toast.LENGTH_SHORT).show();
                        ArrayList<Credentials> list = body.getCredenciales();
                        if (mensaje.equals("OK") && !isNullOrEmpty(list)){
                            for(Credentials c:list){
                                SharedPreferences shared = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("correo", etIngresoUsuario.getText().toString());
                                editor.putString("key", c.getUser_key());
                                editor.putString("identificator", c.getUser_identifier());
                                editor.putString("id", c.getUser_id());
                                editor.commit();
                                goTo();
                            }
                        }else{
                            alertView("Usuario no existe o Contraseña invalida");
                        }


                    }else{
                        alertView("Usuario no existe, intente de nuevo, Respuesta: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Log.i("response", t.getMessage());
                    alertView("Error en servicio, comuniquese con el desarrollador");
                }
            });
        }
    }
    private void goTo(){
        try{
            Intent intent = new Intent(this, menu.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    private void alertView(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cuenta invalida");
        builder.setMessage(mensaje);
        builder.setPositiveButton("CERRAR", null);
        builder.create();
        builder.show();
    }
    public static boolean isNullOrEmpty(Object obj){
        if(obj==null)return true;
        if(obj instanceof String) return ((String)obj).trim().equals("") || ((String)obj).equalsIgnoreCase("NULL");
        if(obj instanceof Integer) return ((Integer)obj)==0;
        if(obj instanceof Long) return ((Long)obj).equals(new Long(0));
        if(obj instanceof Double) return ((Double)obj).equals(0.0);
        if(obj instanceof Collection) return (((Collection)obj).isEmpty());
        return false;
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
    private void begin(){
        this.etIngresoUsuario= findViewById(R.id.etIngresoUsuario);
        this.etIngresoContrasena = findViewById(R.id.etIngresoContrasena);
        this.btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
    }
}