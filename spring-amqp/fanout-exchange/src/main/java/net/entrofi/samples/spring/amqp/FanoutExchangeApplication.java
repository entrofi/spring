package net.entrofi.samples.spring.amqp;


import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
public class FanoutExchangeApplication implements CommandLineRunner {

    public static final String FANOUT_EXCHANGE_NAME = "FanoutExchange";

    @Autowired
    private FanoutPublisher fanoutPublisher;

    public static void main(String[] args) {
        SpringApplication.run(FanoutExchangeApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        for(int i = 0; i < 10; i++) {
            fanoutPublisher.send();
        }
    }


    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }


    @Bean
    public Queue inboundQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding exchangeBinding1(@Autowired FanoutExchange exchange,
                                    @Autowired Queue inboundQueue1) {
        return BindingBuilder.bind(inboundQueue1).to(exchange);
    }


    @Bean
    public Queue inboundQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding exchangeBinding2(@Autowired FanoutExchange exchange,
                                    @Autowired Queue inboundQueue2) {
        return BindingBuilder.bind(inboundQueue2).to(exchange);
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
