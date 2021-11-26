package com.example.acnh_wiki.art;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acnh_wiki.R;
import com.example.acnh_wiki.Utils;
import com.example.acnh_wiki.villager.VillagerEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtViewHolder> implements Filterable {

    private List<ArtEntity> artList;
    private List<ArtEntity> artFilterList;
    private Context context;
    private RecyclerViewClickListener listener;

    public ArtAdapter(List<ArtEntity> artList, Context context, RecyclerViewClickListener listener) {
        this.artList = artList;
        this.context = context;
        this.listener = listener;
        artFilterList = new ArrayList<>(artList);
    }

    @NonNull
    @Override
    public ArtAdapter.ArtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.art, parent , false);
        return new ArtAdapter.ArtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtAdapter.ArtViewHolder holder, int position) {
        ArtEntity art = artList.get(position);
        String name = Utils.capitalizeString(art.getName().getNameEUen());
        holder.name.setText(name);
        String price = art.getByePrice() + " \uD83D\uDCB0";
        holder.buyPrice.setText(price);
        price = art.getSellPrice() + " \uD83D\uDCB0";
        holder.sellPrice.setText(price);
        Picasso.with(context).load(art.getImageUri()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return artList.size();
    }

    @Override
    public Filter getFilter() {
        return artFitler;
    }

    private Filter artFitler = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ArtEntity> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(artFilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ArtEntity art: artFilterList ) {
                    if(isContains(filterPattern, art)) {
                        filteredList.add(art);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            artList.clear();
            artList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    private boolean isContains(String filterPattern, ArtEntity art) {
        return art.getName().getNameEUen().toLowerCase().contains(filterPattern);
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class ArtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name , buyPrice , sellPrice;
        ImageView img;

        public ArtViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.art_title);
            buyPrice = view.findViewById(R.id.art_buy_price);
            sellPrice = view.findViewById(R.id.art_sell_price);
            img = view.findViewById(R.id.art_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
