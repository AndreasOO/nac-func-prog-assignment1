import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LongTarget {
    MAX_ACTOR_APPEARANCE(list -> list.stream().map(Movie::getCast)
                                                        .flatMap(List::stream)
                                                        .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                                                        .values().stream().mapToLong(Long::longValue)
                                                                          .max()
                                                                          .orElse(0));



    final Function<List<Movie>, Long> func;

    LongTarget(Function<List<Movie>, Long> func) {
        this.func = func;
    }
}
