import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class C06_TestName {

    @Test
    public void testName() {
        StepVerifierOptions name = StepVerifierOptions.create().scenarioName("test-a");
        StepVerifier.create(Flux.just(1,2,3),name)
                .expectNext(1,2)
                .verifyComplete();
    }

    @Test
    public void testName_1() {
        StepVerifierOptions.create().scenarioName("test-a");
        StepVerifier.create(Flux.just(1,2,3))
                .expectNext(1)
                .as("test-1")
                .expectNext(2)
                .as("test-2")
                .expectNext(4)
                .as("test-3")
                .verifyComplete();
    }
}
