import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieDataHandler {


    public MovieDataHandler() {

    }


    // filter out duplicate ids
    public long findNumberOfMoviesByYear(List<Movie> movieList, int year) {
        return movieList.stream().filter(movie1-> movie1.getYear() == year)
                                                              .count();
    }

    public int findRuntimeOfLongestMovie(List<Movie> movieList) {
        return movieList.stream().mapToInt(Movie::getRuntime)
                                                              .max()
                                                              .orElse(0);
    }

    public long findNumberOfUniqueAttributesByYear(List<Movie> movieList, int year, Function<Stream<Movie>, Stream<String>> extractor) {
        return extractor.apply(movieList.stream().filter(movie1-> movie1.getYear() == year)).count();
    }


    public List<String> findAttributesByHighestRatedMovie(List<Movie> movieList, Function<Stream<Movie>, Stream<String>> extractor) {
        double highestRating = movieList.stream().mapToDouble(Movie::getImdbRating)
                                                 .max()
                                                 .orElse(0.0);

        return extractor.apply(movieList.stream()
                                                  .filter(movie -> movie.getImdbRating() == highestRating))
                                  .toList();
    }


    public String findMovieTitleWithLeastNumberOfActors(List<Movie> movieList) {
        return movieList.stream().filter(movie -> movie.getCast().size() == movieList.stream()
                                                                                                   .mapToInt(movie2 -> movie2.getCast().size())
                                                                                                   .min()
                                                                                                   .orElseThrow())
                                                              .map(Movie::getTitle)
                                                              .collect(Collectors.joining(", shared place with: "));
    }

    public long findNumberOfAttributesPresentInMultipleMovies(List<Movie> movieList, Function<List<Movie>, Optional<Map<String,Long>>> mapper) {
        return mapper.apply(movieList)
                                         .orElseThrow()
                                         .values()
                                         .stream()
                                         .filter(starring -> starring > 1)
                                         .count();
    }


    public String findAttributeFoundInMostMovies(List<Movie> movieList, Function<List<Movie>, Optional<Map<String,Long>>> mapper) {
        return mapper.apply(movieList)
                                         .orElseThrow()
                                         .entrySet()
                                         .stream().filter(entry -> entry.getValue() == mapper.apply(movieList)
                                                                                                                                 .orElseThrow()
                                                                                                                                 .values()
                                                                                                                                 .stream().mapToLong(Long::longValue)
                                                                                                                                          .max()
                                                                                                                                          .orElseThrow())
                                                  .map(Map.Entry::getKey)
                                                  .collect(Collectors.joining(", shared place with: "));
    }


    public long findNumberOfUniqueAttributesInMovies(List<Movie> movieList, Function<Stream<Movie>, Stream<String>> extractor) {
        return extractor.apply(movieList.stream()).count();
    }



    public boolean moviesHaveDuplicatesOfTitles(List<Movie> movieList) {
        return movieList.stream().map(Movie::getTitle)
                                                              .distinct()
                                                              .count() < (long) movieList.size();

    }




}
