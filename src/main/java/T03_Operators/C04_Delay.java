package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

import static utils.SubscriberUtil.freeze;
import static utils.SubscriberUtil.subscriber;

public class C04_Delay {
    public static void main(String[] args) {
        int count = 10;
        int delaySec = 5;
        Flux.range(1,count)
                .log()
                .delayElements(Duration.ofSeconds(2))
                .subscribe(subscriber());
        freeze(count*delaySec + 2);
    }
}
