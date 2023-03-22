package T10_Sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

// can have only one subscriber
public class C03_SinkUniCast {
    public static void main(String[] args) {
        Sinks.Many<Object> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> unicastFlux = unicastSink.asFlux();
        unicastFlux
                .doOnComplete(() -> System.out.println("completed..."))
                .subscribe(SubscriberUtil.normalSubscriber("unicast sink"));

        unicastFlux.subscribe(SubscriberUtil.normalSubscriber("theMiddleMan"));

        unicastSink.tryEmitNext("one");
        unicastSink.tryEmitNext("two");
        unicastSink.tryEmitNext("three");
    }
}
