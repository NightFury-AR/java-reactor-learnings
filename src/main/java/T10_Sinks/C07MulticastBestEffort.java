package T10_Sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

import java.time.Duration;

public class C07MulticastBestEffort {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        Flux<Object> sinkFlux = sink.asFlux();
        sink.tryEmitNext("one");
        sink.tryEmitNext("two");
        sinkFlux.delayElements(Duration.ofMillis(600)).subscribe(SubscriberUtil.normalSubscriber("A"));
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("B"));
        sink.tryEmitNext("three");
        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }
        SubscriberUtil.freeze(10);
    }
}
