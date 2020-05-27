package com.ajzamora.heavenbaked.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.databinding.ItemRecipeBinding;
import com.ajzamora.heavenbaked.interfaces.IRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipes;
    final private IRecyclerItemClickListener mOnClickListener;

    public RecipeAdapter(IRecyclerItemClickListener onClickListener) {
        this(new ArrayList<Recipe>(), onClickListener);
    }

    public RecipeAdapter(List<Recipe> recipes, IRecyclerItemClickListener onClickListener) {
        mRecipes = recipes;
        mOnClickListener = onClickListener;
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeBinding itemRecipeBinding
                = ItemRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        RecipeViewHolder movieViewHolder = new RecipeViewHolder(itemRecipeBinding);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return (mRecipes == null) ? 0 : mRecipes.size();
    }

    public Recipe getItem(int position) {
        return mRecipes.get(position);
    }

    public final class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        ItemRecipeBinding mItemRecipeBinding;

        RecipeViewHolder(ItemRecipeBinding itemRecipeBinding) {
            super(itemRecipeBinding.getRoot());
            mItemRecipeBinding = itemRecipeBinding;
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            Recipe recipe = mRecipes.get(position);
            mItemRecipeBinding.tvRecipeItem.setText(recipe.getName());
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }
}