package T10_Sinks.slack;

public class Slack {

    public static void main(String[] args) {
        Room slackRoom = new Room("Slack");
        //when new room is created new sink and flux were initialized

        //just new object created here
        Member heMan = new Member("HE-MAN");
        Member batMan = new Member("BAT-MAN");
        Member superMan = new Member("SUPER-MAN");

        //subscription happening here with consumer
        slackRoom.joinRoom(heMan);

        //emittin]'
        heMan.posts(" heee man....");
        slackRoom.joinRoom(batMan);
        batMan.posts(" batttt man");
        slackRoom.joinRoom(superMan);
        superMan.posts(" super mann..");

    }
}
