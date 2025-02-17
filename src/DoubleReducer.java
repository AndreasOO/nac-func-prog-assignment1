import java.util.List;
import java.util.function.Function;

public enum DoubleReducer {
    MAX_RATING(list -> list.stream().mapToDouble(Movie::getImdbRating)
                                                  .max()
                                                  .orElse(0.0));

    final Function<List<Movie>, Double> func;

    DoubleReducer(Function<List<Movie>, Double> func) {
        this.func = func;
    }
}
