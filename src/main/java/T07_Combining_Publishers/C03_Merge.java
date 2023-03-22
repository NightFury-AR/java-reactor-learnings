package T07_Combining_Publishers;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

import static utils.SubscriberUtil.normalSubscriber;

// it will act like shuffle
public class C03_Merge {
    public static void main(String[] args) {
        Flux<String> flights = Flux.merge(getEmiratesFlights(), getQatarFlights(), getIndianFlights());
        flights.subscribe(normalSubscriber());
        SubscriberUtil.freeze(5);
    }

    private static Flux<String> getQatarFlights() {
        return Flux.just(
                "Q:A","Q:B","Q:C"
        ).delayElements(Duration.ofSeconds(1));
    }
    private static Flux<String> getEmiratesFlights() {
        return Flux.just(
                "E:A","E:B","E:C"
        ).delayElements(Duration.ofSeconds(1));
    }
    private static Flux<String> getIndianFlights() {
        return Flux.just(
                "I:A","I:B","I:C"
        ).delayElements(Duration.ofSeconds(1));
    }

}
