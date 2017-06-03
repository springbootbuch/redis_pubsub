package de.springbootbuch.redis_pubsub.payment_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
public class FilmReturnedEventReceiver {

	private static final Logger LOG = LoggerFactory
		.getLogger(FilmReturnedEventReceiver.class);

	public void filmReturned(FilmReturnedEvent event) {
		LOG.info(
			"Film '{}' returned, billing customer...", 
			event.title
		);
	}
}
