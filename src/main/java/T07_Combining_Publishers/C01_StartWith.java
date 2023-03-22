package T07_Combining_Publishers;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import java.util.ArrayList;
import java.util.List;

import static utils.SubscriberUtil.*;

// it'll look for startwith publisher first
public class C01_StartWith {

    private static final List<String> cacheList = new ArrayList<>();

    public static void main(String[] args) {
        nameGen()
                .take(2)
                .subscribe(normalSubscriber("A"));
        nameGen()
                .take(2)
                .subscribe(normalSubscriber("B"));
    }

    private static Flux<String> nameGen() {
         return Flux.generate(stringSynchronousSink -> {
             System.out.println("generated fresh");
             freeze(2);
             String name = faker().name().fullName();
             cacheList.add(name);
             stringSynchronousSink.next(name);
        }).cast(String.class)
                 .startWith(getFromCache());
    }

    private static Flux<String> getFromCache() {
        return Flux.fromIterable(cacheList);
    }
}
