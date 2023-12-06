package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class configuracion extends AppCompatActivity {
    private ImageButton btnBackConfig;
    private Button btnEliminarCategoria;
    private Button btnBorraDatos;
    private Button btnCrearNuevaCuenta;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        begin();
        this.btnCerrarSesion.setOnClickListener(this::irMain);
        this.btnCrearNuevaCuenta.setOnClickListener(this::irRegistro);
        this.btnBackConfig.setOnClickListener(this::irNav);
    }

    private void irNav(View view) {
        Intent irCategory= new Intent(getApplicationContext(),menu.class);
        startActivity(irCategory);
    }

    private void irRegistro(View view) {
        Intent irCategory= new Intent(getApplicationContext(),RegistroCuenta.class);
        startActivity(irCategory);
    }

    private void irMain(View view) {
        Intent ir= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ir);
    }

    private void begin(){
        this.btnBackConfig=findViewById(R.id.btnBackConfig);
        this.btnEliminarCategoria=findViewById(R.id.btnEliminarCategoria);
        this.btnBorraDatos=findViewById(R.id.btnBorraDatos);
        this.btnCrearNuevaCuenta=findViewById(R.id.btnCrearNuevaCuenta);
        this.btnCerrarSesion=findViewById(R.id.btnCerrarSesion);
    }
}