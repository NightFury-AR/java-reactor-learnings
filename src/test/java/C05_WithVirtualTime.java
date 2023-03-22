import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class C05_WithVirtualTime {
    @Test
    public void virtualTest() {
        StepVerifier.
                withVirtualTime(() -> Flux.just(1,2,3).delayElements(Duration.ofSeconds(3)))
                .thenAwait(Duration.ofSeconds(12))
                .expectNext(1,2,3)
                .verifyComplete();
    }

    @Test
    public void virtualTest_1() {
        StepVerifier
                .withVirtualTime(() -> Flux.just(1,2,3).delayElements(Duration.ofSeconds(3)))
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(3))
                .thenAwait(Duration.ofSeconds(10))
                .expectNext(1,2,3)
                .verifyComplete();
    }
}
