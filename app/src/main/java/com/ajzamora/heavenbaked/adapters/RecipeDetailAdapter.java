package com.ajzamora.heavenbaked.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajzamora.heavenbaked.databinding.ItemRecipeDetailBinding;
import com.ajzamora.heavenbaked.interfaces.IRecyclerItemClickListener;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailViewHolder> {
    private String mRecipe;
    private IRecyclerItemClickListener mOnClickListener;

    public RecipeDetailAdapter(String recipe, IRecyclerItemClickListener onClickListener) {
        mRecipe = recipe;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecipeDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeDetailBinding itemRecipeDetailBinding
                = ItemRecipeDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        RecipeDetailViewHolder recipeDetailViewHolder = new RecipeDetailViewHolder(itemRecipeDetailBinding);
        return recipeDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (mRecipe == null) ? 0 : 1;
    }

    public final class RecipeDetailViewHolder extends RecyclerView.ViewHolder
        implements RecyclerView.OnClickListener {
        ItemRecipeDetailBinding mItemRecipeDetailBinding;

        RecipeDetailViewHolder(ItemRecipeDetailBinding itemRecipeDetailBinding) {
            super(itemRecipeDetailBinding.getRoot());
            mItemRecipeDetailBinding = itemRecipeDetailBinding;
            mItemRecipeDetailBinding.tvRecipeDetailItem.setText(mRecipe);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }
}