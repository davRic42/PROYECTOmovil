package co.edu.gestion_inventarios;

import static co.edu.gestion_inventarios.api.ValuesApi.BASE_URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import co.edu.gestion_inventarios.api.ServiceLogin;
import co.edu.gestion_inventarios.model.Loger;
import co.edu.gestion_inventarios.model.ResponseCredentials;
import co.edu.gestion_inventarios.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditUsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText etNombreusuario;
    private EditText etContrasena;
    private EditText etConfirmarContrasena;
    private Button btnAplicarCambios;
    private Retrofit retrofit;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUsFragment newInstance(String param1, String param2) {
        EditUsFragment fragment = new EditUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_us, container, false);
        etNombreusuario = view.findViewById(R.id.etNombreusuario);
        etContrasena = view.findViewById(R.id.etContrasena);
        etConfirmarContrasena = view.findViewById(R.id.etConfirmarContrasena);
        btnAplicarCambios = view.findViewById(R.id.btnAplicarCambios);
        btnAplicarCambios.setOnClickListener(v -> updateUser());
        return view;
    }
    private void updateUser() {
        Context context = getContext();
        SharedPreferences loginPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        String loginId = loginPreferences.getString("loginId", "");
        String nameUpdate = etNombreusuario.getText().toString();
        String passUpdate = etContrasena.getText().toString();
        Loger loger = new Loger();
        loger.setUser_name(nameUpdate);
        loger.setUser_pss(md5(passUpdate));
        retrofit = ClienteRetrofit.getCliente(BASE_URL);
        ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
        Call<ResponseCredentials> call = serviceLogin.updateUsers(loger);
        call.enqueue(new Callback<ResponseCredentials>() {
            @Override
            public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                if (response.isSuccessful()) {
                    ResponseCredentials credentials = response.body();
                    Log.i("id_update", loginId);
                    if (loginId.equals("8")) {
                        Toast.makeText(context, "Proceso completo", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("updateCredentials", credentials.toString());
                        Toast.makeText(context, "El ID del usuario no coincide", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Error en la respuesta del servidor: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                Toast.makeText(context, "No se ha podido completar la actualización", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}