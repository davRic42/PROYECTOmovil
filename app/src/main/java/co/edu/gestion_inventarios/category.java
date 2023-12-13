package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class category extends AppCompatActivity {

    private TextView tvCategory;
    private ImageButton btnBackCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        begin();
        this.btnBackCategory.setOnClickListener(this::irMenu);
    }

    private void irMenu(View view) {
        Intent irCategory= new Intent(getApplicationContext(),menu.class);
        startActivity(irCategory);
    }

    private void begin(){
        this.btnBackCategory=findViewById(R.id.btnBackCategory);
        this.tvCategory=findViewById(R.id.tvCategory);
    }
}