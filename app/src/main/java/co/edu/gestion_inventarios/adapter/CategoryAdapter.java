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
    private View.OnClickListener listener;

    public CategoryAdapter(ArrayList<Categoria> itemList){
        this.categoryList=itemList;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categoria categoria = categoryList.get(position);

        // Configura los datos en las vistas del CardView
        // Aquí puedes cargar la imagen si tu modelo de datos tiene información de imagen.
        // Puedes usar bibliotecas como Picasso o Glide para cargar imágenes desde URL o recursos.
        // Picasso.get().load(categoria.getImagenUrl()).into(holder.categoryPhoto);

        holder.nameCategory.setText(categoria.getName_category());
        holder.numberCategory.setText(String.valueOf(categoria.getId_category()));
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }else{
            Toast.makeText(v.getContext(), "???", Toast.LENGTH_SHORT).show();
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.listener = onClickListener;
    }

    public void setItems(List<Categoria> items){
        categoryList=items;
    }

    public void setCategory(List<Categoria> categoryList){
        this.categoryList= categoryList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView categoryPhoto;
        public TextView nameCategory;
        public TextView numberCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryPhoto = itemView.findViewById(R.id.ivCategoryPhoto);
            nameCategory = itemView.findViewById(R.id.tvNameCategory);
            numberCategory = itemView.findViewById(R.id.tvNumberCard);
        }
    }

}
