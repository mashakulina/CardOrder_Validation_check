package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestValidation {

    @Test
    void shouldTestAll() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $("#root > div > div > p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void souldTestNameWithHyphen() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван-Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $("#root > div > div > p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
}

    @Test
    void souldTestSurnameWithHyphen() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван Иванов-Сидоров");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $("#root > div > div > p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void souldTestNameAndSurnameWithHyphen() {
        open("http://localhost:7777");
        SelenideElement form = $("#root > div > form");
        form.$("[data-test-id=name] input").setValue("Иван-Иван Иванов-Сидоров");
        form.$("[data-test-id=phone] input").setValue("+79672699504");
        form.$("[data-test-id=agreement]").click();
        form.$("#root > div > form > div:nth-child(4) > button").click();
        $("#root > div > div > p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
