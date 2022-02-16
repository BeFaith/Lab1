package com.company;

public class Function {
    public int degreeX;
    public int multiplierX;
    double Delta = 0.00000001;
    double maxN = 100;

    Function()
    {
        degreeX = 5;
        multiplierX = 5;
    }

    Function(int degreeX, int multiplierX)
    {
        this.degreeX = degreeX;
        this.multiplierX = multiplierX;
    }

    public Extreme FindExtremeByMethodDichotomy(double intervalMin, double intervalMax, double eps)
    {
        Extreme res = new Extreme();
        res.setMinimum(Dichotomy(true, intervalMin, intervalMax, eps));
        res.setMaximum(Dichotomy(false, intervalMin, intervalMax, eps));
        return res;
    }

    public Extreme FindExtremeByMethodGoldenRatio(double intervalMin, double intervalMax, double eps)
    {
        Extreme res = new Extreme();
        res.setMinimum(GoldenRatio(true, intervalMin, intervalMax, eps));
        res.setMaximum(GoldenRatio(false, intervalMin, intervalMax, eps));
        return res;
    }

    public Extreme FindExtremeByMethodHords(double intervalMin, double intervalMax, double eps)
    {
        Extreme res = new Extreme();
        res.setMinimum(Hords(true, intervalMin, intervalMax, eps));
        res.setMaximum(Hords(false, intervalMin, intervalMax, eps));
        return res;
    }

    public double Dichotomy(boolean findMininum, double intervalMin, double intervalMax, double eps)
    {
        int n = 0; //число итераций
        double x1 = 0,fx1, x2, fx2, deltan;
        boolean isStop = false;
        double a = intervalMin;
        double b = intervalMax;
        while(!isStop)
        {
            deltan = b - a;
            if(deltan <= eps || n > maxN)
            {
                isStop = true;
            }
            x1 = (a + b) / 2 - Delta;
            fx1 = Math.pow(x1, degreeX) * Math.sin(multiplierX*x1);
            x2 = (a + b) / 2 + Delta;
            fx2 = Math.pow(x2, degreeX) * Math.sin(multiplierX*x2);

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

    public double GoldenRatio(boolean findMininum, double intervalMin, double intervalMax, double eps)
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
            fal = Math.pow(al, degreeX) * Math.sin(multiplierX * al);
            bt= a + zc2 * delta;
            fbt = Math.pow(bt, degreeX) * Math.sin(multiplierX * bt);
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

    public double Hords(boolean findMininum, double intervalMin, double intervalMax, double eps)
    {
        int n = 0;
        double a = intervalMin;
        double b = intervalMax;
        double x = 100, fa, fb, delta, fx, xdelta;
        double xold;
        while(true)
        {
            delta = b - a;
            fa = degreeX*Math.pow(a, degreeX - 1)*Math.sin(multiplierX*a) + degreeX*Math.pow(a, degreeX)*Math.cos(multiplierX*a);
            fb = degreeX*Math.pow(b, degreeX - 1)*Math.sin(multiplierX*b) + degreeX*Math.pow(b, degreeX)*Math.cos(multiplierX*b);
            xold = x;
            //x=a-fa*delta/(fb-fa);
            x=b-fb*delta/(fb-fa);
            fx = Math.pow(x, degreeX)*Math.sin(multiplierX*x);
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
