package de.springbootbuch.redis_pubsub.payment_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Component
public class FilmReturnedEventReceiver {

	private static final Logger LOG = LoggerFactory
		.getLogger(Application.class);

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FilmReturnedEvent {

		private final String title;

		public FilmReturnedEvent(@JsonProperty("title") String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}
	}

	public void filmReturned(FilmReturnedEvent event) {
		LOG.info("Film '{}' returned, billing customer...", event.getTitle());
	}
}
