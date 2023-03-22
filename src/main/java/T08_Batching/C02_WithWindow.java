package T08_Batching;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class C02_WithWindow {
    public static void main(String[] args) {
        //simple example
        /*getNums()
                .window(5)
                .doOnNext(integerFlux -> integerFlux.collectList().subscribe(SubscriberUtil.normalSubscriber("window")))
                .subscribe();*/

        //fraud detection
        Flux<Integer> c1 = Flux.just(101, 102, 103, 104, 105, 106,102).delayElements(Duration.ofMillis(500));
        Flux<Integer> c2 = Flux.just(107, 108, 109, 110, 111, 112, 113, 114,102).delayElements(Duration.ofMillis(600));

    }

    private static Flux<Integer> getNums() {
        return Flux.range(1,10);
    }


}
