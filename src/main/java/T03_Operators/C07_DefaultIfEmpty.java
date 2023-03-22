package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import static utils.SubscriberUtil.subscriber;

public class C07_DefaultIfEmpty {
    public static void main(String[] args) {
        getOrders()
                .filter(integer -> integer > 22)
                .defaultIfEmpty(-100)
                .subscribe(subscriber());
    }
    private static Flux<Integer> getOrders() {
        return Flux.range(1,20);
    }
}
