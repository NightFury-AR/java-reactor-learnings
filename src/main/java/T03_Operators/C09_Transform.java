package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.util.function.Function;

public class C09_Transform {

    public static void main(String[] args) {
        getPerson()
                .transform(transformPerson())
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
