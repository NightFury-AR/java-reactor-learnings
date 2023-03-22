package utils;

import T02_Flux.C03_DefaultSubscriber;
import T02_Flux.C03_Subscriber;
import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class SubscriberUtil {

    public static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return o -> System.out.println(" RECEIVED : "+o);
    }

    public static Consumer<Throwable> onError() {
        return err -> System.out.println(" ERROR : "+err.toString());
    }

    public static Runnable onComplete() {
        return () -> System.out.println(" COMPLETED");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static Subscriber<Object> subscriber() {
        return new C03_Subscriber();
    }

    public static Subscriber<Object> normalSubscriber() {
        return new C03_DefaultSubscriber();
    }
    public static Subscriber<Object> normalSubscriber(String name) {
        return new C03_DefaultSubscriber(name);
    }

    public static void freeze(int seconds) {
        sleep(seconds * 1000);
    }

    public static void sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
