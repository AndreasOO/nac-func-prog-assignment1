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
                                               .distinct());

    final Function<Stream<Movie>, Stream<String>> extractFunc;

    Extractor(Function<Stream<Movie>, Stream<String>> extractFunc) {
        this.extractFunc = extractFunc;
    }
}
