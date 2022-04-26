package com.serenity.rest.tasks.web;

import com.serenity.rest.webdriver.LocalChromeDriverHeadless;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenEmail implements Task {
    private final String email;
    private final String locator;

    public OpenEmail(String email, String locator) {
        this.email = email;
        this.locator = locator;
    }

    @Override
    @Step("{0} ingresa al email y abre el correo electronico de #nameEmail")
    public <T extends Actor> void performAs(T actor) {
        actor.can(BrowseTheWeb.with(LocalChromeDriverHeadless.web().inToUrl("https://www.mailinator.com/")));

        actor.attemptsTo(
                SendKeys.of(email).into(Target.the("Box ingresar email").located(By.id("search"))),
                Click.on(Target.the("Botón que permite el ingreso al correo").locatedBy(locator)));
    }

    public static OpenEmail with(String email, String locator) {
        return instrumented(OpenEmail.class, email, locator);
    }
}
