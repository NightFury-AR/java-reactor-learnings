package T04_Hot_n_Cold_pubs;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

// it doesn't wait for the delayed subscriber (e.g going late for movie theatre , you will miss the scenes)
public class C02_HotPublisher_01Share {
    public static void main(String[] args) {
        CustomSubscriber person_a = new CustomSubscriber("PERSON A");
        CustomSubscriber person_b = new CustomSubscriber("PERSON B");
        Flux<String> share = simulateGroupMessages()
                .share();

        share.subscribe(person_a);
        SubscriberUtil.freeze(3);
        share
                .subscribe(person_b);
        SubscriberUtil.freeze(10);
    }

    public static Flux<String> simulateGroupMessages() {
        return Flux.just(
                "message 1",
                "message 2",
                "message 3",
                "message 4",
                "message 5",
                "message 6"
        ).delayElements(Duration.ofSeconds(1))
                .share();
    }
}
