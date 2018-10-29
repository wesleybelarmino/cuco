package com.app.cuco.recipedetail.preparation.ingredients.pojo;

import java.io.Serializable;
import lombok.Data;

@Data
public class Ingredients implements Serializable{
    private String ingredient;
    private String measure;
    private float quantity;


    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(quantity + "");
        stringBuilder.append(" ");
        stringBuilder.append(measure);
        stringBuilder.append(" ");
        stringBuilder.append(ingredient);

        return  stringBuilder.toString();
    }
}
