package de.springbootbuch.redis_pubsub.film_rental;

import de.springbootbuch.redis_pubsub.film_rental.RentalController.FilmReturnedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
		final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setDefaultSerializer(
			new Jackson2JsonRedisSerializer<>(FilmReturnedEvent.class));
		return redisTemplate;
	}
}
