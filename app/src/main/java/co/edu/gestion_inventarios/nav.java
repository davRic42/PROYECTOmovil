package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class nav extends AppCompatActivity {
    private ImageButton btnBackMenu;
    private Button btnDeleteCategory;
    private Button btnEditProfile;
    private ImageButton btnConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        begin();
        this.btnBackMenu.setOnClickListener(this::backMenu);
        this.btnConfig.setOnClickListener(this::irConfig);
        this.btnDeleteCategory.setOnClickListener(this::irDeleteC);
        this.btnEditProfile.setOnClickListener(this::irEditP);

    }

    private void irEditP(View view) {
        Intent irCategory= new Intent(getApplicationContext(),activity_editarPerfil.class);
        startActivity(irCategory);
    }

    private void irDeleteC(View view) {

    }


    private void irConfig(View view) {
        Intent irCategory= new Intent(getApplicationContext(),configuracion.class);
        startActivity(irCategory);
    }

    private void backMenu(View view) {
        Intent irCategory= new Intent(getApplicationContext(),menu.class);
        startActivity(irCategory);
    }

    private void begin(){
        this.btnBackMenu=findViewById(R.id.btnBackCreate);
        this.btnEditProfile=findViewById(R.id.btnEditProfile);
        this.btnConfig=findViewById(R.id.btnConfig);
        this.btnDeleteCategory=findViewById(R.id.btnDeleteCategory);
    }
}