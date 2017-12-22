package com.twenty.four.crafts;

/**
 * Created by rajiv on 10/9/17.
 */

public class IconsClass {
    int img_id;

    public IconsClass(int img_id, String craft_name, String url_param) {
        this.img_id = img_id;
        this.craft_name = craft_name;
        this.url_param = url_param;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getCraft_name() {
        return craft_name;
    }

    public void setCraft_name(String craft_name) {
        this.craft_name = craft_name;
    }

    String craft_name,url_param;

    public String getUrl_param() {
        return url_param;
    }

    public void setUrl_param(String url_params) {
        this.url_param = url_param;
    }
}
