package T10_Sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

public class C05_MulticastBuffer {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> sinkFlux = sink.asFlux();

        //ex - 1
        //when there is already a subscriber consumed some items, then new subscriber comes then new subscriber
        // will only receive latest item
        sink.tryEmitNext("one");
        sink.tryEmitNext("two");
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("A"));
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("B"));
        sink.tryEmitNext("three");
    }
}
