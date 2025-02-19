import java.util.function.Function;

public enum DoubleComparable {
    RATING(Movie::getImdbRating);

    final Function<Movie, Double> func;

    DoubleComparable(Function<Movie, Double> func) {
        this.func = func;
    }
}
