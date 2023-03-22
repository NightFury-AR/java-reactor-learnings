package T11_Context;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import utils.SubscriberUtil;

public class C01_withDefer {
    public static void main(String[] args) {
        getString()
                .contextWrite(Context.of("user","Jack"))
                .subscribe(SubscriberUtil.normalSubscriber());
    }

    private static Mono<String> getString() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")) {
                return Mono.just("Hello, "+contextView.get("user"));
            } else {
                return Mono.error(new SecurityException(" unauthorized"));
            }
        });
    }
}
