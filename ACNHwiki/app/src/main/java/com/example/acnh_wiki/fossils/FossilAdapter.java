package com.example.acnh_wiki.fossils;

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

public class FossilAdapter extends RecyclerView.Adapter<FossilAdapter.FossilViewHolder> implements Filterable {
    private List<FossilEntity> fossilsList;
    private List<FossilEntity> fossilsFilterList;
    private Context context;
    private RecyclerViewClickListener listener;

    public FossilAdapter(List<FossilEntity> fossilsList, Context context, RecyclerViewClickListener listener) {
        this.fossilsList = fossilsList;
        this.context = context;
        this.listener = listener;
        fossilsFilterList = new ArrayList<>(fossilsList);
    }

    @NonNull
    @Override
    public FossilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fossil, parent , false);
        return new FossilAdapter.FossilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FossilViewHolder holder, int position) {
        FossilEntity fossil = fossilsList.get(position);
        String name = Utils.capitalizeString(fossil.getName().getNameEUen());
        holder.name.setText(name);
        String text = fossil.getPrice() + " \uD83D\uDCB0";
        holder.sellPrice.setText(text);
        text = Utils.capitalizeString(fossil.getPartOf());
        holder.partOf.setText(text.equals(name) ? "" : "Part of " + text);
        Picasso.with(context).load(fossil.getImageUri()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return fossilsList.size();
    }


    @Override
    public Filter getFilter() {
        return fossilFilter;
    }

    private Filter fossilFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FossilEntity> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(fossilsFilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (FossilEntity fossil: fossilsFilterList ) {
                    if(isContains(filterPattern, fossil)) {
                        filteredList.add(fossil);
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
            fossilsList.clear();
            fossilsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    private boolean isContains(String filterPattern, FossilEntity fossil) {
        return fossil.getName().getNameEUen().toLowerCase().contains(filterPattern) ||
                fossil.getImageUri().toLowerCase().contains(filterPattern);
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }


    public class FossilViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name , sellPrice , partOf;
        ImageView img;

        public FossilViewHolder(@NonNull View view) {
            super(view);

            name = view.findViewById(R.id.fossil_title);
            sellPrice = view.findViewById(R.id.fossil_sell_price);
            partOf = view.findViewById(R.id.fossil_part_of);
            img = view.findViewById(R.id.fossil_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
