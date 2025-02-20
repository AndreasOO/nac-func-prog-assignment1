import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Extractor {
    UNIQUE_GENRES(stream -> stream.map(Movie::getGenres)
                                               .flatMap(List::stream)
                                               .distinct()),

    UNIQUE_LANGUAGES(stream -> stream.map(Movie::getLanguages)
                                                   .flatMap(List::stream)
                                                   .distinct()),

    UNIQUE_ACTORS(stream -> stream.map(Movie::getCast)
                                               .flatMap(List::stream)
                                               .distinct()),

    UNIQUE_TITLES(stream -> stream.map(Movie::getTitle).distinct()),

    ALL_TITLES(stream -> stream.map(Movie::getTitle)),

    UNIQUE_MOVIE_IDS(stream -> stream.map(Movie::getId).distinct()),;



    final Function<Stream<Movie>, Stream<String>> func;

    Extractor(Function<Stream<Movie>, Stream<String>> func) {
        this.func = func;
    }
}
