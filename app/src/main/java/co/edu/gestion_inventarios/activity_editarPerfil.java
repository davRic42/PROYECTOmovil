package co.edu.gestion_inventarios;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_editarPerfil extends AppCompatActivity {
    private Button btnAtrasPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        Begin();
    }


    @SuppressLint("WrongViewCast")
    private void Begin() {
        this.btnAtrasPerfil=findViewById(R.id.ibtnAtrasPerfil);
    }
}