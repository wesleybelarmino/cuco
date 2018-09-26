package com.app.cuco.common.pojo;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable{
    private int id;
    private String name;
    private int servings;
    private String image;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
}
