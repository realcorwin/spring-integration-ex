package dik.springintegrationex.services;

import dik.springintegrationex.model.Drink;
import dik.springintegrationex.model.Order;
import org.springframework.stereotype.Service;

@Service
public class FirstBarkeeperService {
    public Drink make(Order order) throws Exception {
        System.out.println("Making " + order.getDrinkName() + " by first barkeeper");
        Thread.sleep(500);
        System.out.println("Making " + order.getDrinkName() + " done");
        return new Drink(order.getDrinkName(), order.getDrinkSize());
    }
}
