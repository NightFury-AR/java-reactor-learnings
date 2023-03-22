package T02_Flux.helper;

import reactor.core.publisher.FluxSink;
import utils.SubscriberUtil;

import java.util.function.Consumer;

import static utils.SubscriberUtil.faker;

public class CountryProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce() {
        this.fluxSink.next(faker().country().name());
    }
}
