package co.com.adnII.controllers;

import co.com.adnII.dto.PersonDTO;
import co.com.adnII.handlers.PersonResourceHandler;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class PersonController extends Controller {

    private HttpExecutionContext ec;
    private PersonResourceHandler handler;

    @Inject
    public PersonController(HttpExecutionContext ec, PersonResourceHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> list() {
        return handler.find().thenApplyAsync(posts -> {
            final List<PersonDTO> postList = posts.collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }

    public CompletionStage<Result> find(String id) {
        return handler.find(id).thenApplyAsync(optionalResource -> {
            return optionalResource.map(resource ->
                    ok(Json.toJson(resource))
            ).orElseGet(() ->
                    notFound()
            );
        }, ec.current());
    }

    public CompletionStage<Result> update(String id) {
        JsonNode json = request().body().asJson();
        PersonDTO resource = Json.fromJson(json, PersonDTO.class);
        return handler.update(id, resource).thenApplyAsync(optionalResource -> {
            return optionalResource.map(r ->
                    ok(Json.toJson(r))
            ).orElseGet(() ->
                    notFound()
            );
        }, ec.current());
    }

    public CompletionStage<Result> create() {
        JsonNode json = request().body().asJson();
        final PersonDTO resource = Json.fromJson(json, PersonDTO.class);
        return handler.create(resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
        }, ec.current());
    }


    public CompletionStage<Result> delete(String id) {
        return handler.delete(id).thenApplyAsync(deletedResource -> {
            return deletedResource ? noContent() : notFound();
        }, ec.current());
    }
}
