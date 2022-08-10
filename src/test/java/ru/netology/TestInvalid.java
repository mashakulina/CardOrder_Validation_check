package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestInvalid {


    @Test
    void submittingAFormWithAnEmptyNameString() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void submittingAFormWithAnEmptyPhoneString() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void submittingAFormWithoutCheckBox() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("button").click();
        $("[data-test-id='agreement']").shouldHave(Condition.cssClass("input_invalid"));
    }

    @Test
    void shouldTestEngName() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Ivan");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNameWithNumbers() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("111111");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestPhoneNumber8() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("89672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestPhoneNumber10numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("+7967269950");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestPhoneNumberWithLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("Иван");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
