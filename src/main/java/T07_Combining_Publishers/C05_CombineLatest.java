package T07_Combining_Publishers;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

public class C05_CombineLatest {

    public static void main(String[] args) {
        Flux.combineLatest(getAlphas(),getNumeric(), (s, integer) -> s+integer)
                .subscribe(SubscriberUtil.normalSubscriber("AlphaNumeric"));

        SubscriberUtil.freeze(6);
    }

    private static Flux<String> getAlphas() {
        return Flux.just("A","B","C","D")
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumeric() {
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(1));
    }
}
