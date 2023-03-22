package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.util.Objects;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class C10_SwitchOnFirst {
    //need more understanding
    public static void main(String[] args) {
        getPerson()
                .switchOnFirst((signal, personFlux) -> signal.isOnNext() && requireNonNull(signal.get()).getAge() < 10 ? personFlux : transformPerson().apply(personFlux))
                .subscribe(SubscriberUtil.subscriber());
    }

    private static Flux<Person> getPerson() {
        return Flux.range(1,10)
                .map(integer -> new Person());
    }
    private static Function<Flux<Person>,Flux<Person>> transformPerson() {
        return personFlux -> personFlux
                .filter(person -> person.getAge() > 20)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class,person -> System.out.println("Rejected person :"+person.toString()));
    }
}
