package dik.springintegrationex.config;

import dik.springintegrationex.model.Drink;
import dik.springintegrationex.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@EnableIntegration
@IntegrationComponentScan
@Configuration
public class IntegrationConfig {

    @Bean
    public DirectChannel order() {
        return MessageChannels.direct().datatype(Order.class).get();
    }

    @Bean
    public PublishSubscribeChannel barkeep() {
        return MessageChannels.publishSubscribe().datatype(Drink.class).get();
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
