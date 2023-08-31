package T05_Threading_n_Schedulers;

import T04_Hot_n_Cold_pubs.CustomSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import utils.SubscriberUtil;

/* scenario -1
flux.data
    .subcriber1
    .publisherOn
    .subscriber2
    .subcribeOn1
    .subscriber3
    .subscriberOn2
    .subscriber4
=> in this case whichever subscribers mentioned before publishOn will be executed by very nearly configured subscribeOn thread
=> in our case its subscribeOn1 which is very near to flux.
=> but as soon as we find a publisherOn operator , then subsequent code will be executed by the publisher thread.
=> however all operators which is before "publisherOn" will be executed only by the nearly "subscribeOn" thread
 */

public class C05_SubsPubsOn {
    public static void main(String[] args) {
        Flux.just("new york")
                .map(C05_SubsPubsOn::toUpperCase)
                .publishOn(Schedulers.boundedElastic())
                .map(C05_SubsPubsOn::toConcat)
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.boundedElastic())
                .map(C05_SubsPubsOn::toLowerCase)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribeWith(new CustomSubscriber("c"));
        SubscriberUtil.freeze(5);
    }

    private static String toUpperCase(String str) {
        System.out.println("UPPERCASE :: "+str+" using "+Thread.currentThread().getName());
        return str.toUpperCase();
    }

    private static String toConcat(String str) {
        System.out.println("CONCAT :: "+str+" using "+Thread.currentThread().getName());
        return str.concat(" city");
    }

    private static String toLowerCase(String str) {
        System.out.println("LOWERCASE :: "+str+" using "+Thread.currentThread().getName());
        return str.toLowerCase();
    }
}
