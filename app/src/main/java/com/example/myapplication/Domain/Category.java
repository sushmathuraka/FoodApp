package com.example.myapplication.Domain;

public class Category {
    private int Id;
    private String ImagePath;
    private String Name;

    // Getters and setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        this.ImagePath = imagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
