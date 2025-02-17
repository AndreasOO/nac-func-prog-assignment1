import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class MovieDataHandler {
    private final List<Movie> movieList;
    private final Predicate<Movie> filterByUniqueId;



    public MovieDataHandler(List<Movie> movieList) {
        this.movieList = movieList;
        filterByUniqueId = movie1 -> movieList.stream().filter(movie2 -> movie1.getId().equals(movie2.getId()))
                                                             .count() == 1;
    }


    // filter out duplicate ids
    public long findNumberOfMoviesByYear(int year) {
        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie1-> movie1.getYear() == year)
                                 .count();
    }

    public int findRuntimeOfLongestMovie() {
        return movieList.stream().filter(filterByUniqueId)
                                 .mapToInt(Movie::getRuntime)
                                 .max()
                                 .orElse(0);
    }

    public long findNumberOfUniqueGenresByYear(int year) {
        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie1-> movie1.getYear() == year)
                                 .map(Movie::getGenres)
                                 .flatMap(List::stream)
                                 .distinct()
                                 .count();
    }


    public List<String> findActorsByHighestRatedMovie() {
        double highestRating = movieList.stream().mapToDouble(Movie::getImdbRating)
                                                 .max()
                                                 .orElse(0.0);

        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie -> movie.getImdbRating() == highestRating)
                                 .map(Movie::getCast)
                                 .flatMap(List::stream)
                                 .distinct()
                                 .toList();
    }


    public String findMovieTitleWithLeastNumberOfActors() {
        int leastNumberOfActors = movieList.stream().filter(filterByUniqueId)
                                                    .mapToInt(movie -> movie.getCast().size())
                                                    .min()
                                                    .orElseThrow();

        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie -> movie.getCast().size() == leastNumberOfActors)
                                 .map(Movie::getTitle)
                                 .collect(Collectors.joining(", shared place with movie: "));
    }

    public long findNumberOfActorsStarringInTwoOrMoreMovies() {
        return movieList.stream().filter(filterByUniqueId)
                                 .map(Movie::getCast)
                                 .flatMap(List::stream)
                                 .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                                 .values()
                                 .stream()
                                 .filter(starring -> starring > 1)
                                 .count();
    }

    // HORRIBLE FIX IT
    public String findActorFoundInMostMovies() {
        Map<String,Long> mapOfActorsCountedInMovies = movieList.stream().filter(filterByUniqueId)
                                                                        .map(Movie::getCast)
                                                                        .flatMap(List::stream)
                                                                        .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        long max = mapOfActorsCountedInMovies.values().stream().mapToLong(x -> x)
                                                               .max()
                                                               .orElseThrow();

        return mapOfActorsCountedInMovies.entrySet().stream()
                                                    .filter(entry -> entry.getValue() == max)
                                                    .map(Map.Entry::getKey)
                                                    .collect(Collectors.joining(", shared place with actor: "));
    }

    public long findNumberOfUniqueLanguagesInMovies() {
        return movieList.stream().filter(filterByUniqueId)
                                 .map(Movie::getLanguages)
                                 .flatMap(List::stream)
                                 .distinct()
                                 .count();
    }

    // contestant for higher level function
    public boolean moviesHaveDuplicatesOfTitles() {
        return movieList.stream().filter(filterByUniqueId)
                                 .map(Movie::getTitle)
                                 .distinct()
                                 .count() < movieList.stream()
                                 .filter(filterByUniqueId)
                                 .count();
    }




}
