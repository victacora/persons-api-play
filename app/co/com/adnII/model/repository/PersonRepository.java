package co.com.adnII.model.repository;

import co.com.adnII.model.entities.Person;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface PersonRepository {

    CompletionStage<Stream<Person>> list();

    CompletionStage<Person> create(Person person);

    CompletionStage<Optional<Person>> get(Long id);

    CompletionStage<Optional<Person>> update(Long id, Person person);

    CompletionStage<Boolean> delete(Long id);
}
