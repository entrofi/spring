package net.entrofi.samples.spring.amqp;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Hasan COMAK
 */
@SpringBootApplication
public class SpringAMQPApplication implements CommandLineRunner {

    public static final String INVENTORY_QUEUE = "InventoryQueue";

    @Autowired
    private InventoryPublisher inventoryPublisher;


    public static void main(String[] args) {
        SpringApplication.run(SpringAMQPApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        for(int i = 0; i < 10; i++) {
            inventoryPublisher.send();
        }
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE, false);
    }


    @Bean
    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
