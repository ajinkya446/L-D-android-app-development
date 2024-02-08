package com.abc.notifiaction;

import android.graphics.drawable.Drawable;

public class CategoryModel {
    public int drawableId;
    public String titleName;
    public int drawable;

    public CategoryModel(int drawableId, String titleName, int drawable) {
        this.drawableId = drawableId;
        this.titleName = titleName;
        this.drawable = drawable;
    }


    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
