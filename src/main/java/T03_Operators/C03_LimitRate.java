package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import static utils.SubscriberUtil.subscriber;

public class C03_LimitRate {
    public static void main(String[] args) {
        Flux.range(1,100)
                .log()
                .limitRate(10)
                .subscribe(subscriber());

        Flux.range(1,100)
                .log()
                .limitRate(10,5)
                .subscribe(subscriber());
    }
}
