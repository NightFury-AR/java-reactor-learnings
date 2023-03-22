package T10_Sinks.slack;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Room {
    private final String roomName;
    private final Sinks.Many<Message> sink;
    private final Flux<Message> flux;

    public Room(String roomName) {
        this.roomName = roomName;
        this.sink = Sinks.many().replay().all();
        this.flux = this.sink.asFlux();
    }

    public void joinRoom(Member slackMember) {
        System.out.println(slackMember.getName()+" joined to "+this.roomName);
        this.subscribe(slackMember);
        slackMember.setMessageConsumer(s -> this.postMessage(s,slackMember));
    }

    public void subscribe(Member slackMember) {
        this.flux
                .filter(message -> !message.getSender().equals(slackMember.getName()))
                .doOnNext(message -> message.setReceiver(slackMember.getName()))
                .map(Message::toString)
                .subscribe(slackMember::receives);
    }

    public void postMessage(String message,Member slackMember){
        Message slackMessage = new Message();
        slackMessage.setSender(slackMember.getName());
        slackMessage.setMessage(message);
        this.sink.tryEmitNext(slackMessage);
    }
}
