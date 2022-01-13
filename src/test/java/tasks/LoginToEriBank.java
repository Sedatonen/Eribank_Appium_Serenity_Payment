package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.thucydides.core.annotations.Step;
import pages.LoginPage;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginToEriBank implements Task {
    private String username;
    private String password;


    public LoginToEriBank(String username, String password) {

        this.username = username;
        this.password = password;
    }


    @Override
    @Step("{0} logins to the eribank")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LoginPage.USERNAME_FIELD),
                SendKeys.of(this.username).into(LoginPage.USERNAME_FIELD),
                Click.on(LoginPage.PASSWORD_FIELD),
                SendKeys.of(this.password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BTN)
        );


    }


    public static LoginToEriBank login(String username, String password) {
        return instrumented(LoginToEriBank.class, username, password);
    }


}
