package co.edu.gestion_inventarios;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private LinearLayout navView;
    private Button btnAddCategory;
    private TextView tvNameUser;
    private RelativeLayout containerCategory;
    private List<list_element> elements;
    private RecyclerView rvMenu;

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

        btnAddCategory = view.findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(this::onClick);

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
}
