package T02_Flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class C03_DefaultSubscriber implements Subscriber<Object> {
    private Subscription subscription;
    private final String name;
    public C03_DefaultSubscriber(String name) {
        this.name = name;
    }

    public C03_DefaultSubscriber() {
        this.name = "Default";
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        System.out.println(getName()+ " SUBSCRIBED ✅");
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object object) {
        System.out.println(getName() + " REQUEST_RECEIVED ↘️=> "+object);
    }

    @Override
    public void onError(Throwable throwable) {
        this.subscription.cancel();
        System.out.println(getName()+" ERROR_OCCURRED 💀 => "+throwable.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(getName()+" COMPLETED 🏁 ");
    }

    public String getName() {
        return name;
    }
}
