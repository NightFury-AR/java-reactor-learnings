package T03_Operators;

import reactor.core.publisher.Flux;
import utils.SubscriberUtil;

import static utils.SubscriberUtil.faker;
import static utils.SubscriberUtil.subscriber;

public class C01_Handle {
    public static void main(String[] args) {
        // Handle operator
        /*Flux.range(1,100)
                .handle((value, synchronousSink) -> {
                    if (value % 2 == 0) {
                        synchronousSink.next("Even: " + value);
                    } else {
                        synchronousSink.next("odd: " + value);
                    }
                }).subscribe(subscriber());*/

        Flux.generate(countryList -> countryList.next(faker().country().name())).map(Object::toString)
                .handle((country, synchronousSink) -> {
                    if (country.equalsIgnoreCase("canada")) {
                        synchronousSink.next(country);
                        synchronousSink.complete();
                    } else {
                        synchronousSink.next(country);
                    }
                }).subscribe(subscriber());
    }
}
