package T05_Threading_n_Schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import utils.SubscriberUtil;

public class C04_PublisherOn {
    public static void main(String[] args) {
        Flux.just("new york","lisbon","tokyo","london","paris")
                .map(C04_PublisherOn::toUpperCase) //main
                .publishOn(Schedulers.boundedElastic())
                .map(C04_PublisherOn::toConcat) //be-2
                .publishOn(Schedulers.boundedElastic())
                .map(C04_PublisherOn::toLowerCase)//be-1
                .subscribe();
        SubscriberUtil.freeze(5);
    }

    private static String toUpperCase(String str) {
        System.out.println("UPPERCASE :: "+Thread.currentThread().getName());
        return str.toUpperCase();
    }

    private static String toConcat(String str) {
        System.out.println("CONCAT :: "+Thread.currentThread().getName());
        return str.concat(" city");
    }

    private static String toLowerCase(String str) {
        System.out.println("LOWERCASE :: "+Thread.currentThread().getName());
        return str.toLowerCase();
    }

}
