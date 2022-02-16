package com.company;

public class Methods {
    static double Delta = 0.00000001;

    public static Extreme FindExtremeByMethodDichotomy(double intervalMin, double intervalMax, double eps)
    {
        Extreme res = new Extreme();
        res.setMinimum(Dichotomy(true, intervalMin, intervalMax, eps));
        res.setMaximum(Dichotomy(false, intervalMin, intervalMax, eps));
        return res;
    }

    public static Extreme FindExtremeByMethodGoldenRatio(double intervalMin, double intervalMax, double eps)
    {
        Extreme res = new Extreme();
        res.setMinimum(GoldenRatio(true, intervalMin, intervalMax, eps));
        res.setMaximum(GoldenRatio(false, intervalMin, intervalMax, eps));
        return res;
    }

    public static Extreme FindExtremeByMethodHords(double intervalMin, double intervalMax, double eps)
    {
        Extreme res = new Extreme();
        res.setMinimum(Hords(true, intervalMin, intervalMax, eps));
        res.setMaximum(Hords(false, intervalMin, intervalMax, eps));
        return res;
    }

    public static double Dichotomy(boolean findMininum, double intervalMin, double intervalMax, double eps)
    {
        int n = 0; //число итераций
        double x1 = 0,fx1, x2, fx2, deltan;
        boolean isStop = false;
        double a = intervalMin;
        double b = intervalMax;
        while(!isStop)
        {
            deltan = b - a;
            if(deltan <= eps || n > 100)
            {
                isStop = true;
            }
            x1 = (a + b) / 2 - Delta;
            fx1 = Math.pow(x1, 5) * Math.sin(5*x1);
            x2 = (a + b) / 2 + Delta;
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

    public static double GoldenRatio(boolean findMininum, double intervalMin, double intervalMax, double eps)
    {
        int n = 0;
        double al = 0, fal, bt, fbt, delta;
        double a = intervalMin;
        double b = intervalMax;
        double zc1 = 2/(3 + Math.sqrt(5));
        double zc2 = 2/(1 + Math.sqrt(5));
        while(true)
        {
            delta = b - a;
            if(delta <= eps)
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

    public static double Hords(boolean findMininum, double intervalMin, double intervalMax, double eps)
    {
        int n = 0;
        double a = intervalMin;
        double b = intervalMax;
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
            if(fx == 0 || xdelta <= eps)
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
