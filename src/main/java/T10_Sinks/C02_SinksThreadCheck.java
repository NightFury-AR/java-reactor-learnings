package T10_Sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class C02_SinksThreadCheck {
    public static void main(String[] args) {
        Sinks.Many<Object> sinkMany = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> sinkFlux = sinkMany.asFlux();
        List<Object> list = new ArrayList<>();
        sinkFlux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> sinkMany.emitNext(j,(signalType, emitResult) -> true));
        }
        SubscriberUtil.freeze(10);
        System.out.println("list is "+list.size());
    }
}
