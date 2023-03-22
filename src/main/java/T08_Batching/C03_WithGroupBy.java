package T08_Batching;

import reactor.core.publisher.Flux;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C03_WithGroupBy {
    public static void main(String[] args) {
        Map<Integer,List<Integer>> result = new HashMap<>();
        getNums()
                .groupBy(i -> i%2)
                .flatMap(gf -> gf.collectList().map(values -> new Remainder(gf.key(),values)))
                .subscribe(System.out::println);
    }

    private static Flux<Integer> getNums() {
        return Flux.range(1,50);
    }
}

class Remainder {
    public Remainder(Integer key,List<Integer> values) {
        this.key = key;
        this.values = values;
    }
    private final Integer key;
    private final List<Integer> values;

    @Override
    public String toString() {
        return "Remainder{" +
                "key=" + key +
                ", values=" + values +
                '}';
    }
}