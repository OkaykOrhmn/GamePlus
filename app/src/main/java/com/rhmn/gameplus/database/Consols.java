package com.rhmn.gameplus.database;

public class Consols {

    public int id = 1;
    public String name = "";
    public String genre = "";
    private boolean isSelected = false;


    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Consols(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
