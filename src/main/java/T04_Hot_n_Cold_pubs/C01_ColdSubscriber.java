package T04_Hot_n_Cold_pubs;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

//no matter what each subscriber will get data from start
public class C01_ColdSubscriber {
    public static void main(String[] args) {
        simulateGroupMessages()
                .subscribeWith(new CustomSubscriber("Person 1"));
        SubscriberUtil.freeze(5);
        simulateGroupMessages()
                .subscribeWith(new CustomSubscriber("Person 2"));
        SubscriberUtil.freeze(60);
    }

    public static Flux<String> simulateGroupMessages() {
        return Flux.just(
                "message 1",
                "message 2",
                "message 3",
                "message 4",
                "message 5",
                "message 6"
        ).delayElements(Duration.ofSeconds(2));
    }

}
