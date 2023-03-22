package T04_Hot_n_Cold_pubs;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;
import java.util.stream.Stream;

public class C04_HotPublisher_RefCount2 {
    public static void main(String[] args) {
        CustomSubscriber member1 = new CustomSubscriber("Member 1");
        CustomSubscriber member2 = new CustomSubscriber("Member 2");

        Flux<String> group = broadcastTheMessage()
                .publish()
                .refCount(1);

        group.subscribe(member1);
        SubscriberUtil.freeze(4);
        group.subscribe(member2);
        SubscriberUtil.freeze(15);
    }

    public static Flux<String> broadcastTheMessage() {
        return Flux.fromStream(
                Stream.of(
                        "Message 1 ",
                        "Message 2 ",
                        "Message 3 ",
                        "Message 4 ",
                        "Message 5 ",
                        "Message 6 ",
                        "Message 7 ")
        ).delayElements(Duration.ofSeconds(1));
    }
}
