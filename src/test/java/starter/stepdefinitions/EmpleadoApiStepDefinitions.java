package starter.stepdefinitions;

import com.serenity.rest.tasks.rest.GetRequest;
import com.serenity.rest.tasks.web.OpenEmail;
import com.serenity.rest.utils.DtoConsultaEmpleados.ConsultaEmpleadoDetalleDto;
import com.serenity.rest.webdriver.LocalChromeDriverHeadless;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.core.annotations.events.AfterScenario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class EmpleadoApiStepDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoApiStepDefinitions.class);

    Actor actor = Actor.named("Angie");
    EnvironmentVariables environmentVariables;

    @Before
    public void setup() {
        OnStage.setTheStage(Cast.whereEveryoneCan(
                CallAnApi.at(""))
        );
        actor.entersTheScene();

        actor.whoCan(CallAnApi.at(environmentVariables.getProperty("baseUrl")));
        RestAssured.baseURI = environmentVariables.getProperty("baseUrl");
    }

    @After
    public void finish() {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleados")));
    }

    @AfterScenario
    public void finishScenario(Scenario scenario) {
        Collection<String> tags = scenario.getSourceTagNames();
        String headless = tags.stream().filter(x -> x.contains("headless"))
                .findFirst()
                .orElse("No contiene un tag con la palabra day");
        if (tags.contains("headless")) {
            try {
                LocalChromeDriverHeadless.closeProcess();
            } catch (Exception e) {
                logger.info("El driver headless no fue iniciado");
            }
        }
    }

    @When("ingresa al correo electr??nico y da clic en {string}")
    public void goToEmail(String locator) {
        String email = "testEmail@mailinator.com";
        actor.attemptsTo(
                OpenEmail.with(email,locator)
        );
    }

    @When("un tester consulta empleados")
    public void un_tester_consulta_empleados() {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleados")));
    }

    @Then("puede recuperar la informaci??n de los empleados")
    public void puede_recuperar_la_informaci??n_de_los_empleados() {
        actor.should(seeThatResponse("Ver el c??digo de respuesta",
                response -> response.statusCode(200)));
        ConsultaEmpleadoDetalleDto[] listaEmpleados = SerenityRest.lastResponse()
                .jsonPath().getObject("data", ConsultaEmpleadoDetalleDto[].class);
        /**
         * Se buscan empleados mayores de 30 a??os y se imprimen
         * con el nombre
         */
        HashMap<String, Integer> mapEmpleados = new HashMap<>();
        for (int i = 0; i < listaEmpleados.length; i++) {
            assertThat(listaEmpleados[i]).hasNoNullFieldsOrProperties();
            mapEmpleados.put(listaEmpleados[i].getEmployee_name(),
                    listaEmpleados[i].getEmployee_age());
        }
        System.out.println("Empleados mayores de 30 a??os: " + mapEmpleados);
    }

    @When("un tester consulta un empleado {string}")
    public void un_tester_consulta_un_empleado(String id) {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleado" + id)));
    }

    @Then("puede recuperar la informaci??n del empleado")
    public void puede_recuperar_la_informaci??n_del_empleado() {
        actor.should(seeThatResponse("Ver el c??digo de respuesta",
                response -> response.statusCode(200)));
    }

}
