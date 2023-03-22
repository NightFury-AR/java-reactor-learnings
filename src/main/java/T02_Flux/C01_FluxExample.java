package T02_Flux;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.util.stream.Stream;

import static utils.SubscriberUtil.*;

public class C01_FluxExample {
    public static void main(String[] args) {
        Flux.just(Stream.of("1","2","3"));
        // need to revisit flux methods
        Flux<Integer> integerFlux = Flux.fromStream(Stream.of(1,2,3));
        integerFlux.subscribe(onNext(), onError(), onComplete());
    }
}
