package ru.internet.sergeevss90.data;

public class ExpectedResultCalculator {
    public String getData(String number) {
        return String.valueOf(Integer.parseInt(number) + 1);
    }

    public String getData(String number, String action) {
        switch (action) {
            case "+":
                return String.valueOf(Integer.parseInt(number) + Integer.parseInt(number));
            case "-":
                return String.valueOf(Integer.parseInt(number) - Integer.parseInt(number));
            case "*":
                return String.valueOf(Integer.parseInt(number) * Integer.parseInt(number));
            case "/":
                return String.valueOf(Integer.parseInt(number) / Integer.parseInt(number));
        }
        return "0";
    }

    public String getData(String firstNumber, String action, String secondNumber) {
        switch (action) {
            case "+":
                return String.valueOf(Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber));
            case "-":
                return String.valueOf(Integer.parseInt(firstNumber) - Integer.parseInt(secondNumber));
            case "*":
                return String.valueOf(Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
            case "/":
                return String.valueOf(Integer.parseInt(firstNumber) / Integer.parseInt(secondNumber));
        }
        return "0";
    }
}
