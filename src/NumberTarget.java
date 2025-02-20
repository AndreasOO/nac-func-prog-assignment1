import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum NumberTarget {
    MAX_RATING(list -> list.stream().mapToDouble(Movie::getImdbRating)
                                                  .max()
                                                  .orElse(0.0)),

    MAX_CAST_SIZE(list -> list.stream().mapToInt(movie2 -> movie2.getCast().size())
            .min()
            .orElseThrow()),

    MAX_RUNTIME(list -> list.stream().mapToInt(Movie::getRuntime)
            .max()
            .orElse(0)),

    MAX_ACTOR_APPEARANCE(list -> list.stream().map(Movie::getCast)
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
            .values().stream().mapToLong(Long::longValue)
            .max()
            .orElse(0));;

    final Function<List<Movie>, ? extends Number> func;

    NumberTarget(Function<List<Movie>, ? extends Number> func) {
        this.func = func;
    }
}
