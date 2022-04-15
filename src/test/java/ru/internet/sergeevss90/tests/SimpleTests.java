package ru.internet.sergeevss90.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
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
    void csvSourceForm(String firstNumber, String action, String secondNumber) {
        expectedData = expectedResultCalculator.getData(firstNumber, action, secondNumber);
        calculatorPage.useNumber(firstNumber)
                .action(action)
                .useNumber(secondNumber)
                .calculate()
                .displayValueCheck(expectedData);
    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of("3", "+", "3"),
                Arguments.of("3", "-", "3"),
                Arguments.of("3", "*", "3"),
                Arguments.of("3", "/", "3")
        );
    }

    @DisplayName("Проверим базовые целочесленные операции (MethodSource)")
    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest(name = "Подсчитаем {0} {1} {2}")
    void methodSourceExampleTest(String firstNumber, String action, String secondNumber) {
        expectedData = expectedResultCalculator.getData(firstNumber, action, secondNumber);
        calculatorPage.useNumber(firstNumber)
                .action(action)
                .useNumber(secondNumber)
                .calculate()
                .displayValueCheck(expectedData);
    }
    /*
    @EnumSource(TitleEnum.class)
    @ParameterizedTest(name = "Проверка с помощью Enum")
    void enumSourceTest(TitleEnum testData) {
        textBoxPage.openPage().userFormTitle(testData.rusName);
        System.out.println(testData.rusName);
        Assertions.assertEquals("Text Box", testData.rusName);
    }
    */

    @AfterEach
    void clearResult() {
        $(byName("clearButton")).click();
    }
}
