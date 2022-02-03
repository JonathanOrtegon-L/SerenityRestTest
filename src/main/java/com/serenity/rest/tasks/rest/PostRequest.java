package com.serenity.rest.tasks.rest;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class PostRequest implements Task {

    String resource;
    Object requestBody;

    public PostRequest(String resource, Object requestBody) {
        this.resource = resource;
        this.requestBody = requestBody;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(resource)
                .with(request -> request.contentType(ContentType.JSON)
                        .body(requestBody))
        );
    }

    public static PostRequest withData(String resource, Object requestBody) {
        return Tasks.instrumented(PostRequest.class, resource, requestBody);
    }
}
