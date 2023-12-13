package co.edu.gestion_inventarios.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import co.edu.gestion_inventarios.R;
import co.edu.gestion_inventarios.list_element;
import co.edu.gestion_inventarios.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener{

    private List<Categoria> categoryList;
    private Context context;

    public CategoryAdapter(List<Categoria> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.numberCategory.setText(categoryList.get(position).getId_category());
        holder.nameCategory.setText(categoryList.get(position).getName_category());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameCategory;
        public TextView numberCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCategory = itemView.findViewById(R.id.tvNameCategory);
            numberCategory = itemView.findViewById(R.id.tvNumberCard);
        }
    }
}
