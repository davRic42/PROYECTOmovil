package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class activity_CrearCategoria extends AppCompatActivity {
    private ImageButton btnCaja;
    private ImageButton btnLlave;
    private ImageButton btnCarton;
    private ImageButton btnMesita;
    private ImageButton btnTren;
    private ImageButton btnCorte;
    private Button btnCrear;
    private ImageButton btnBackMenu;
    private EditText etNameCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);
        begin();
        this.btnBackMenu.setOnClickListener(this::backCreate);

    }

    private void backCreate(View view) {
        Intent irMenu= new Intent(getApplicationContext(),menu.class);
        startActivity(irMenu);
    }

    private void begin(){
        this.btnCaja=findViewById(R.id.btnCaja);
        this.btnLlave=findViewById(R.id.btnLlave);
        this.btnCarton=findViewById(R.id.btnCarton);
        this.btnMesita=findViewById(R.id.btnMesita);
        this.btnTren=findViewById(R.id.btnTren);
        this.btnCorte=findViewById(R.id.btnCorte);
        this.btnCrear=findViewById(R.id.btnCrear);
        this.etNameCategory=findViewById(R.id.etNombreCategoria);
        this.btnBackMenu=findViewById(R.id.btnBackCreate);
    }
}