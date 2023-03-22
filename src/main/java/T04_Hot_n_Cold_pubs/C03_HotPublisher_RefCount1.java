package T04_Hot_n_Cold_pubs;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;
import java.util.stream.Stream;

// the refCount = subscriber count. (i.e when the subscriber count matches with ref count , then only data will flow)
public class C03_HotPublisher_RefCount1 {
    public static void main(String[] args) {
        CustomSubscriber member1 = new CustomSubscriber("Member 1");
        CustomSubscriber member2 = new CustomSubscriber("Member 2");

        Flux<String> group = broadcastTheMessage()
                .publish()
                .refCount(2);

        group.subscribe(member1);
        SubscriberUtil.freeze(2);
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
