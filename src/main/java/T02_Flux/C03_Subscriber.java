package T02_Flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class C03_Subscriber implements Subscriber<Object> {
    private static final Integer NUMBER_LIMIT = 70;
    private static final Integer STRING_MAX_LETTERS = 2007;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        System.out.println("SUBSCRIBED ✅");
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object object) {
        System.out.println("REQUEST_RECEIVED ↘️=> "+object);
        if ((object instanceof Integer) && ((Integer) object > NUMBER_LIMIT)) {
            System.out.println("CANNOT MOVE AFTER "+NUMBER_LIMIT+", SO CANCELLING ❌");
            subscription.cancel();
        } else if (object instanceof String && ((String) object).length() > STRING_MAX_LETTERS) {
            System.out.println("THE LENGTH IS GREATER THAN "+STRING_MAX_LETTERS+", SO CANCELLING");
            subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("ERROR_OCCURRED 💀 => "+throwable.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("COMPLETED 🏁 ");
    }
}
