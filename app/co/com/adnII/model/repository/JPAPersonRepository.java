package co.com.adnII.model.repository;

import co.com.adnII.model.context.PersonExecutionContext;
import co.com.adnII.model.entities.Person;

import play.db.jpa.JPAApi;

import javax.inject.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;


import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPAPersonRepository implements PersonRepository {

    private final JPAApi jpaApi;
    private final PersonExecutionContext ec;
    private final CircuitBreaker circuitBreaker = new CircuitBreaker().withFailureThreshold(1).withSuccessThreshold(3);

    @Inject
    public JPAPersonRepository(JPAApi api, PersonExecutionContext ec) {
        this.jpaApi = api;
        this.ec = ec;
    }

    @Override
    public CompletionStage<Stream<Person>> list() {
        return supplyAsync(() -> wrap(em -> select(em)), ec);
    }

    @Override
    public CompletionStage<Person> create(Person person) {
        return supplyAsync(() -> wrap(em -> insert(em, person)), ec);
    }

    @Override
    public CompletionStage<Optional<Person>> get(Long id) {
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> lookup(em, id))), ec);
    }

    @Override
    public CompletionStage<Optional<Person>> update(Long id, Person person) {
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> modify(em, id, person))), ec);
    }

    @Override
    public CompletionStage<Boolean> delete(Long id) {
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> delete(em, id))), ec);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Optional<Person> lookup(EntityManager em, Long id) {
        return Optional.ofNullable(em.find(Person.class, id));
    }

    private Stream<Person> select(EntityManager em) {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList().stream();
    }

    private Optional<Person> modify(EntityManager em, Long id, Person person) throws InterruptedException {
        final Person data = em.find(Person.class, id);
        if (data != null) {
            data.setFirtsName( person.getFirtsName());
            data.setSecondName(person.getSecondName());
            data.setLastName(person.getLastName());
            data.setIdCardNumber(person.getIdCardNumber());
            data.setPhoneNumber(person.getPhoneNumber());
            data.setEmail(person.getEmail());
        }
        return Optional.ofNullable(data);
    }

    private Person insert(EntityManager em, Person Person) {
        return em.merge(Person);
    }

    private Boolean delete(EntityManager em, Long id) {
        final Person data = em.find(Person.class, id);
        if (data != null) {
            try {
                em.remove(data);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
}
