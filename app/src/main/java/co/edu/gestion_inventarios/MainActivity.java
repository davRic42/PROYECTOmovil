package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnIngresar;
    private Button btnRegistrar;
    private Button btnPruebas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        this.btnRegistrar.setOnClickListener(this::irRegistrar);
        this.btnIngresar.setOnClickListener(this::irIngreso);
        this.btnPruebas.setOnClickListener(this::pruebas);
    }

    private void pruebas(View view) {
        Intent irMenu = new Intent(getApplicationContext(), menu.class);
        startActivity(irMenu);
    }

    private void irIngreso(View view) {
        Intent irIngreso = new Intent(getApplicationContext(), IngresoCuenta.class);
        startActivity(irIngreso);
    }

    private void irRegistrar(View view) {
        Intent irRegistrar = new Intent(getApplicationContext(), RegistroCuenta.class);
        startActivity(irRegistrar);
    }

    private void begin(){
        this.btnIngresar = findViewById(R.id.btnIngresar);
        this.btnRegistrar = findViewById(R.id.btnRegistrar);

        this.btnPruebas=findViewById(R.id.btnPruebas);
    }
}