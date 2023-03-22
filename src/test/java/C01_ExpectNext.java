import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class C01_ExpectNext {

    @Test
    public void testExpectNext() {
        Flux<String> just = Flux.just("1", "2", "3");
        StepVerifier.create(just)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .verifyComplete();
    }

    @Test
    public void testExpectNext2() {
        Flux<String> just = Flux.just("1", "2", "3");
        StepVerifier.create(just)
                .expectNext("1","2","3")
                .verifyComplete();
    }
}
