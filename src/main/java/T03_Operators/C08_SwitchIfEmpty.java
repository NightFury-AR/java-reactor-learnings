package T03_Operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utils.SubscriberUtil;

public class C08_SwitchIfEmpty {
    public static void main(String[] args) {
        getPerson()
                .filter(person -> person.getAge()>100)
                .switchIfEmpty(fallBack())
                .subscribe(SubscriberUtil.subscriber());
    }

    private static Flux<Person> getPerson() {
        return Flux.range(1,10)
                .map(integer -> new Person());
    }

    private static Flux<Person> fallBack() {
        return Flux.range(1,100)
                .map(i -> new Person());
    }
}
