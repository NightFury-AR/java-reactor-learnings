import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class C07_TestContext {
    @Test
    public void testContext() {
        Mono<String> stringMono = Mono.deferContextual(contextView -> {
            if (contextView.get("user")) {
                return Mono.just("welcome");
            } else {
                return Mono.error(() -> new SecurityException("unauthorized"));
            }
        });
        StepVerifierOptions opt = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));
        StepVerifier.create(stringMono,opt)
                .expectComplete();
    }
}
