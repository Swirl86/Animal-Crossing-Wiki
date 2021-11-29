package com.example.acnh_wiki.song;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewvHolder> implements Filterable {
    private List<SongEntity> songList;
    private List<SongEntity> songFilterList;
    private Context context;
    private RecyclerViewClickListener listener;

    public SongAdapter(List<SongEntity> songList,  Context context, RecyclerViewClickListener listener) {
        this.songList = songList;
        this.context = context;
        this.listener = listener;
        songFilterList = new ArrayList<>(songList);
    }

    @NonNull
    @Override
    public SongViewvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song, parent , false);
        return new SongAdapter.SongViewvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewvHolder holder, int position) {
        SongEntity song = songList.get(position);
        String text = Utils.capitalizeString(song.getName().getNameEUen());
        holder.name.setText(text);

        text = song.getByePrice() != null ? song.getByePrice() + " \uD83D\uDCB0" : "NaN";
        holder.buyPrice.setText(text);

        text = song.getSellPrice() + " \uD83D\uDCB0";
        holder.sellPrice.setText(text);
        Picasso.with(context).load(song.getImageUri()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    @Override
    public Filter getFilter() {
        return songFilter;
    }

    private Filter songFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           List<SongEntity> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(songFilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SongEntity song: songFilterList ) {
                    if(isContains(filterPattern, song)) {
                        filteredList.add(song);
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
            songList.clear();
            songList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

   private boolean isContains(String filterPattern, SongEntity song) {
        return song.getName().getNameEUen().toLowerCase().contains(filterPattern);
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class SongViewvHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name , sellPrice , buyPrice;
        ImageView img;

        public SongViewvHolder(@NonNull View view) {
            super(view);

            name = view.findViewById(R.id.song_title);
            sellPrice = view.findViewById(R.id.song_sell_price);
            buyPrice = view.findViewById(R.id.song_buy_price);
            img = view.findViewById(R.id.song_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
