package T05_Threading_n_Schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import utils.SubscriberUtil;

public class C03_SubscribeOn2 {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            printThreadName("Flux initializing...");
            fluxSink.next(1);
        })
        .subscribeOn(Schedulers.newParallel("p-2"))
        .doOnNext(o -> printThreadName(" do on next " + o))
        .doFirst(() -> printThreadName(" do first"))
        .subscribeOn(Schedulers.newParallel("p-1"))
        .doFirst(() -> printThreadName(" starting program ..."))
        .subscribe(o -> printThreadName(" subscriber "+o));

        SubscriberUtil.freeze(5);
    }

    private static void printThreadName(String module) {
        System.out.println(" Thread "+Thread.currentThread().getName()+" executing "+module);
    }
}
