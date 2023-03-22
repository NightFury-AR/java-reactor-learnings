package T01_Mono;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import utils.SubscriberUtil;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static utils.SubscriberUtil.*;
import static utils.SubscriberUtil.onError;
import static utils.SubscriberUtil.onNext;

public class C02_MonoExample {

    public static void main(String[] args) {
/*
        // 1.code example
        System.out.println("\n MONO_FROM_METHOD ");
        System.out.println(" ================ ");
        getUserByUserId(-3)
                .subscribe(onNext(),onError(),onComplete());

        // 2.mono from supplier
        System.out.println("\n MONO_FROM_SUPPLIER ");
        System.out.println(" ================== ");
        Mono.fromSupplier(getRandomNameSupplier())
                .subscribe(onNext(),onError(),onComplete());*/

        // 3.mono from supplier + delay
        System.out.println("\n MONO_FROM_SUPPLIER_WITH_DELAY ");
        System.out.println(" ================== ");

        //getNameAndModify logic will not be called until we call subscribe
        // when we use block the statement will not move until the blocked statement is executed
        String usingBlock = getNameAndModify()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        System.out.println(" FROM BLOCK "+usingBlock);

        // 4.without block
        getNameAndModify()
                .subscribe(onNext(),onError(),onComplete());


        // 5. mono from future
        System.out.println("\n MONO_FROM_FUTURE ");
        System.out.println(" ================== ");
        Mono.fromFuture(getNameUsingFuture())
                .subscribe(
                        v -> System.out.println("FUTURE: "+v),
                        onError(),
                        onComplete());
        //just to see the future mono result

        // 6. mono from runnable
        System.out.println("\n MONO_FROM_RUNNABLE ");
        System.out.println(" ================== ");
        Mono.fromRunnable(
                () -> {
                    freeze(5);
                    faker().name().fullName();
                    })
                .subscribe(
                        onNext(),
                        onError(),
                        () -> System.out.println(" do some business tasks (mails,bkp,etc.,)"));
        freeze(15);
    }

    private static Mono<String> getUserByUserId(int userId) {
        if (userId == 0) return Mono.empty();
        else if (userId >= 1 && userId <= 10) return Mono.just(faker().name().fullName());
        else return Mono.error(() -> new IllegalArgumentException(" user Id is not Valid"));
    }

    private static Supplier<String> getRandomNameSupplier() {
        return () -> faker().name().firstName();
    }

    private static Supplier<String> getNameSupplier() {
        System.out.println(" getting name...");
        freeze(2);
        return getRandomNameSupplier();
    }

    private static Mono<String> getNameAndModify() {
        System.out.println(" validating name");
        return Mono
                .fromSupplier(getNameSupplier())
                .map(String::toUpperCase);
    }

    private static CompletableFuture<String> getNameUsingFuture() {
        return CompletableFuture.supplyAsync( () -> faker().name().fullName());
    }
}
