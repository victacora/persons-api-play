package co.com.adnII.handlers;

import com.palominolabs.http.url.UrlBuilder;
import co.com.adnII.dto.PersonDTO;
import co.com.adnII.model.entities.Person;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import co.com.adnII.model.repository.PersonRepository;

import javax.inject.Inject;
import java.nio.charset.CharacterCodingException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class PersonResourceHandler {

    private final PersonRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public PersonResourceHandler(PersonRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<Stream<PersonDTO>> find() {
        return repository.list().thenApplyAsync(PersonStream -> {
            return PersonStream.map(data -> new PersonDTO(data, link(data)));
        }, ec.current());
    }

    public CompletionStage<PersonDTO> create(PersonDTO resource) {
        final Person data = new Person(resource.getFirtsName(),resource.getSecondName(),resource.getLastName(),resource.getIdCardNumber(),resource.getPhoneNumber(), resource.getEmail());
        return repository.create(data).thenApplyAsync(savedData -> {
            return new PersonDTO(savedData, link(savedData));
        }, ec.current());
    }

    public CompletionStage<Optional<PersonDTO>> find(String id) {
        return repository.get(Long.parseLong(id)).thenApplyAsync(optionalData -> {
            return optionalData.map(data -> new PersonDTO(data, link(data)));
        }, ec.current());
    }

    public CompletionStage<Optional<PersonDTO>> update(String id, PersonDTO resource) {
        final Person data = new Person(resource.getFirtsName(),resource.getSecondName(),resource.getLastName(),resource.getIdCardNumber(),resource.getPhoneNumber(), resource.getEmail());
        return repository.update(Long.parseLong(id), data).thenApplyAsync(optionalData -> {
            return optionalData.map(op -> new PersonDTO(op, link(op)));
        }, ec.current());
    }

    public CompletionStage<Boolean> delete(String id) {
         return repository.delete(Long.parseLong(id)).thenApplyAsync(result -> {
            return result;
        }, ec.current());
    }

    private String link(Person data) {
        // Make a point of using request context here, even if it's a bit strange
        final Http.Request request = Http.Context.current().request();
        final String[] hostPort = request.host().split(":");
        String host = hostPort[0];
        int port = (hostPort.length == 2) ? Integer.parseInt(hostPort[1]) : -1;
        final String scheme = request.secure() ? "https" : "http";
        try {
            return UrlBuilder.forHost(scheme, host, port)
                    .pathSegments("api","v1", "persons", data.getId().toString())
                    .toUrlString();
        } catch (CharacterCodingException e) {
            throw new IllegalStateException(e);
        }
    }

}
