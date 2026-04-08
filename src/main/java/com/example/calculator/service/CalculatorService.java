package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private Double lastResult;
//adding secret key here
    private static final String ADMIN_KEY = "SECRET_12345_DONOT_SHARE";

    public double add(double a,double b){
         lastResult=a+b;
        return lastResult;
    }

    public Double getLastResult() {
        return lastResult;
    }

//    public void setLastResult(Double lastResult) {
//        this.lastResult = lastResult;
//    }
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
}
