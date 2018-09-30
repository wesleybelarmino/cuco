package com.app.cuco.recipedetail.preparation.ingredients.pojo;

import java.io.Serializable;
import lombok.Data;

@Data
public class Ingredients implements Serializable{
    private String ingredient;
    private String measure;
    private float quantity;
}
