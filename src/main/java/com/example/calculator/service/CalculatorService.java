package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private Double lastResult;


    public double add(double a,double b){
         lastResult=a+b;
        return lastResult;
    }

    public Double getLastResult() {
        return lastResult;
    }


    public void clear(){
        lastResult=null;
    }


    public double subtract(double a, double b){
        return a-b;
    }
    public double multiply(double a,double b){
        return a*b;
    }
    public double division(double a,double b){
        return a/b;
    }

    public double power(double num1, double num2) {
        return Math.pow(num1, num2);
    }
}
