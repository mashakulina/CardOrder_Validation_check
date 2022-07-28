package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestInvalid {

    @Test
    void SubmittingAnEmptyForm() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void souldTestEngName() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Ivan");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void souldTestNameWithNumbers() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("111111");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void souldTestWithPhoneNumber8() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("89672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void souldTestWithPhoneNumber10numbers() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("+7967269950");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void souldTestPhoneNumberWithLetters() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("Иван");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    

}
