import java.util.function.Function;

public enum NumberComparable {
    RATING(Movie::getImdbRating),
    CAST_SIZE(movie -> movie.getCast().size());

    final Function<Movie, ? extends Number> func;

    NumberComparable(Function<Movie, ? extends Number> func) {
        this.func = func;
    }
}
