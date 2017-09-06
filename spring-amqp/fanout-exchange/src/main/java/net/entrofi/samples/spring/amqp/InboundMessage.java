package net.entrofi.samples.spring.amqp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Hasan COMAK
 */
public class InboundMessage {

    private final int messageNum;

    @JsonCreator
    public InboundMessage(@JsonProperty("messageNum") int messageNum) {
        this.messageNum = messageNum;
    }

    public int getMessageNum() {
        return messageNum;
    }

    @Override
    public String toString() {
        return "InboundMessage{" +
                "messageNum=" + messageNum +
                '}';
    }
}
