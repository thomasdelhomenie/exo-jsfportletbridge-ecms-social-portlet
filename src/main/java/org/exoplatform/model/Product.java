package org.exoplatform.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thomas Delhoménie
 * Date: 26/12/11
 * Time: 16:42
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String thumbnailPath;
    private List<Picture> pictures;

    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnailPath() {	
    	return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	/**
     * Get pictures linked to the product in eXo Content Management
     * @return
     */
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
    
}
