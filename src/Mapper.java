import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Mapper {
    ACTOR_APPEARANCE(list -> list.isEmpty() ? Optional.empty() : Optional.of(list.stream()
            .map(Movie::getCast)
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))));

    final Function<List<Movie>, Optional<Map<String,Long>>> mapFunc;

    Mapper(Function<List<Movie>, Optional<Map<String,Long>>> mapFunc) {
        this.mapFunc = mapFunc;
    }
}
