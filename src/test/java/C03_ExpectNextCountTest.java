import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class C03_ExpectNextCountTest {
    @Test
    public void testExpectNextCount() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4);
        StepVerifier.create(just)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void testExpectNextCount_1() {
       StepVerifier.create(Flux.range(1,500))
               .thenConsumeWhile(i -> i > 1000)
               .verifyComplete();
    }
}
