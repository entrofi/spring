package net.entrofi.samples.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Hasan COMAK
 */
@Component
public class FanoutPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutPublisher.class);

    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void send() {
       OutboundMessage outboundMessage = new OutboundMessage(ThreadLocalRandom.current().nextInt());
       LOGGER.info("Sending message to fanout exchange: " + outboundMessage);
       rabbitMessagingTemplate.convertAndSend(fanoutExchange.getName(), "", outboundMessage);
    }

}
