package T05_Threading_n_Schedulers;

import reactor.core.publisher.Flux;

//boundElastic - time consuming
//parallel - CPU intensive
//single - dedicated thread for single task
//immediate - current Thread

//subscriberOn
//publishOn
public class C01_ThreadDemo {
    public static void main(String[] args) {
        Flux<Object> fl = Flux.create(fluxSink -> {
            printThreadName("inside flux");
            fluxSink.next(1);
        }).doOnNext(item -> printThreadName(item + ""));
        Runnable r = () -> fl.subscribe(item -> printThreadName(item+" from subscriber"));
        new Thread(r).start();
        new Thread(r).start();
    }

    private static void printThreadName(String module) {
        System.out.println(module+" Thread :"+Thread.currentThread().getName());
    }
}
