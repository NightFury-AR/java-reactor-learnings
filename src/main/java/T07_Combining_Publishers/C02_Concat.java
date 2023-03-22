package T07_Combining_Publishers;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

public class C02_Concat {
    public static void main(String[] args) {
        Flux<String> A = Flux.just("A", "B", "C", "D");
        Flux<String> B = Flux.just("E", "F", "G", "H");
        Flux<Object> C = Flux.error(new RuntimeException("errrorrrr"));
        Flux<Object> concat = Flux.concat(A, B, C);
        concat.subscribe(SubscriberUtil.normalSubscriber());
        Flux<Object> concat1 = Flux.concat(A, C, B);
        concat1.subscribe(SubscriberUtil.normalSubscriber());//here B will be omitted because of error
        Flux<Object> concat2 = Flux.concatDelayError(A, C, B);// here error will always be at the end
        concat2.subscribe(SubscriberUtil.normalSubscriber());
    }
}
