import java.util.List;
import java.util.function.Function;

public enum DoubleTarget {
    MAX_RATING(list -> list.stream().mapToDouble(Movie::getImdbRating)
                                                  .max()
                                                  .orElse(0.0));

    final Function<List<Movie>, Double> func;

    DoubleTarget(Function<List<Movie>, Double> func) {
        this.func = func;
    }
}
