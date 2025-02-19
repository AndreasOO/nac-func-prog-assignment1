import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class MovieDataHandler {


    public MovieDataHandler() {

    }

    public long findNumberOfMoviesByYear(List<Movie> movieList, int year) {
        return movieList.stream().filter(movie1-> movie1.getYear() == year)
                                                              .count();
    }

    public int findRuntimeStatistics(List<Movie> movieList,
                                     Function<List<Movie>, Integer> reducer) {

        return reducer.apply(movieList);
    }



    public long findNumberOAttributesByYear(List<Movie> movieList,
                                            int year,
                                            Function<Stream<Movie>, Stream<String>> extractor) {

        return extractor.apply(movieList.stream().filter(movie1-> movie1.getYear() == year))
                        .count();
    }



    public List<String> findAttributesByDoubleComparison(List<Movie> movieList,
                                                         Function<Stream<Movie>, Stream<String>> extractor,
                                                         Function<Movie, Double> doubleTarget,
                                                         Function<List<Movie>, Double> reducer) {

        return extractor.apply(movieList.stream().filter(movie -> (double) doubleTarget.apply(movie) == reducer.apply(movieList)))
                        .toList();
    }


    public List<String> findAttributeByNumberOfActors(List<Movie> movieList,
                                                Function<Stream<Movie>, Stream<String>> extractor,
                                                Function<List<Movie>, Integer> reducer) {

        return extractor.apply(movieList.stream().filter((movie) -> movie.getCast().size() == reducer.apply(movieList)))
                        .toList();
    }

    public long findNumberOfAttributesPresentInMultipleMovies(List<Movie> movieList,
                                                              Function<List<Movie>, Optional<Map<String,Long>>> mapper) {
        return mapper.apply(movieList).orElseThrow()
                                      .values()
                                      .stream()
                                      .filter(count -> count > 1)
                                      .count();
    }


    public List<String> findAttributeFoundInMostMovies(List<Movie> movieList,
                                                 Function<List<Movie>, Optional<Map<String,Long>>> mapper) {

        long max = mapper.apply(movieList).orElseThrow()
                                            .values()
                                            .stream().mapToLong(Long::longValue)
                                                     .max()
                                                     .orElseThrow();

        return mapper.apply(movieList).orElseThrow()
                                      .entrySet()
                                      .stream().filter(entry -> entry.getValue() == max)
                                               .map(Map.Entry::getKey)
                                               .toList();
    }


    public long findNumberOfAttributesInMovies(List<Movie> movieList,
                                               Function<Stream<Movie>, Stream<String>> extractor) {

        return extractor.apply(movieList.stream()).count();
    }



    public boolean moviesHaveDuplicatesOfAttributes(List<Movie> movieList,
                                                    Function<Stream<Movie>, Stream<String>> uniqueExtractor,
                                                    Function<Stream<Movie>, Stream<String>> allExtractor) {

        return uniqueExtractor.apply(movieList.stream()).count() < allExtractor.apply(movieList.stream()).count();

    }




}
