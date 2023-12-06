package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {
    private LinearLayout navView;;
    private Button btnAddCategory;
    private TextView tvNameUser;
    private ImageButton btnMenu;
    private RelativeLayout containerCategory;
    List<list_element> elements;
    private RecyclerView rvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        begin();

        this.btnAddCategory.setOnClickListener(this::goCrear);
        this.btnMenu.setOnClickListener(this::goMenu);
    }

    private void goCrear(View view) {
        Intent irCategory= new Intent(getApplicationContext(),activity_CrearCategoria.class);
        startActivity(irCategory);
    }

    private void goMenu(View view) {
        Intent irNav= new Intent(getApplicationContext(), nav.class);
        startActivity(irNav);
    }

    private void begin(){
        this.btnAddCategory = findViewById(R.id.btnAddCategory);
        this.tvNameUser = findViewById(R.id.tvNameUser);
        this.containerCategory = findViewById(R.id.containerCategory);
        this.btnMenu = findViewById(R.id.btnMenu);
        this.elements=new ArrayList<>();
        this.elements.add(new list_element("#51776C","herramientas","numero #1"));
        this.rvMenu=findViewById(R.id.rvMenu);

        ListAdapter listAdapter=new ListAdapter(elements, this, new ListAdapter.OnItemCLickListener() {
            @Override
            public void OnItemClick(list_element item) {
                moveTDescription(item);
            }
        });
        this.rvMenu.setLayoutManager(new LinearLayoutManager(this));
        this.rvMenu.setAdapter(listAdapter);
    }

    public void moveTDescription(list_element item) {
        Intent irNav= new Intent(getApplicationContext(), category.class);
        irNav.putExtra("listItem",item);
        startActivity(irNav);
    }
}
