package com.twenty.four.crafts;

/**
 * Created by rajiv on 2/9/17.
 */

public class Talent_support_class {
    public Talent_support_class(int img_1, int img_2, int img_3, int bgc, String craft_name) {
        this.img_1 = img_1;
        this.img_2 = img_2;
        this.img_3 = img_3;
        this.bgc = bgc;
        this.craft_name = craft_name;
    }

    public int getImg_1() {
        return img_1;
    }

    public void setImg_1(int img_1) {
        this.img_1 = img_1;
    }

    public int getImg_2() {
        return img_2;
    }

    public void setImg_2(int img_2) {
        this.img_2 = img_2;
    }

    public int getImg_3() {
        return img_3;
    }

    public void setImg_3(int img_3) {
        this.img_3 = img_3;
    }

    public int getBgc() {
        return bgc;
    }

    public void setBgc(int bgc) {
        this.bgc = bgc;
    }

    public String getCraft_name() {
        return craft_name;
    }

    public void setCraft_name(String craft_name) {
        this.craft_name = craft_name;
    }

    int img_1,img_2,img_3,bgc;
    String craft_name;
}
