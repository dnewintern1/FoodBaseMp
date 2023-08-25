package com.base.foodbasemp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.foodbasemp.R;
import com.base.foodbasemp.model.Equipment;
import com.base.foodbasemp.model.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquipmentAdapter extends RecyclerView.Adapter<InstructionEquipmentAdapter.InstructionEquipmentViewHolder> {

    Context context;
    List<Equipment> list;

    public InstructionEquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_item, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentViewHolder holder, int position) {

        holder.textView_instruction_step_item.setText(list.get(position).getName());
        holder.textView_instruction_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+ list.get(position).getImage()).into(holder.imageView_instruction_step_item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class InstructionEquipmentViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView_instruction_step_item;
        TextView textView_instruction_step_item;




        public InstructionEquipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_instruction_step_item = itemView.findViewById(R.id.imageView_instruction_step_item);
            textView_instruction_step_item = itemView.findViewById(R.id.textView_instruction_step_item);
        }
    }
}

