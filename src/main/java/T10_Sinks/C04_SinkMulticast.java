package T10_Sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

// 1: many subs
public class C04_SinkMulticast {
    public static void main(String[] args) {
        Sinks.Many<Object> sinkMany = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> sinkFlux = sinkMany.asFlux();
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("s1"));
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("s2"));

        sinkMany.tryEmitNext("one");
        sinkMany.tryEmitNext("two");
        sinkMany.tryEmitNext("three");

    }
}
