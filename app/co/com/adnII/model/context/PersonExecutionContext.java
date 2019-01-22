package co.com.adnII.model.context;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class PersonExecutionContext extends CustomExecutionContext {

    @Inject
    public PersonExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "person.repository");
    }
}

