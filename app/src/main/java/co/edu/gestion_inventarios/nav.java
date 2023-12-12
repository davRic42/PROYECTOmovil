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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        begin();
        this.btnBackMenu.setOnClickListener(this::backMenu);

        this.btnDeleteCategory.setOnClickListener(this::irDeleteC);

    }

    private void irEditP(View view) {
        Intent irCategory= new Intent(getApplicationContext(),activity_editarPerfil.class);
        startActivity(irCategory);
    }

    private void irDeleteC(View view) {

    }




    private void backMenu(View view) {
        Intent irCategory= new Intent(getApplicationContext(),menu.class);
        startActivity(irCategory);
    }

    private void begin(){
        this.btnBackMenu=findViewById(R.id.btnBackCreate);
        this.btnDeleteCategory=findViewById(R.id.btnDeleteCategory);
    }
}