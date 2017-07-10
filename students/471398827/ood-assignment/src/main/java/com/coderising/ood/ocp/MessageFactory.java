package com.coderising.ood.ocp;

/**
 * Created by szf on 6/20/17.
 */
public class MessageFactory {

    static IMessage produce(int messageType) {
        IMessage msg = null;
        switch (messageType) {
            case Config.RAW_LOG:
                msg = new RawMessage();
                break;
            case Config.RAW_LOG_WITH_DATE:
                msg = new DateMessage();
                break;
        }
        return msg;
    }
}
