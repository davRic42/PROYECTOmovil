package co.edu.gestion_inventarios;

import static co.edu.gestion_inventarios.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import co.edu.gestion_inventarios.api.ServiceCategory;
import co.edu.gestion_inventarios.api.ServiceLogin;
import co.edu.gestion_inventarios.model.Categoria;
import co.edu.gestion_inventarios.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);
        begin();
        this.btnBackMenu.setOnClickListener(this::backCreate);
        this.btnCrear.setOnClickListener(this::createCt);
    }

    private void backCreate(View view) {
        Intent irMenu= new Intent(getApplicationContext(),menu.class);
        startActivity(irMenu);

    }

    private void createCt(View view){
        String nameCategory = etNameCategory.getText().toString();

        if(nameCategory != null && !nameCategory.isEmpty()){
            Categoria categoria = new Categoria();
            categoria.setName_category(nameCategory);

            retrofit = ClienteRetrofit.getCliente(BASE_URL);
            ServiceCategory serviceCategory = retrofit.create(ServiceCategory.class);

            Call<Categoria> call = serviceCategory.createCategory(categoria);
            call.enqueue(new Callback<Categoria>() {
                @Override
                public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                    Toast.makeText(getApplicationContext(), "la categoria "+nameCategory+" ha sido creada con exito", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), menu.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Categoria> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "por favor llene el espacio correspondiente", Toast.LENGTH_SHORT).show();
        }

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