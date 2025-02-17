import java.util.List;
import java.util.function.Function;

public enum IntReducer {
    MAX_CAST_SIZE(list -> list.stream().mapToInt(movie2 -> movie2.getCast().size())
            .min()
            .orElseThrow()),

    MAX_RUNTIME(list -> list.stream().mapToInt(Movie::getRuntime)
            .max()
            .orElse(0));

    final Function<List<Movie>, Integer> func;

    IntReducer(Function<List<Movie>, Integer> func) {
        this.func = func;
    }
}
