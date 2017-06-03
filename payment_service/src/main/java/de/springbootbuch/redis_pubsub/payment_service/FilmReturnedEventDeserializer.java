package de.springbootbuch.redis_pubsub.payment_service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import de.springbootbuch.redis_pubsub.payment_service.FilmReturnedEvent;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@JsonComponent
public class FilmReturnedEventDeserializer 
	extends JsonDeserializer<FilmReturnedEvent> {

	@Override
	public FilmReturnedEvent deserialize(
		JsonParser p, DeserializationContext ctxt
	) throws IOException {
		final JsonNode node = p.getCodec().readTree(p);
		return new FilmReturnedEvent(node.get("title").asText());
	}
}
