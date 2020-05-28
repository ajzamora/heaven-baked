package com.ajzamora.heavenbaked.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Ingredient implements Serializable {

    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    private final static long serialVersionUID = 3462832901305224216L;

    /**
     * No args constructor for use in serialization
     */
    private Ingredient() {
    }

    /**
     * @param quantity
     * @param measure
     * @param ingredient
     */
    public Ingredient(int quantity, String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("quantity", quantity).append("measure", measure).append("ingredient", ingredient).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(quantity).append(measure).append(ingredient).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ingredient) == false) {
            return false;
        }
        Ingredient rhs = ((Ingredient) other);
        return new EqualsBuilder().append(quantity, rhs.quantity).append(measure, rhs.measure).append(ingredient, rhs.ingredient).isEquals();
    }

}