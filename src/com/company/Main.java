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
        System.out.println("MethodDichotomy " + Methods.FindExtremeByMethodDichotomy(min, max, Eps));
        System.out.println("MethodGoldenRatio " + Methods.FindExtremeByMethodGoldenRatio(min, max, Eps));
        System.out.println("MethodHords Minimum = " + Methods.Hords(true, min, max, Eps));
    }
}