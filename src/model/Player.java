package model;

public class Player {
    public Player(String user, int point) {
        this.user = user;
        this.point = point;
    }

    private String user;
    private int point;


    public String getUser() {
        return user;
    }

    public int getPoint() {
        return point;
    }



}