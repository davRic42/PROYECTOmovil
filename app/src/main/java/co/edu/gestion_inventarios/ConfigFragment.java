package co.edu.gestion_inventarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ConfigFragment extends Fragment implements View.OnClickListener {
    private ImageButton btnBackConfig;
    private Button btnEliminarCategoria;
    private Button btnBorraDatos;
    private Button btnCrearNuevaCuenta;
    private Button btnCerrarSesion;

    public ConfigFragment() {
        // Required empty public constructor
    }

    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
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
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        btnBackConfig = view.findViewById(R.id.btnBackConfig);
        btnBackConfig.setOnClickListener(this);

        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this);

        btnCrearNuevaCuenta = view.findViewById(R.id.btnCrearNuevaCuenta);
        btnCrearNuevaCuenta.setOnClickListener(this);
        // Otros botones aquí...

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBackConfig) {
            Toast.makeText(getActivity(), "Clic en el botón Back", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), menu.class); // Ajusta el nombre de la actividad de destino
            startActivity(intent);
        } else if (v.getId()==R.id.btnCerrarSesion) {
            Intent intent = new Intent(getActivity(), MainActivity.class); // Ajusta el nombre de la actividad de destino
            startActivity(intent);
        } else if (v.getId()==R.id.btnCrearNuevaCuenta) {
            Intent intent = new Intent(getActivity(), RegistroCuenta.class); // Ajusta el nombre de la actividad de destino
            startActivity(intent);
        }
    }
}
