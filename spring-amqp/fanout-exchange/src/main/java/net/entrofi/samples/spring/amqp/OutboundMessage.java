package net.entrofi.samples.spring.amqp;

import java.time.Instant;

/**
 * @author Hasan COMAK
 */
public class OutboundMessage {

    private final int messageNum;

    private final Instant messageTime;

    public OutboundMessage(final int messageNum) {
        this.messageNum = messageNum;
        this.messageTime = Instant.now();
    }


    public int getMessageNum() {
        return messageNum;
    }

    public Instant getMessageTime() {
        return messageTime;
    }


    @Override
    public String toString() {
        return "OutboundMessage{" +
                "messageNum=" + messageNum +
                ", messageTime=" + messageTime +
                '}';
    }
}
