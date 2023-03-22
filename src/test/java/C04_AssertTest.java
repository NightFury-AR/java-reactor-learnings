import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class C04_AssertTest {

    @Test
    public void testAssert() {
        StepVerifier.create(Flux.just(1,2,3,5))
                .assertNext(Assert::assertNotNull)
                .expectComplete();
    }

    @Test
    public void testAssert_1() {
        StepVerifier.create(Flux.just(1,2,3,5).delayElements(Duration.ofSeconds(3)))
                .assertNext(Assert::assertNotNull)
                .expectComplete()
                .verify(Duration.ofSeconds(2));
    }
}
