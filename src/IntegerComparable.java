import java.util.function.Function;

public enum IntegerComparable {
    CAST_SIZE(movie -> movie.getCast().size());

    final Function<Movie, Integer> func;

    IntegerComparable(Function<Movie, Integer> func) {
        this.func = func;
    }
}