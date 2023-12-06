package co.edu.gestion_inventarios;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<list_element> mData;
    private LayoutInflater mInflater;
    private Context context;
    private Drawable imgCarton;
    final ListAdapter.OnItemCLickListener listener;

    public interface OnItemCLickListener{
        void OnItemClick(list_element item);
    }

    public ListAdapter(List<list_element> itemList,Context context, ListAdapter.OnItemCLickListener listener){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
         return mData.size();
    }

    @Override
    public  ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=mInflater.inflate(R.layout.list_element,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<list_element> items){
        mData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameCategory;
        TextView numberCategory;

        ViewHolder(View itemView) {
            super(itemView);
            // Corrige la inicialización de los TextView
            nameCategory = itemView.findViewById(R.id.tvNameCategory);
            numberCategory = itemView.findViewById(R.id.tvNumberCard);
        }

        void bindData(final list_element item) {
            numberCategory.setText(item.getNumberCard());
            nameCategory.setText(item.getNameCard());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(item);
                }
            });
        }
    }

}
