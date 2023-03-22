package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.util.Objects;

import static utils.SubscriberUtil.subscriber;

public class C02_DoHook {
    public static void main(String[] args) {
        Flux.range(1,10)
                .doFirst(() -> System.out.println("doFirst: starting ..."))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe :"+subscription.toString()))
                .doOnRequest(value -> System.out.println("doOnRequest: "+value))
                .doOnNext(integer -> System.out.println("doOnNext: "+integer))
                .doOnEach(integerSignal -> System.out.println("doOnEach: value is "+integerSignal.get()))
                .doOnComplete(() -> System.out.println("doOnComplete: completing..."))
                .doOnTerminate(() -> System.out.println("doOnTerminate: terminating ..."))
                .doFinally(signalType -> System.out.println("doFinally: "+signalType.toString()))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate: stream is terminated already..."))
                .doOnCancel(() -> System.out.println("doOnCancel: cancelling ..."))
                .doOnError(throwable -> System.out.println("doOnError: "+throwable.getMessage()))
                .doOnError(Objects::isNull,throwable -> System.out.println("doOnError: "+throwable.getLocalizedMessage()))
                .doOnError(NullPointerException.class,e -> System.out.println("doOnError: "+e.getMessage()))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscord: "+o.toString()))
                .take(5)
                .subscribe(subscriber());
    }
}
