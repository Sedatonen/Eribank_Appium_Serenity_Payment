package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import pages.HomePage;
import pages.PaymentPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class PaymentTo implements Task {

    private String phone;
    private String name;
    private String country;
    private Integer payments;


    public PaymentTo(String phone, String name, Integer payments, String country) {

        this.phone = phone;
        this.name = name;
        this.payments = payments;
        this.country = country;

    }

    @Override
    @Step("{0} logins to the eribank")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.PAYMENT_BTN, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(HomePage.PAYMENT_BTN),
                WaitUntil.the(PaymentPage.COUNTRY_TEXT, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(PaymentPage.PHONE_TEXT),
                SendKeys.of(this.phone).into(PaymentPage.PHONE_TEXT),
                Click.on(PaymentPage.NAME_TEXT),
                SendKeys.of(this.name).into(PaymentPage.NAME_TEXT),
                Click.on(PaymentPage.AMOUNT),
                SendKeys.of(String.valueOf(this.payments)).into(PaymentPage.AMOUNT),
                Click.on(PaymentPage.COUNTRY_TEXT),
                SendKeys.of(this.country).into(PaymentPage.COUNTRY_TEXT),
                Click.on(PaymentPage.SEND_PAYMENT_BTN),
                WaitUntil.the(PaymentPage.SENDPAYMENTYES_BUTTON, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(PaymentPage.SENDPAYMENTYES_BUTTON)
        );

    }


    public static PaymentTo info(String phone, String name, Integer payments, String country) {
        return instrumented(PaymentTo.class, phone, name, payments, country);
    }
}
