package com.company;

public class Extreme {
    private double minimum;
    private double maximum;

    public Extreme(){
        minimum = 0;
        maximum = 0;
    }

    public void setMinimum(double Value){
        minimum = Value;
    }

    public void setMaximum(double Value){
        maximum = Value;
    }

    public double getMinimum(){
        return minimum;
    }
    public double getMaximum(){
        return maximum;
    }

    public String toString(){
        return "Minimum = " + minimum +
                " Maximum = " + maximum;
    }
}
