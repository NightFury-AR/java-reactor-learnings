package T09_RepeatAndRetry;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

// repeat will repeat the subscription + given nums
//TODO:: repeatWhen
public class C01_Repeat {
    public static void main(String[] args) {
        Flux.just("A","B","C","D")
                .doOnComplete(() -> System.out.println("completed..."))
                .repeat(2)
                .subscribe(SubscriberUtil.normalSubscriber("repeat"));
    }

}
