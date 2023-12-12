package co.edu.gestion_inventarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {
    MenuFragment menuFragment=new MenuFragment();
    EditUsFragment editUsFragment=new EditUsFragment();
    ConfigFragment configFragment=new ConfigFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navigation=findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(menuFragment);


    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.menuFragment) {
                loadFragment(menuFragment);
                return true;
            } else if (id == R.id.configFragment) {
                loadFragment(configFragment);
                return true;
            } else if (id == R.id.editUsFragment) {
                loadFragment(editUsFragment);
                return true;
            }
            return false;
        }
    };


    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent,fragment);
        transaction.commit();
    }

}
