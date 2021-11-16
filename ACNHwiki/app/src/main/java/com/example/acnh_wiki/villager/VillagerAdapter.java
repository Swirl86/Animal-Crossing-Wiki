package com.example.acnh_wiki.villager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acnh_wiki.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VillagerAdapter extends RecyclerView.Adapter<VillagerAdapter.VillagerViewHolder> {

    private List<VillagerEntity> villagerList;
    private Context context;
    private RecyclerViewClickListener listener;

    public VillagerAdapter(List<VillagerEntity> villagers, Context context, RecyclerViewClickListener listener) {
        this.villagerList = villagers;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VillagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.villager , parent , false);
        return new VillagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VillagerViewHolder holder, int position) {
        VillagerEntity villager = villagerList.get(position);
        holder.name.setText(villager.getName().getNameEUen());
        holder.species.setText(villager.getSpecies());
        holder.personality.setText(villager.getPersonality());
        holder.saying.setText(villager.getSaying());
        Picasso.with(context).load(villager.getImageUri()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return villagerList.size();
    }

    // For villager detail view
    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }


    public class VillagerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name , species , personality , saying;
        ImageView img;

        public VillagerViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.villagerName);
            species = view.findViewById(R.id.villagerSpecies);
            personality = view.findViewById(R.id.villagerPersonality);
            saying = view.findViewById(R.id.villagerSaying);
            img = view.findViewById(R.id.villagerImage);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
