package com.example.calculator.controller;

import com.example.calculator.model.CalculatorRequest;
import com.example.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    //hey im trying to create a new branch
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RequestMapping("/add")
    public double add(@RequestBody CalculatorRequest request) {
        return calculatorService.add(request.getNum1(), request.getNum2());
    }

    @RequestMapping("/subtract")
    public double subtract(@RequestBody CalculatorRequest request) {
        return calculatorService.subtract(request.getNum1(), request.getNum2());
    }

    @RequestMapping("/multiply")
    public double multiply(@RequestBody CalculatorRequest request) {
        return calculatorService.multiply(request.getNum1(), request.getNum2());
    }

    @RequestMapping("/division")
    public double division(@RequestBody CalculatorRequest request) {
        return calculatorService.division(request.getNum1(), request.getNum2());
    }

    //completed calculator basics
    //get-fetch last result
    @GetMapping("/result")
    public Object getResult() {
        Double result = calculatorService.getLastResult();
        return result != null ? result : "no calculation done yet";
    }

    //delete clear result
    @DeleteMapping("/clear")
    public String clear() {
        calculatorService.clear();
        return "calculation cleared successfully";
    }

    //controller endpoint for square root
    @GetMapping("/sqrt/{num}")
    public double squareRoot(@PathVariable double num) {
        return calculatorService.squareRoot(num);
    }
}
