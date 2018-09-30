package com.app.cuco.recipedetail.preparation.steps.pojo;

import java.io.Serializable;
import lombok.Data;

@Data
public class Steps implements Serializable {
    private int id;
    private String description;
    private String shortDescription;
    private String thumbnailURL;
    private String videoURL;
}
