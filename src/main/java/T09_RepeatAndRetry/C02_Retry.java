package T09_RepeatAndRetry;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

//used to retry the subs
// TODO:: retryWhen
public class C02_Retry {
    public static void main(String[] args) {
        Flux.just("A","B","C","D")
                .doOnEach(stringSignal -> {
                    if ("B".equals(stringSignal.get()))
                        throw new RuntimeException("errr");
                    else
                        stringSignal.isOnNext();
                })
                .retry(2)
                .subscribe(SubscriberUtil.normalSubscriber("with error"));
    }
}
