package com.revature.models;

@Deprecated
public class ReimbursementType {
    private int id;
    private String Type;

    public ReimbursementType() {    }

    public ReimbursementType(int id, String type) {
        this.id = id;
        Type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "id=" + id +
                ", Type='" + Type + '\'' +
                '}';
    }
}
