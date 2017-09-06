package net.entrofi.samples.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Hasan COMAK
 */
@Component
public class InventoryPublisher {


    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryPublisher.class);

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;


    public void send() {
        OutboundFlightMessage outboundMessage = generateRandOutboundMsg();
        LOGGER.info("Sending outbound message " + outboundMessage);
        rabbitMessagingTemplate.convertAndSend(SpringAMQPApplication.INVENTORY_QUEUE, outboundMessage);
    }

    private OutboundFlightMessage generateRandOutboundMsg() {
        String flightNumber = "TK" + ThreadLocalRandom.current().nextInt(100, 200);
        String origin = genRandCode();
        String dest = genRandCode();
        int capacity = ThreadLocalRandom.current().nextInt(150, 550);
        Date date = calculateDate();
        return new OutboundFlightMessage(flightNumber, date,capacity, origin, dest);
    }

    private String genRandCode() {
        StringBuilder codeBuilder = new StringBuilder();
        codeBuilder.append((char) ThreadLocalRandom.current().nextInt(65,91))
                .append((char) ThreadLocalRandom.current().nextInt(65,91))
                .append((char) ThreadLocalRandom.current().nextInt(65,91));
        String code = codeBuilder.toString();
        return code;
    }

    private Date calculateDate() {
        final Date currentTime = Calendar.getInstance().getTime();
        final long twoHoursAfterCurrentTime = currentTime.getTime() + 2 * 60 * 60 * 1000l;
        final long sixMonthsAfterCurrentTime = currentTime.getTime() + 6 * 30 * 24 * 60 * 60 * 1000l;
        long originLong = ThreadLocalRandom.current().nextLong(twoHoursAfterCurrentTime, sixMonthsAfterCurrentTime);
        return new Date(originLong);
    }

}
