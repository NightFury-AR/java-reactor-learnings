package T06_Overflow_strategies;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

public class C01_OverflowBuffer {
    public static void main(String[] args) {
        Flux.range(1,500)
                .buffer(5)
                .doOnNext(integer -> System.out.println("pushed -> "+integer))
                .subscribe(integers -> System.out.println("receiving " +integers));
        SubscriberUtil.freeze(5);
    }
}
