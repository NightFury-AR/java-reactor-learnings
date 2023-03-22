package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

import static utils.SubscriberUtil.freeze;
import static utils.SubscriberUtil.subscriber;

public class C06_OnTimeOut {
    public static void main(String[] args) {
        getOrderNumbers()
                .timeout(Duration.ofSeconds(3))
                        .subscribe(subscriber());

        /*getOrderNumbers()
                .timeout(Duration.ofSeconds(3),fallBack())
                        .subscribe(subscriber());*/


        freeze(60);
    }
    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1,100)
                .delayElements(Duration.ofSeconds(5));
    }

    private static Flux<Integer> fallBack() {
        return Flux.range(-4,5)
                .delayElements(Duration.ofSeconds(1));
    }
}
