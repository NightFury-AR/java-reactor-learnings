package T10_Sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

public class C08MulticastReplay {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().replay().all();
        Flux<Object> sinkFlux = sink.asFlux();
        sink.tryEmitNext("one");
        sink.tryEmitNext("two");
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("A"));
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("B"));
        sink.tryEmitNext("three");
        sinkFlux.subscribe(SubscriberUtil.normalSubscriber("C"));

    }
}
