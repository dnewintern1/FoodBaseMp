package com.base.foodbasemp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.foodbasemp.R;
import com.base.foodbasemp.model.Step;

import java.util.List;

public class InstructionsStepAdapter extends RecyclerView.Adapter<InstructionsStepAdapter.InstructionStepViewHolder>{

    Context context;
    List<Step> list;


    public InstructionsStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {

        holder.textView_instruction_step_number.setText(String.valueOf(list.get(position).number));
        holder.textView_instruction_step_title.setText(list.get(position).step);

        holder.recycler_instructions_ingredient.setHasFixedSize(true);
        holder.recycler_instructions_ingredient.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        InstructionIngredientAdapter instructionIngredientAdapter= new InstructionIngredientAdapter(context,list.get(position).getIngredients());

        holder.recycler_instructions_ingredient.setAdapter(instructionIngredientAdapter);

        holder.recycler_instructions_equipments.setHasFixedSize(true);
        holder.recycler_instructions_equipments.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false));
        InstructionEquipmentAdapter instructionEquipmentAdapter =new InstructionEquipmentAdapter(context, list.get(position).getEquipment() );
        holder.recycler_instructions_equipments.setAdapter(instructionEquipmentAdapter);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class InstructionStepViewHolder extends RecyclerView.ViewHolder{

        TextView textView_instruction_step_number, textView_instruction_step_title;
        RecyclerView recycler_instructions_ingredient ,recycler_instructions_equipments;


        public InstructionStepViewHolder(@NonNull View itemView) {
            super(itemView);



            textView_instruction_step_number = itemView.findViewById(R.id.textView_instruction_step_number);
            textView_instruction_step_title = itemView.findViewById(R.id.textView_instruction_step_title);
            recycler_instructions_equipments = itemView.findViewById(R.id.recycler_instructions_equipments);
            recycler_instructions_ingredient = itemView.findViewById(R.id.recycler_instructions_ingredient);





        }
    }

}

