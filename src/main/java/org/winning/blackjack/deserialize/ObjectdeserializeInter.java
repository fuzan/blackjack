package org.winning.blackjack.deserialize;

import java.util.Optional;

public interface ObjectdeserializeInter<T> {

    Optional<T> convertObjectToJsonString(Object object);
}
