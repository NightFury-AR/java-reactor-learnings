package T10_Sinks.slack;

import lombok.Data;
import lombok.ToString;

@Data
public class Message {
    private String sender;
    private String receiver;
    private String message;

    @Override
    public String toString() {
        return "["+sender+" -> "+receiver+"] : "+message;
    }
}
