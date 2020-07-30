package com.codepath.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//responsible for showing data from the model into  a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnClickListener{
        void onItemClicked(int position);
    }
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflater to inflate a view
        //wrap it inside a view holder and return it

        View toDoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap inside of a view holder and return it
        return new ViewHolder(toDoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab the item at the position
        String item = items.get(position);
        // bind the item into the specified viewholder
        holder.bind(item);
    }

    // tells recycler view how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represents each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }

        // update the view inside of the view holder with this fata
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //giving position of item that is long pressed to listener
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
