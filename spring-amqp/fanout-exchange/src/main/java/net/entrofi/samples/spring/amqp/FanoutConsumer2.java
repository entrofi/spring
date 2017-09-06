package net.entrofi.samples.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hasan COMAK
 */
@Component
public class FanoutConsumer2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutConsumer2.class);


    @RabbitListener(queues = "#{inboundQueue2.name}")
    public void consume(InboundMessage message) {
        LOGGER.info("Consumer 2 is consuming message " + message);
    }
}
