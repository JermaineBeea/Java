package com.example;

/**
 * Simple Java application
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        Calculator calc = new Calculator();
        int result = calc.add(5, 3);
        System.out.println("5 + 3 = " + result);
        
        result = calc.multiply(4, 7);
        System.out.println("4 * 7 = " + result);
    }
}

/**
 * Simple calculator class to demonstrate functionality
 */
class Calculator {
    
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}
