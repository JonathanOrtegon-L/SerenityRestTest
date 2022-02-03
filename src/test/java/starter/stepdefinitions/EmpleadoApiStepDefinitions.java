package starter.stepdefinitions;

import com.serenity.rest.tasks.rest.GetRequest;
import com.serenity.rest.utils.DtoConsultaEmpleados.ConsultaEmpleadoDetalleDto;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.HashMap;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class EmpleadoApiStepDefinitions {

    Actor actor = Actor.named("Angie");
    EnvironmentVariables environmentVariables;

    @Before
    public void setup() {
        actor.whoCan(CallAnApi.at(environmentVariables.getProperty("baseUrl")));
        RestAssured.baseURI = environmentVariables.getProperty("baseUrl");
    }

    @After
    public void finish() {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleados")));
    }

    @When("un tester consulta empleados")
    public void un_tester_consulta_empleados() {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleados")));
    }

    @Then("puede recuperar la información de los empleados")
    public void puede_recuperar_la_información_de_los_empleados() {
        actor.should(seeThatResponse("Ver el código de respuesta",
                response -> response.statusCode(200)));
        ConsultaEmpleadoDetalleDto[] listaEmpleados = SerenityRest.lastResponse()
                .jsonPath().getObject("data", ConsultaEmpleadoDetalleDto[].class);
        /**
         * Se buscan empleados mayores de 30 años y se imprimen
         * con el nombre
         */
        HashMap<String, Integer> mapEmpleados = new HashMap<>();
        for (int i = 0; i < listaEmpleados.length; i++) {
            assertThat(listaEmpleados[i]).hasNoNullFieldsOrProperties();
            mapEmpleados.put(listaEmpleados[i].getEmployee_name(),
                    listaEmpleados[i].getEmployee_age());
        }
        System.out.println("Empleados mayores de 30 años: " + mapEmpleados);
    }

    @When("un tester consulta un empleado {string}")
    public void un_tester_consulta_un_empleado(String id) {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleado" + id)));
    }

    @Then("puede recuperar la información del empleado")
    public void puede_recuperar_la_información_del_empleado() {
        actor.should(seeThatResponse("Ver el código de respuesta",
                response -> response.statusCode(200)));
    }
}
