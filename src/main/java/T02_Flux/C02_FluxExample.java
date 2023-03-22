package T02_Flux;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import static utils.SubscriberUtil.*;

public class C02_FluxExample {
    public static void main(String[] args) {
        //1.Just
        System.out.println("===USING_JUST===");
        Flux<String> usingJust = Flux.just("A", "B", "C", "D");
        usingJust.subscribe(onNext(),onError(),onComplete());

        //2.from array
        System.out.println("===USING_ARRAY===");
        Flux<Integer> usingArray = Flux.fromArray(new Integer[]{1, 2, 3});
        usingArray.subscribe(onNext(),onError(),onComplete());

        //3. from stream
        System.out.println("===USING_STREAM===");
        Flux<Integer> usingStream = Flux.fromStream(Stream.of(1, 2, 3));
        usingStream.subscribe(onNext(),onError(),onComplete());

        //4.using range
        System.out.println("===USING_RANGE===");
        Flux<Integer> usingRange = Flux.range(1, 5);
        usingRange.subscribe(onNext(),onError(),onComplete());

        //log
        /*usingJust.log()
                .subscribe(onNext());*/

        // using custom subscribe
        System.out.println("===USING_CUSTOM_SUBSCRIBER===");
        Flux.just(1,2,3,4)
                .subscribeWith(new C03_Subscriber())
                .onNext(4);

        // using interval
        System.out.println("===USING_INTERVAL===");
        Flux.interval(Duration.ofSeconds(1))
                .filter(second -> second.intValue() <= 3)
                .subscribe(onNext(),onError(),onComplete());

        // using delay
        System.out.println("===USING_DELAY===");
        Flux.just("AA","BB","CC")
                        .delayElements(Duration.ofSeconds(1))
                                .subscribe(onNext(),onError(),onComplete());

        freeze(14);

    }
}
