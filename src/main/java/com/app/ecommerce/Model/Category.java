package com.app.ecommerce.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Category")
public class Category 
{
    @Id
    @GeneratedValue
    private Integer id;

    private @NotBlanck String categoryName;
    private @NotBlanck String description;
    private @NotBlanck String imgURL;
    
    public Category(){

    }
    
    public Category(Integer id, String categoryName, String description, String imgURL) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.imgURL = imgURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", categoryName=" + categoryName + ", description=" + description + ", imgURL="
                + imgURL + "]";
    }

    

    
}
