package com.example.calculator.controller;

import com.example.calculator.model.CalculatorRequest;
import com.example.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    //hey im trying to create a new branch
    //hey code rabbit check this pr
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RequestMapping("/add")
    public double add(@RequestBody CalculatorRequest request){
        return calculatorService.add(request.getNum1(), request.getNum2());
    }

    @RequestMapping("/subtract")
    public double subtract(@RequestBody CalculatorRequest request){
        return calculatorService.subtract(request.getNum1(), request.getNum2());
    }
    @RequestMapping("/multiply")
    public double multiply(@RequestBody CalculatorRequest request){
        return calculatorService.multiply(request.getNum1(), request.getNum2());
    }
    @RequestMapping("/division")
    public double division(@RequestBody CalculatorRequest request){
        return calculatorService.division(request.getNum1(), request.getNum2());
    }

    @GetMapping("/result")
    public Object getResult(){
        Double result=calculatorService.getLastResult();
        return result!=null?result:"no calculation done yet";
    }

    @DeleteMapping("/clear")
    public String clear(){
        calculatorService.clear();
        return "calculation cleared successfully";
    }

    @PostMapping("/power")
    public double power(@RequestBody CalculatorRequest request) {
        return Math.pow(request.getNum1(), request.getNum2());
    }

}
