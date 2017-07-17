package work02.ocp;


public class PrinterSender implements Sender {
    public void send(String msg) {
        System.out.println(msg);
    }
}
