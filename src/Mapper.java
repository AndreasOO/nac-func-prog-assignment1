import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Mapper {
    ACTOR_APPEARANCE(list -> list.stream()
            .map(Movie::getCast)
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(actor -> actor, Collectors.counting())));

    final Function<List<Movie>, Map<String,Long>> func;

    Mapper(Function<List<Movie>, Map<String,Long>> func) {
        this.func = func;
    }
}
