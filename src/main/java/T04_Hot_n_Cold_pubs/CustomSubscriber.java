package T04_Hot_n_Cold_pubs;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CustomSubscriber implements Subscriber<Object> {

    private final String name;
    private Subscription subscription;

    public CustomSubscriber(String subscriberName) {
        this.name = subscriberName;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        System.out.println(this.name + " subscribed ✔️");
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println(this.name + " Received : "+o.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error occurred : "+throwable.getMessage());
        System.out.println("cancelling subscription");
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println(this.name+" received all data. subscription completed");
    }
}
