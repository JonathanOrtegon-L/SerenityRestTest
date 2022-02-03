package com.serenity.rest.tasks.rest;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetRequest implements Task {

    String resource;

    public GetRequest(String resource) {
        this.resource = resource;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
        );
    }

    public static GetRequest withResource(String resource) {
        return Tasks.instrumented(GetRequest.class, resource);
    }
}
