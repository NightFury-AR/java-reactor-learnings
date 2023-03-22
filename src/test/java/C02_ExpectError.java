import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class C02_ExpectError {
    @Test
    public void expectErrorTest() {
        Flux<Integer> f1 = Flux.just(1, 2, 3);
        Flux<Object> f2 = Flux.error(new RuntimeException("err"));
        Flux<Object> concat = Flux.concat(f1, f2);
        StepVerifier.create(concat)
                .expectNext(1,2,3)
                .verifyError(RuntimeException.class);
    }
}
