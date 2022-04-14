package ru.internet.sergeevss90;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class SimpleTests {

    @BeforeAll
    static void prepare() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.theonlinecalculator.com/";
        Configuration.browserSize = "1920x1080";
    }
    @DisplayName("Проверка сложения единиц")
    @Test
    void testAddition() {
        Selenide.open("");
        $(byName("one")).click();
        $(byName("add")).click();
        $(byName("one")).click();
        $(byName("calculate")).click();
        $("#display").shouldHave(value("2"));
    }

    @ValueSource(strings = {"2", "8"})

    @DisplayName("Проверка увеличения на единицу")
    @ParameterizedTest(name = "Проверка увеличения числа {0} на единицу")
    void valueSourceForm(String testData) {
        $("#display.window").sendKeys(testData);
        $(byName("add")).click();
        $(byName("one")).click();
        $(byName("calculate")).click();
        $("#display").shouldHave(value(String.valueOf(Integer.parseInt(testData) + 1)));
    }
    /*
    @CsvSource(value = {
            "Kirill| kirillkatkov@gmail.com | Address 1 | Address 2",
            "Ivan| ivanivanov@gmail.com | Address 3 | Address 4"
    },
            delimiter = '|'
    )


    @ParameterizedTest(name = "Заполнение формы с помощью CsvSource {0}")
    void csvSourceForm(String name, String email, String currentAddress, String permanentAddress) {
        textBoxPage.openPage().
                setFirstName(name).setUserEmail(email).setCurrentAddress(currentAddress).setPermanentAddress(permanentAddress)
                .submitClick();
        textBoxPage.checkResult("Name:" + name).checkResult("Email:" + email).checkResult("Current Address :" + currentAddress).checkResult("Permananet Address :" + permanentAddress);
    }

    @MethodSource("methodSourceTextBox")
    @ParameterizedTest(name = "Заполнение формы c помощью MethodSource {0}")
    void methodSourceExampleTest(String name, String email, String currentAddress, String permanentAddress) {
        textBoxPage.openPage().
                setFirstName(name).setUserEmail(email).setCurrentAddress(currentAddress).setPermanentAddress(permanentAddress)
                .submitClick();
    }

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
