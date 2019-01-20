package main.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private List<Owner> ownerList;


    public Bank(String name) {
        this.name = name;
        ownerList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", ownerList=" + ownerList +
                '}';
    }
}
