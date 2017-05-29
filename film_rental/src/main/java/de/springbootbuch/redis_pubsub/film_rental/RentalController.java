package de.springbootbuch.redis_pubsub.film_rental;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RestController
public class RentalController {
	
	public static class ReturnedFilm {
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
	
	private final InventoryRepository inventoryRepository;
	
	private final  RedisTemplate redisTemplate;

	public RentalController(InventoryRepository inventoryRepository, RedisTemplate redisTemplate) {
		this.inventoryRepository = inventoryRepository;
		this.redisTemplate = redisTemplate;
	}

	@PostMapping("/returnedFilms")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void returnFilm(
		@RequestBody ReturnedFilm returnedFilm
	) {
		final FilmInStore filmInStore = 
			this.inventoryRepository
				.save(new FilmInStore(returnedFilm.getTitle()));

		redisTemplate.convertAndSend(
			"returned-films-events", 
			new FilmReturnedEvent(
				filmInStore.getId(), 
				filmInStore.getTitle()
			)
		);
	}
}
