package com.ajinkya.photoalbum.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_images")
public class MyImages {
    @PrimaryKey(autoGenerate = true)
    public int imageId;
    public String imageName;
    public String imageTitle;
    public String imageDescription;
    public byte[] image;

    public MyImages(String imageName, String imageTitle, String imageDescription, byte[] image) {
        this.imageName = imageName;
        this.imageTitle = imageTitle;
        this.imageDescription = imageDescription;
        this.image = image;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
