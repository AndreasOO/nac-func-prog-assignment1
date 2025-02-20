import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AttributeExtractor {
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

    UNIQUE_MOVIE_IDS(stream -> stream.map(Movie::getId).distinct()),

    DUPLICATE_ACTORS(stream -> stream.map(Movie::getCast)
                                                   .flatMap(List::stream)
                                                   .collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet()
                                                   .stream().filter((entry) -> entry.getValue() > 1)
                                                            .map(Map.Entry::getKey));



    final Function<Stream<Movie>, Stream<String>> func;

    AttributeExtractor(Function<Stream<Movie>, Stream<String>> func) {
        this.func = func;
    }
}
