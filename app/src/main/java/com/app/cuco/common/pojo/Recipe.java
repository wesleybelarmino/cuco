package com.app.cuco.common.pojo;

import com.app.cuco.recipedetail.preparation.ingredients.pojo.Ingredients;
import com.app.cuco.recipedetail.preparation.steps.pojo.Steps;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class Recipe implements Serializable{
    private int id;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private int servings;
    private String image;
}
