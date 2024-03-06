package com.abc.notifiaction.model;

import java.io.Serializable;
import java.util.Map;

public class Category implements Serializable {
    public String drawableId;
    public String titleName;
    public Map<String, String> colorMap;

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

    public Map<String, String> getColorMap() {
        return colorMap;
    }

    public void setColorMap(Map<String, String> colorMap) {
        this.colorMap = colorMap;
    }

    public Category(String drawableId, String titleName, Map<String, String> colorMap) {
        this.drawableId = drawableId;
        this.titleName = titleName;
        this.colorMap = colorMap;
    }
}