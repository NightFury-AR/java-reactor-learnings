package T03_Operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utils.SubscriberUtil;

import static utils.SubscriberUtil.subscriber;

public class C05_OnError {
    public static void main(String[] args) {
        // onErrorReturn
        Flux.range(1,10)
                .log()
                .map(integer -> {
                    if (integer == 3) {
                        throw new IllegalArgumentException();
                    }
                    return integer;
                })
                .onErrorReturn(0)
                .subscribe(subscriber());

        //onErrorResume
        Flux.range(1,5)
                .log()
                .map(integer -> {
                    if (integer == 3) {throw new IllegalArgumentException();}
                    return integer;
                })
                .onErrorResume(e -> Mono.fromSupplier(() -> 5))
                .subscribe(subscriber());
        //onError
        Flux.range(1,5)
                .log()
                .map(integer -> {
                    if (integer == 3) {throw new IllegalArgumentException();}
                    return integer;
                })
                .onErrorContinue((throwable, o) -> {
                    Integer i = (Integer) o;
                    System.out.println("Error happened on : "+i);
                })
                .subscribe(subscriber());
    }
}
