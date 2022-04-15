package ru.internet.sergeevss90.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class CalculatorPage {
    SelenideElement add = $(byName("add"));
    SelenideElement subtract = $(byName("subtract"));
    SelenideElement multiply = $(byName("multiply"));
    SelenideElement divide = $(byName("divide"));
    SelenideElement numberZero = $(byName("zero"));
    SelenideElement numberOne = $(byName("one"));
    SelenideElement numberTwo = $(byName("two"));
    SelenideElement numberThree = $(byName("three"));
    SelenideElement numberFour = $(byName("four"));
    SelenideElement numberFive = $(byName("five"));
    SelenideElement numberSix = $(byName("six"));
    SelenideElement numberSeven = $(byName("seven"));
    SelenideElement numberEight = $(byName("eight"));
    SelenideElement numberNine = $(byName("nine"));
    SelenideElement calculate = $(byName("calculate"));
    SelenideElement display = $("#display");

    public CalculatorPage useNumber(String number) {
        switch (number) {
            case "0":
                numberZero.click();
                return this;
            case "1":
                numberOne.click();
                return this;
            case "2":
                numberTwo.click();
                return this;
            case "3":
                numberThree.click();
                return this;
            case "4":
                numberFour.click();
                return this;
            case "5":
                numberFive.click();
                return this;
            case "6":
                numberSix.click();
                return this;
            case "7":
                numberSeven.click();
                return this;
            case "8":
                numberEight.click();
                return this;
            case "9":
                numberNine.click();
                return this;
        }
        return this;
    }

    public CalculatorPage action(String action) {
        switch (action) {
            case "+":
                add.click();
                break;
            case "-":
                subtract.click();
                break;
            case "*":
                multiply.click();
                break;
            case "/":
                divide.click();
                break;
        }
        return this;
    }

    public CalculatorPage add() {
        add.click();
        return this;
    }

    public CalculatorPage calculate() {
        calculate.click();
        return this;
    }

    public CalculatorPage displayValueCheck(String testData) {
        display.shouldHave(value(testData));
        return this;
    }
}
