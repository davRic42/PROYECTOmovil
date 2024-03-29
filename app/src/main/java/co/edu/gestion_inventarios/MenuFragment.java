package co.edu.gestion_inventarios;

import static co.edu.gestion_inventarios.api.ValuesApi.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.edu.gestion_inventarios.adapter.CategoryAdapter;
import co.edu.gestion_inventarios.api.ServiceCategory;
import co.edu.gestion_inventarios.api.ServiceLogin;
import co.edu.gestion_inventarios.model.Categoria;
import co.edu.gestion_inventarios.model.Credentials;
import co.edu.gestion_inventarios.model.Loger;
import co.edu.gestion_inventarios.model.ResponseCredentials;
import co.edu.gestion_inventarios.model.ResultsC;
import co.edu.gestion_inventarios.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private LinearLayout navView;
    private Button btnAddCategory;
    private TextView tvNameUser;
    private RelativeLayout containerCategory;
    private ArrayList<Categoria> elements;
    private RecyclerView rvMenu;
    private CategoryAdapter categoryAdapter;
    private Retrofit retrofit;


    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tvNameUser = view.findViewById(R.id.tvNameUser);
        btnAddCategory = view.findViewById(R.id.btnAddCategory);
        checkName();
        btnAddCategory.setOnClickListener(this::onClick);

        rvMenu = view.findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        getProductData();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddCategory) {
            Toast.makeText(getActivity(), "Clic en el botón Add Category", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), activity_CrearCategoria.class);
            startActivity(intent);
        }
    }


    private void checkName() {
        Context context = getContext();
        SharedPreferences loginPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        String loginId = loginPreferences.getString("loginId", "");
        retrofit = ClienteRetrofit.getCliente(BASE_URL);
        ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
        Call<List<ResponseCredentials>> call = serviceLogin.getUsers();
        call.enqueue(new Callback<List<ResponseCredentials>>() {
            @Override
            public void onResponse(Call<List<ResponseCredentials>> call, Response<List<ResponseCredentials>> response) {
                Log.i("respuesta", "respuesta");
                // Aquí puedes manejar la lista de ResponseCredentials
                List<ResponseCredentials> credentialsList = response.body();
                if (credentialsList != null && !credentialsList.isEmpty()) {
                    StringBuilder names = new StringBuilder();
                    for (ResponseCredentials credentials : credentialsList){
                        if (loginId.equals(credentials.getUser_id())){
                            names.append(credentials.getUser_name());
                            tvNameUser.setText(names.toString());
                            break;
                        }
                    }
                    Log.i("respuesta2", credentialsList.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseCredentials>> call, Throwable t) {
                Log.i("Error", "error");
                Log.i("T-Pose", t.getMessage().toString());
                Log.i("error call", call.toString());
            }
        });
    }
    private void getProductData() {
        //Toast.makeText(getContext(),"Error:getProruduct ",Toast.LENGTH_LONG).show();
        Retrofit retrofit = ClienteRetrofit.getCliente(BASE_URL);
        ServiceCategory serviceCategory = retrofit.create(ServiceCategory.class);

        Call<ArrayList<Categoria>> call = serviceCategory.getCategoria();
        call.enqueue(new Callback<ArrayList<Categoria>>() {
            public void onResponse(Call<ArrayList<Categoria>> call, Response<ArrayList<Categoria>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Categoria> productList = response.body();

                    // Verifica que la lista de categorías no sea nula antes de configurar el adaptador
                    if (productList != null) {
                        elements=response.body();
                        categoryAdapter=new CategoryAdapter(elements,getContext().getApplicationContext());
                        categoryAdapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(v.getContext(), "funciona el click", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getActivity(),category.class);
                                startActivity(intent);
                            }
                        });
                        rvMenu.setAdapter(categoryAdapter);
                        Toast.makeText(getContext(), "La lista de categorías no es nula", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext(), "La lista de categorías es nula", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getContext(),"Error: response ",Toast.LENGTH_LONG).show();
                    Log.e("Retrofit", "Error en la llamada: res " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Categoria>> call, Throwable t) {
                Toast.makeText(getContext(),"Error: OnFailure ",Toast.LENGTH_LONG).show();
                Log.e("Retrofit", "Error en la llamada: failure" + t.getMessage());
            }
        });
    }
}
