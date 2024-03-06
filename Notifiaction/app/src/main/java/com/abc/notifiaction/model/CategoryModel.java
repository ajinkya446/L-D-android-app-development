package com.abc.notifiaction.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.Map;

public class CategoryModel implements Serializable {
    public String drawableId;
    public String titleName;
    public Drawable drawable;

    public CategoryModel(String drawableId, String titleName, Drawable drawable) {
        this.drawableId = drawableId;
        this.titleName = titleName;
        this.drawable = drawable;
    }

    public String getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(String drawableId) {
        this.drawableId = drawableId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}



