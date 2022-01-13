package pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {
    public static Target PAYMENT_BTN = Target.the("make payment button")
            .located(By.id("makePaymentButton"));


    public static Target FIRST_BALANCE = Target.the("first balance text")
            .located(By.className("android.view.View"));
}
