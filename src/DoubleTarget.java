import java.util.function.Function;

public enum DoubleTarget {
    RATING(Movie::getImdbRating);

    final Function<Movie, Double> func;

    DoubleTarget(Function<Movie, Double> func) {
        this.func = func;
    }
}
