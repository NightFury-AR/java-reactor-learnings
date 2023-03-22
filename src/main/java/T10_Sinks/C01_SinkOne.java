package T10_Sinks;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import utils.SubscriberUtil;

public class C01_SinkOne {
    public static void main(String[] args) {
        //Mono sinks
        Sinks.One<Object> sinkOne = Sinks.one();
        Mono<Object> sinkAsMono = sinkOne.asMono();
        sinkAsMono.subscribe(SubscriberUtil.normalSubscriber("sinkAsMono"));
        sinkOne.tryEmitValue("Hello");

        // retry logic
        /*sinkOne.emitValue("hello",(signalType, emitResult) -> {
            System.out.println(" signal is "+signalType.name());
            System.out.println(" EmitResult "+emitResult.name());
            return false;
        });
        sinkOne.emitValue("hello2",(signalType, emitResult) -> {
            System.out.println(" signal is "+signalType.name());
            System.out.println(" EmitResult "+emitResult.name());
            return false;
        });*/

        sinkAsMono.subscribe(SubscriberUtil.normalSubscriber("first"));
        sinkAsMono.subscribe(SubscriberUtil.normalSubscriber("2nd"));
    }
}
