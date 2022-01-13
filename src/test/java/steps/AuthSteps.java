package steps;

import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.PaymentPage;
import tasks.LoginToEriBank;
import tasks.PaymentTo;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class AuthSteps {
    public static int balance;

    @Managed(driver = "Appium")

    public WebDriver herMobileDevice;

    String actorName = "sedat";
    Actor actor = Actor.named(actorName);

    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("Open browser and homepage screen")
    public void open_browser_and_homepage_screen() {
        actor.can(BrowseTheWeb.with(herMobileDevice));

    }

    @When("Login with company username {string} and {string}")
    public void login_with_company_username_and(String username, String password) {
        actor.attemptsTo(LoginToEriBank.login(username, password));

    }

    @When("Make {string} with the values {string},{int},{string}")
    public void make_with_the_values(String phone, String name, Integer payments, String country) {
        actor.attemptsTo(PaymentTo.info(phone, name, payments, country));
        balance = 100;
        balance = balance - payments;
    }

    @Then("Should see that log out page")
    public void should_see_that_log_out_page() throws InterruptedException {
        Thread.sleep(1500);
        actor.should(
                GivenWhenThen.seeThat(the(PaymentPage.getLastBalance(balance))
                        , containsText(String.valueOf(balance))));
    }


}
