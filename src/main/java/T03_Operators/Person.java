package T03_Operators;

import lombok.Data;
import lombok.ToString;
import utils.SubscriberUtil;

import static utils.SubscriberUtil.faker;

@Data
@ToString
public class Person {
    private String name;
    private Integer age;
    public Person() {
        this.name = faker().name().name();
        this.age = faker().number().numberBetween(16,25);
    }
}
