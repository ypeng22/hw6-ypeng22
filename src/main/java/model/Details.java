package model;

import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName(value = "Description")
    private String desc;

    public String getDesc() {
        return desc;
    }
}
