package T05_Threading_n_Schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import utils.SubscriberUtil;

public class C02_SubscribeOn {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThread("flux started");
            fluxSink.next(1);
        }).doOnNext(o -> printThread("" + o));

        flux.doFirst(() -> printThread("first"))
                        .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThread("first after"))
                .subscribe(o -> printThread(o+""));
        SubscriberUtil.freeze(5);
    }
    public static void printThread(String module) {
        System.out.println("Thread "+Thread.currentThread().getName() + " running from "+module);
    }
}
