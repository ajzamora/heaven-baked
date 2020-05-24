package com.ajzamora.heavenbaked;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajzamora.heavenbaked.databinding.ItemRecipeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<String> mRecipes;

    public RecipeAdapter() {
        this(new ArrayList<String>());
    }

    public RecipeAdapter(List<String> recipes) {
        // TODO: Delete fakeData
        // mRecipes = recipes; // uncomment after delete
        mRecipes = fakeData();
    }

    // TODO: Delete fakeData
    private List<String> fakeData() {
        String[] fakeArray = {"Nutella", "Peanut Butter & Jelly", "Butter",
                "Jam", "Cheese", "Egg", "Bacon", "Ipsum", "Nutella", "Peanut Butter & Jelly", "Butter",
                "Jam", "Cheese", "Egg", "Bacon", "Ipsum", "Nutella", "Peanut Butter & Jelly", "Butter",
                "Jam", "Cheese", "Egg", "Bacon", "Ipsum", "Nutella", "Peanut Butter & Jelly", "Butter",
                "Jam", "Cheese", "Egg", "Bacon", "Ipsum", "Nutella", "Peanut Butter & Jelly", "Butter",
                "Jam", "Cheese", "Egg", "Bacon", "Ipsum", "Nutella", "Peanut Butter & Jelly", "Butter",
                "Jam", "Cheese", "Egg", "Bacon", "end"};
        return Arrays.asList(fakeArray);
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

    public final class RecipeViewHolder extends RecyclerView.ViewHolder {
        ItemRecipeBinding mItemRecipeBinding;

        RecipeViewHolder(ItemRecipeBinding itemRecipeBinding) {
            super(itemRecipeBinding.getRoot());
            mItemRecipeBinding = itemRecipeBinding;
        }

        void bind(int position) {
            String recipe = mRecipes.get(position);
            mItemRecipeBinding.tvRecipeItem.setText(recipe);
        }
    }
}