package de.springbootbuch.redis_pubsub.film_rental.payment_service;

import de.springbootbuch.redis_pubsub.film_rental.payment_service.FilmReturnedEventReceiver.FilmReturnedEvent;
import java.util.concurrent.CountDownLatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		final CountDownLatch closeLatch = new CountDownLatch(1);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				closeLatch.countDown();
			}
		});
		closeLatch.await();
	}

	@Bean
	MessageListenerAdapter eventReceiverAdapter(
		FilmReturnedEventReceiver eventReceiver
	) {
		final MessageListenerAdapter adapter
			= new MessageListenerAdapter(eventReceiver, "filmReturned");
		adapter.setSerializer(
			new Jackson2JsonRedisSerializer<>(FilmReturnedEvent.class));
		return adapter;
	}

	@Bean
	RedisMessageListenerContainer messageListenerContainer(
		MessageListenerAdapter eventReceiverAdapter,
		RedisConnectionFactory connectionFactory
	) {

		RedisMessageListenerContainer container
			= new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(eventReceiverAdapter, new PatternTopic("returned-films-events"));
		return container;
	}
}
