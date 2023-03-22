package T08_Batching;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.time.Duration;

public class C01_WithBuffer {
    public static void main(String[] args) {
        //with 5 buffer
        /*getNums()
                .buffer(5)
                .subscribe(SubscriberUtil.normalSubscriber("with5Buffer"));

        //buffer for 2 sec
        getNums()
                .buffer(Duration.ofSeconds(2))
                .subscribe(SubscriberUtil.normalSubscriber("with2SecBuffer"));

        //buffer with timeout
        getNums()
                .delayElements(Duration.ofSeconds(1))
                .bufferTimeout(5,Duration.ofSeconds(1))
                .subscribe(SubscriberUtil.normalSubscriber("with5or3secBuffer"));
        */
        //buffer with skip
        getNums()
                .buffer(3,1)
                .subscribe(SubscriberUtil.normalSubscriber("withSkipBuffer"));



        SubscriberUtil.freeze(15);
    }

    private static Flux<Integer> getNums() {
        return Flux.range(1,200);
    }
}
