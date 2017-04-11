package de.springbootbuch.redis_pubsub.film_rental;

import java.io.Serializable;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
public class FilmReturnedEvent implements Serializable {

	private static final long serialVersionUID = -1174938991379385169L;
	
	private final Integer inventoryId;
	private final String title;

	public FilmReturnedEvent(
		Integer inventoryId, String title
	) {
		this.inventoryId = inventoryId;
		this.title = title;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public String getTitle() {
		return title;
	}
}
