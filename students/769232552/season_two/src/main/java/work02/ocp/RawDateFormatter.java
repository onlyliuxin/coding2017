package work02.ocp;

public class RawDateFormatter implements Formatter {

    public String formatMsg(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        return  txtDate + ": " + msg;
    }
}
