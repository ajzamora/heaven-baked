package com.ajzamora.heavenbaked.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.ajzamora.heavenbaked.data.Ingredient;
import com.ajzamora.heavenbaked.data.Step;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

@Entity
public class Recipe implements Serializable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("servings")
    @Expose
    private int servings;
    @SerializedName("image")
    @Expose
    private String image;
    private final static long serialVersionUID = 2317324046967821115L;

    /**
     * No args constructor for use in serialization
     */
    @Ignore
    private Recipe() {
    }

    /**
     * @param image
     * @param servings
     * @param name
     * @param ingredients
     * @param id
     * @param steps
     */
    public Recipe(long id, String name, List<Ingredient> ingredients, List<Step> steps, int servings, String image) {
        super();
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("ingredients", ingredients).append("steps", steps).append("servings", servings).append("image", image).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(image).append(servings).append(name).append(ingredients).append(id).append(steps).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Recipe) == false) {
            return false;
        }
        Recipe rhs = ((Recipe) other);
        return new EqualsBuilder().append(image, rhs.image).append(servings, rhs.servings).append(name, rhs.name).append(ingredients, rhs.ingredients).append(id, rhs.id).append(steps, rhs.steps).isEquals();
    }

}