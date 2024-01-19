package dev.adamedmunds.IODSave.JSON;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonFileFormat {
    @SerializedName("links")
    private ArrayList<LinkObject> links;

    public ArrayList<LinkObject> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkObject> links) {
        this.links = links;
    }
}
