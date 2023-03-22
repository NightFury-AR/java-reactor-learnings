package T02_Flux;

import T02_Flux.helper.CountryProducer;
import reactor.core.publisher.Flux;

import static utils.SubscriberUtil.*;
// what is fluxSink ?
// what is synchorous sink ?
public class C03_FluxExample {

    public static void main(String[] args) {

        //flux sink example
        System.out.println("===USING_FLUX_SINK===");
        Flux.create(fluxSink -> {
            String name = "";
            if (!name.equalsIgnoreCase("canada") && !fluxSink.isCancelled()) {
                for (int i = 0; i < 100; i++) {
                    name = faker().country().name();
                    fluxSink.next(name);
                }
                fluxSink.complete();
            }
        }).subscribe(subscriber());

        //flux sink 2
        System.out.println("===USING_FLUX_SINK_2===");
        CountryProducer countryProducer = new CountryProducer();
        Flux.create(countryProducer).subscribe(subscriber());
        // calling next method in produce
        countryProducer.produce();


        //flux sink vs sink
        //synchronous sink
        System.out.println("===USING_SYNCHRONOUS_SINK===");
        Flux.generate(synchronousSink -> {
            String name = faker().country().name();
            synchronousSink.next(name);
            if (name.equalsIgnoreCase("canada")) synchronousSink.complete();
        }).subscribe(subscriber());

        //synchronous sink with state
        System.out.println("===USING_SYNCHRONOUS_SINK_WITH_STATE===");
        Flux.generate(
                () -> 1,
                (state,sink) -> {
                    String name = faker().country().name();
                    sink.next(name);
                    if (state == 100 || name.equalsIgnoreCase("canada")) {
                        System.out.println( state==100 ? "Reached 10 ":" found canada");
                        sink.complete();
                    }
                    return state+1;
                }
        ).subscribe(subscriber());

        System.out.println("===USING_TAKE===");
        Flux.range(1,10)
                .log()
                .take(3)
                .log()
                .subscribe(onNext(), onError(), onComplete());
    }


}
