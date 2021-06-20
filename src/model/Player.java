package model;

import java.io.Serializable;

public class Player implements Serializable {
    public Player(String user, int point) {
        this.user = user;
        this.point = point;
    }

    private String user;
    private int point;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUser() {
        return user;
    }

    public int getPoint() {
        return point;
    }



}