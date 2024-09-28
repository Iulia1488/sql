package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement pass = $("[data-test-id=password] input");
    private SelenideElement buttonEnter = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void errorNotificationVisible() {
        errorNotification.shouldBe(visible);
    }

    public void findErrorMessage(String expectedText) {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text(expectedText));
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        buttonEnter.click();
        return new VerificationPage();
    }

    public void notValidPass(DataHelper.AuthInfo info) {
        for (int i = 0; i <= 3; i++) {
            login.setValue(info.getLogin());
            pass.setValue(info.getPassword());
            buttonEnter.click();
            errorNotificationVisible();
            if (i <= 2) {
                login.doubleClick().sendKeys(Keys.BACK_SPACE);
                pass.doubleClick().sendKeys(Keys.BACK_SPACE);
            }
        }
    }
}