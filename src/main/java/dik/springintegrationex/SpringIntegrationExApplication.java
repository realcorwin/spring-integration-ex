package dik.springintegrationex;

import dik.springintegrationex.model.Drink;
import dik.springintegrationex.model.DrinkNames;
import dik.springintegrationex.model.Order;
import dik.springintegrationex.services.Bar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Random;

@SpringBootApplication
public class SpringIntegrationExApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationExApplication.class, args);

		Bar bar = ctx.getBean(Bar.class);

		for (int i = 0; i < 10; i++) {
			Thread.sleep(300);
			Order order = generateOrder();
			order.setId(i);
			System.out.println("New order: " + order.toString());
			Drink drink = bar.process(order);
			System.out.println("Ready drink: " + drink.toString());
		}
	}

	private static Order generateOrder() {
		Random r = new Random();
		DrinkNames[] names = DrinkNames.values();
		return new Order(names[r.nextInt(names.length)].toString(), r.nextInt(Drink.NUMBER_OF_SIZES - 1) + 1);
	}

}
