package work02.ocp;


public class SMSSender implements Sender{
    public void send(String msg) {
        SMSUtil.send(msg);
    }
}
