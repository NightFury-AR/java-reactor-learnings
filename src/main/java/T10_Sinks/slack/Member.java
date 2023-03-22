package T10_Sinks.slack;

import java.util.function.Consumer;

public class Member {
    private final String name;

    private Consumer<String> messageConsumer;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receives(String message) {
        System.out.println(message);
    }

    public void posts(String message) {
        messageConsumer.accept(message);
    }

    void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }
}
