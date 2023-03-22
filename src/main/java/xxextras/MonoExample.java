package xxextras;

import reactor.core.publisher.Mono;

import java.time.Duration;

public class MonoExample {
    public static void main(String[] args) throws InterruptedException {
        //1.Mono.Create
        Mono<Object> usingCreate = Mono.create(monoSink -> monoSink.success("usingCreate"));
        usingCreate.subscribe(System.out::println);
        //2.Mono.Defer
        Mono<String> usingDefer = Mono.defer(() -> Mono.fromSupplier(() -> "usingDefer"));
        usingDefer.subscribe(System.out::println);
        //3.Mono.DeferContextual
        //Mono<Object> usingDeferContextual = Mono.deferContextual(contextView -> Mono.just("using"+contextView.get(key)));
        //usingDeferContextual.subscribe(System.out::println);
        System.out.println("usingDeferContextual - not implemented");
        //4.Mono.Delay
        Mono<String> usingDelay = Mono.just("usingDelay").delayElement(Duration.ofSeconds(0));
        usingDelay.subscribe(System.out::println);
        //5.Mono.Empty
        Mono<Object> usingEmpty = Mono.empty();
        usingEmpty.subscribe(o -> System.out.println("usingEmpty"+o));
        //6.Mono.Error
        Mono<Object> usingError = Mono.error(new IllegalArgumentException("usingError"));
        usingError.subscribe(System.out::println);
        //7.Mono.first()
        Mono<String> usingFirst = Mono.first(Mono.just("1"), Mono.just("2"));
        usingFirst.subscribe(s -> System.out.println(" deprecated usingFirst"+s));
        //8.Mono.firstWithSignal();

        //9.Mono.firstWithValue();
        //10.Mono.fromCallable();
        //11.Mono.fromCompletionStage();
        //12.Mono.Direct()
        //13.Mono.fromFuture();
        //14.Mono.fromRunnable()
        //15.Mono.fromSupplier();
        //16.Mono.ignoreElements();
        //17.Mono.just();
        Mono<String> usingJust = Mono.just("Mono Using Just ");
        //18.Mono.justOrEmpty();
        //19.Mono.never();
        //20.Mono.sequenceEqual();
        //21.Mono.using();
        //22.Mono.usingWhen();
        //23.Mono.when();
        //24.Mono.whenDelayError();
        //25.Mono.zip();
        //26.Mono.zipDelayError();
        Mono<Object> usingNever = Mono.never(); // only for testing
        usingJust.subscribe(System.out::println);
    }
}

