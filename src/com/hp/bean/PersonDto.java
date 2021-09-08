package com.hp.bean;

public class PersonDto {
    private int gender;
    private double avgscore;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(double avgscore) {
        this.avgscore = avgscore;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "gender=" + gender +
                ", avgscore=" + avgscore +
                '}';
    }
}
