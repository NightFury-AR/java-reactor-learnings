package T01_Mono;

import reactor.core.publisher.Mono;
import utils.SubscriberUtil;

public class C01_MonoExample {
    public static void main(String[] args) {
        //1. creating mono in simple way (lot other ways there in Mono)
        Mono<String> simpleMono = Mono.just("Hello World");

        //a.dummy subscribe
        simpleMono.subscribe();

        //b. with consumer
        System.out.println("### NORMAL_CONSUMER");
        simpleMono.subscribe(s -> System.out.println(" subscribing from normal consumer "+s));

        //c.with consumer , error , complete
        System.out.println("### CONSUMER_WITH_REQUEST_ERROR_COMPLETE");
        simpleMono.subscribe(
                item -> System.out.println("item "+Integer.parseInt(item)),
                err -> System.out.println(err.toString()),
                () -> System.out.println("completed")
        );

        //some refactors
        System.out.println("SUBSCRIBING ...");
        simpleMono.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()
        );
    }
}
