package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input interval min: ");
        double min = in.nextDouble();
        System.out.print("Input interval max: ");
        double max = in.nextDouble();
        System.out.print("Input Eps: ");
        double Eps = in.nextDouble();

        System.out.println("MethodDichotomy " + MethodDichotomy(min, max, Eps));
        System.out.println("MethodGoldenRatio " + MethodGoldenRatio(min, max, Eps));
        System.out.println("MethodHords Minimum = " + Methods.Hords(true, min, max, Eps));
    }

    public static Result MethodDichotomy(double IntervalMin, double IntervalMax, double Eps)
    {
        Result res = new Result();
        res.setMinimum(Methods.Dichotomy(true, IntervalMin, IntervalMax, Eps));
        res.setMaximum(Methods.Dichotomy(false, IntervalMin, IntervalMax, Eps));
        return res;
    }

    public static Result MethodGoldenRatio(double IntervalMin, double IntervalMax, double Eps)
    {
        Result res = new Result();
        res.setMinimum(Methods.GoldenRatio(true, IntervalMin, IntervalMax, Eps));
        res.setMaximum(Methods.GoldenRatio(false, IntervalMin, IntervalMax, Eps));
        return res;
    }

    public static Result MethodHords(double IntervalMin, double IntervalMax, double Eps)
    {
        Result res = new Result();
        res.setMinimum(Methods.Hords(true, IntervalMin, IntervalMax, Eps));
        res.setMaximum(Methods.Hords(false, IntervalMin, IntervalMax, Eps));
        return res;
    }
}

class Result{

    private double Minimum;
    private double Maximum;

    public Result(){
        Minimum = 0;
        Maximum = 0;
    }

    public void setMinimum(double Value){
        Minimum = Value;
    }

    public void setMaximum(double Value){
        Maximum = Value;
    }

    public double getMinimum(){
        return Minimum;
    }
    public double getMaximum(){
        return Maximum;
    }

    public String toString(){
        return "Minimum = " + Minimum +
                " Maximum = " + Maximum;
    }
}

class Methods
{
    public static double Dichotomy(boolean findMininum, double IntervalMin, double IntervalMax, double Eps)
    {
        int n = 0; //число итераций
        double x1 = 0,fx1, x2, fx2, delta;
        boolean isStop = false;
        double a = IntervalMin;
        double b = IntervalMax;
        while(!isStop)
        {
            delta = b - a;
            if(delta <= Eps || n > 100)
            {
                isStop = true;
            }
            x1 = (a + b) / 2 - 0.001;
            fx1 = Math.pow(x1, 5) * Math.sin(5*x1);
            x2 = (a + b) / 2 + 0.001;
            fx2 = Math.pow(x2, 5) * Math.sin(5*x2);

            if(findMininum)
            {
                if((fx1 > fx2))
                    a = x1;
                else
                    b = x2;
            }
            else
            {
                if((fx1 <= fx2))
                    a = x1;
                else
                    b = x2;
            }
            n = n + 1;
        }
        System.out.println("Число пройденных итераций в методе дихотомии " + n + (findMininum ? " - поиск минимума" : " - поиск максимума"));
        return x1;
    }

    public static double GoldenRatio(boolean findMininum, double IntervalMin, double IntervalMax, double Eps)
    {
        int n = 0;
        double al = 0, fal, bt, fbt, delta;
        double a = IntervalMin;
        double b = IntervalMax;
        double zc1 = 2/(3 + Math.sqrt(5));
        double zc2 = 2/(1 + Math.sqrt(5));
        while(true)
        {
            delta = b - a;
            if(delta <= Eps)
            {
                break;
            }
            al = a + zc1 * delta;
            fal = Math.pow(al, 5) * Math.sin(5 * al);
            bt= a + zc2 * delta;
            fbt = Math.pow(bt, 5) * Math.sin(5 * bt);
            if(findMininum)
            {
                if(fal > fbt)
                    a =al;
                else
                    b = bt;
            }
            else
            {
                if(fal <= fbt)
                    a =al;
                else
                    b = bt;
            }
            n = n + 1;
        }
        System.out.println("Число пройденных итераций в методе золотого сечения " + n + (findMininum ? " - поиск минимума" : " - поиск максимума"));
        return al;
    }

    public static double Hords(boolean findMininum, double IntervalMin, double IntervalMax, double Eps)
    {
        int n = 0;
        double a = IntervalMin;
        double b = IntervalMax;
        double x = 100, fa, fb, delta, fx, xdelta;
        double xold;
        while(true)
        {
            delta = b - a;
            fa = 5*Math.pow(a, 4)*Math.sin(5*a) + 5*Math.pow(a, 5)*Math.cos(5*a);
            fb = 5*Math.pow(b, 4)*Math.sin(5*b) + 5*Math.pow(b, 5)*Math.cos(5*b);
            xold = x;
            //x=a-fa*delta/(fb-fa);
            x=b-fb*delta/(fb-fa);
            fx = Math.pow(x, 5)*Math.sin(5*x);
            xdelta = Math.abs(x - xold);
            if(fx == 0 || xdelta <= Eps)
            {
                break;
            }

            if(findMininum)
            {
                if(fx*fb <= 0)
                    a = x;
                else
                    b = x;
            }
            else
            {
                if(fx*fb <= 0)
                    a = x;
                else
                    b = x;
            }
            n = n + 1;

            //System.out.println( a + " " + b + " " + x + " " + fa + " " + fb + " " + delta + " " + fx + " " + xdelta);
        }
        System.out.println("Число пройденных итераций в методе хорд " + n + (findMininum ? " - поиск минимума" : " - поиск максимума"));
        return x;
    }
}