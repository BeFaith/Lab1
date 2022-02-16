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
        Function myFunctionParameters = new Function();
        System.out.println("MethodDichotomy " + myFunctionParameters.FindExtremeByMethodDichotomy(min, max, Eps));
        System.out.println("MethodGoldenRatio " + myFunctionParameters.FindExtremeByMethodGoldenRatio(min, max, Eps));
        System.out.println("MethodHords Minimum = " + myFunctionParameters.Hords(true, min, max, Eps));
    }
}