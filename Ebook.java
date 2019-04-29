package com.kalangirajeev.ebooks.apgovtebooks;

/**
 * Created by dell on 12/15/2016.
 */

public class Ebook {
    private String para_name;
    private String para_desc;

    public String getPara_name() {
        return para_name;
    }

    public void setPara_name(String para_name) {
        this.para_name = para_name;
    }

    public String getPara_desc() {
        return para_desc;
    }

    public void setPara_desc(String para_desc) {
        this.para_desc = para_desc;
    }

    @Override
    public String toString() {
        String str, str1, str2;

        str1 = para_name;
        str2 = para_desc;

        str1 = str1.replaceAll("\\s ( )+", " ");
        str2 = str2.replaceAll("\\s ( )+", " ");

        str = str1 + "\n            " + str2 + "\n";
        return str;
    }
}
