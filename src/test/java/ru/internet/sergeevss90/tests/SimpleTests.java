package ru.internet.sergeevss90.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.internet.sergeevss90.data.EnumForNumbers;
import ru.internet.sergeevss90.data.ExpectedResultCalculator;
import ru.internet.sergeevss90.pages.CalculatorPage;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class SimpleTests {

    CalculatorPage calculatorPage = new CalculatorPage();
    ExpectedResultCalculator expectedResultCalculator = new ExpectedResultCalculator();
    String expectedData;

    @BeforeAll
    static void prepare() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.theonlinecalculator.com/";
        Configuration.browserSize = "1920x1080";
        Selenide.open("");
    }

    @DisplayName("Проверка сложения единиц (Simple@Test)")
    @Test
    void testAddition() {
        expectedData = expectedResultCalculator.getData("1");
        calculatorPage.useNumber("1")
                .add()
                .useNumber("1")
                .calculate()
                .displayValueCheck(expectedData);
    }

    @ValueSource(strings = {"2", "8"})
    @DisplayName("Проверка увеличения на единицу (ValueSource)")
    @ParameterizedTest(name = "Проверка увеличения числа {0} на единицу")
    void valueSourceTest(String testData) {
        expectedData = expectedResultCalculator.getData(testData);
        calculatorPage.useNumber(testData)
                .add()
                .useNumber("1")
                .calculate()
                .displayValueCheck(expectedData);
    }

    @CsvSource(value = {
            "2 | + | 2",
            "2 | - | 2",
            "2 | * | 2",
            "2 | / | 2"
    },
            delimiter = '|'
    )

    @ParameterizedTest(name = "Подсчитаем {0} {1} {2}")
    @DisplayName("Проверим базовые целочесленные операции (CsvSource)")
    void csvSourceTest(String firstNumber, String action, String secondNumber) {
        expectedData = expectedResultCalculator.getData(firstNumber, action, secondNumber);
        calculatorPage.useNumber(firstNumber)
                .action(action)
                .useNumber(secondNumber)
                .calculate()
                .displayValueCheck(expectedData);
    }

    static Stream<Arguments> methodSourceTest() {
        return Stream.of(
                Arguments.of("3", "+", "3"),
                Arguments.of("3", "-", "3"),
                Arguments.of("3", "*", "3"),
                Arguments.of("3", "/", "3")
        );
    }

    @DisplayName("Проверим базовые целочесленные операции (MethodSource)")
    @MethodSource("methodSourceTest")
    @ParameterizedTest(name = "Подсчитаем {0} {1} {2}")
    void methodSourceTest(String firstNumber, String action, String secondNumber) {
        expectedData = expectedResultCalculator.getData(firstNumber, action, secondNumber);
        calculatorPage.useNumber(firstNumber)
                .action(action)
                .useNumber(secondNumber)
                .calculate()
                .displayValueCheck(expectedData);
    }

    @DisplayName("Проверка квадратов (EnumSource)")
    @EnumSource(EnumForNumbers.class)
    @ParameterizedTest(name = "Проверка квадрата {0}")
    void enumSourceTest(EnumForNumbers testData) {
        expectedData = expectedResultCalculator.getData(testData.number, "*");
        calculatorPage.useNumber(testData.number)
                .action("*")
                .useNumber(testData.number)
                .calculate()
                .displayValueCheck(expectedData);
    }

    @DisplayName("Повторение - мать учения")
    @RepeatedTest (value = 6, name = "Прогон {currentRepetition} из {totalRepetitions}")
    void multiply() {
        expectedData = expectedResultCalculator.getData("6", "*");
        calculatorPage.useNumber("6")
                .action("*")
                .useNumber("6")
                .calculate()
                .displayValueCheck(expectedData);
    }
    @AfterEach
    void clearResult() {
        $(byName("clearButton")).click();
    }
}
