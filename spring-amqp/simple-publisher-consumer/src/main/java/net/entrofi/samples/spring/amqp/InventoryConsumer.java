package net.entrofi.samples.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hasan COMAK
 */
@Component
public class InventoryConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryConsumer.class);

    @RabbitListener(queues = SpringAMQPApplication.INVENTORY_QUEUE)
    public void consumeMessage(InboundFlightMessage inboundFlightMessage) {
        LOGGER.info("Received message: " + inboundFlightMessage);
    }

}
