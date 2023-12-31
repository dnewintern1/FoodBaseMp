package com.base.foodbasemp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.base.foodbasemp.Listeners.RecipeClickListener;
import com.base.foodbasemp.R;
import com.base.foodbasemp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.RandomRecipeViewHolder> {
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {

        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_timeduration.setText(list.get(position).readyInMinutes + " Minutes");
        holder.textView_like.setText(list.get(position).aggregateLikes + " Likes");
        holder.textView_serving.setText(list.get(position).servings+" Serving");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);


        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

        CardView random_list_container;
        TextView textView_title, textView_like, textView_serving, textView_timeduration;
        ImageView imageView_food;


        public RandomRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_container = itemView.findViewById(R.id.random_list_container);
            textView_like = itemView.findViewById(R.id.textView_like);
            textView_serving = itemView.findViewById(R.id.textView_Serving);
            textView_timeduration = itemView.findViewById(R.id.textView_timePeriod);
            textView_title = itemView.findViewById(R.id.textview_title);
            imageView_food = itemView.findViewById(R.id.imageView_food);

        }
    }

}