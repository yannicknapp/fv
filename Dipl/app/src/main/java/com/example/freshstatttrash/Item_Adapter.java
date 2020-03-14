package com.example.freshstatttrash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.ExampleViewHolder> {

    private ArrayList<Item_LandRezepte> itemlist;
    private onNoteListener monlistener;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView view1;
        public TextView view2;
        onNoteListener onlistener;
        public ExampleViewHolder(@NonNull View itemView, onNoteListener onlistener) {
            super(itemView);
            view1=itemView.findViewById(R.id.textView1);
            view2=itemView.findViewById(R.id.textView2);
            this.onlistener=onlistener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onlistener.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteListener{
        void onNoteClick(int position);
    }

    public Item_Adapter(ArrayList<Item_LandRezepte> itemlist, onNoteListener monlistener)
    {
        this.itemlist=itemlist;
        this.monlistener=monlistener;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_landrezepte, parent, false);
        ExampleViewHolder evh=new ExampleViewHolder(v, monlistener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
    {
        Item_LandRezepte currentItem= itemlist.get(position);

        holder.view1.setText(currentItem.getmText1());
        holder.view2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount()
    {
        return itemlist.size();
    }
}
