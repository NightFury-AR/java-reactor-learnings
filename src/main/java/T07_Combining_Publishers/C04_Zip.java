package T07_Combining_Publishers;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

//its like LCM strategy (e.g 3 doors 2 locks 2 keys then only 2 doors , 2 keys , 2 locks can be subscribed)
public class C04_Zip {
    public static void main(String[] args) {
        Flux.zip(getDoor(),getLock(),getKey())
                .subscribe(SubscriberUtil.normalSubscriber("zip"));
    }

    private static Flux<String> getDoor() {
        return Flux.just("A:DOOR","B:DOOR","C:DOOR","D:DOOR");
    }

    private static Flux<String> getLock() {
        return Flux.just("A:LOCK","B:LOCK","C:LOCK");
    }

    private static Flux<String> getKey() {
        return Flux.just("A:KEY","B:KEY","C:KEY");
    }
}
