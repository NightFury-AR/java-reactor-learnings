package T06_Overflow_strategies;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import utils.SubscriberUtil;

public class C03_Overflow_Latest {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small","16");

        Flux.create(fluxSink -> {
                    for (int i = 0; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed "+i);
                        SubscriberUtil.sleep(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> SubscriberUtil.sleep(10))
                .subscribe(SubscriberUtil.normalSubscriber());
        SubscriberUtil.freeze(10);
    }
}
