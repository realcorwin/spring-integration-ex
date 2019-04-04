package dik.springintegrationex.services;

import dik.springintegrationex.model.Drink;
import dik.springintegrationex.model.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface Bar {

    @Gateway(requestChannel = "order", replyChannel = "barkeep")
    Drink process(Order orderItem);
}
