package dik.springintegrationex.config;

import dik.springintegrationex.model.Drink;
import dik.springintegrationex.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@EnableIntegration
@IntegrationComponentScan
@Configuration
public class IntegrationConfig {

    @Bean
    public QueueChannel order() {
        return MessageChannels.queue().datatype(Order.class).get();
    }

    @Bean
    public PublishSubscribeChannel barkeep() {
        return MessageChannels.publishSubscribe().datatype(Drink.class).get();
    }

    @Bean (name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return Pollers.fixedRate(100).get() ;
    }

    @Bean
    public IntegrationFlow barkeepFlow() {
        return IntegrationFlows.from("order")
                .<Order, Boolean>route(order -> order.getId() % 3 == 0,
                        m -> m
                                .subFlowMapping(false, sf -> sf
                                        .handle("firstBarkeeperService", "make")
                                )
                                .subFlowMapping(true, sf -> sf
                                        .handle("secondBarkeeperService", "make")
                                )
                )
                .channel("barkeep")
                .log()
                .get();
    }
}
