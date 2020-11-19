package com.udacity.sandwichclub;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

public class SAdapter extends RecyclerView.Adapter<SAdapter.ViewHolder> {

    private final List<Sandwich> SandwichList2;

    public SAdapter(List<Sandwich> sandwichList) {
        SandwichList2 = sandwichList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sand_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        Sandwich sandwich = SandwichList2.get(i);
        viewHolder.sandwichName.setText(sandwich.getMainName());

    }

    @Override
    public int getItemCount() {
        return SandwichList2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sandwichName;

        ViewHolder( View itemView) {
            super(itemView);
            sandwichName = itemView.findViewById(R.id.sandwichName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View activ) {
                    launchDetailActivity(activ, getAdapterPosition());
                }
            });
        }

        private void launchDetailActivity(View activ, int position) {
            Intent intent = new Intent(activ.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, position);
            activ.getContext().startActivity(intent);
        }
    }
}
