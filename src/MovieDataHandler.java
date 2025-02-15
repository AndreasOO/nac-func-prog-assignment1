import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieDataHandler {
    private final List<Movie> movieList;

    private final Predicate<Movie> filterByUniqueId;
    private final Function<List<Movie>, Double> findHighestRating = movies -> movies.stream().mapToDouble(Movie::getImdbRating).max().orElse(0.0);


    public MovieDataHandler(List<Movie> movieList) {
        this.movieList = movieList;
        filterByUniqueId = (movie1 -> (movieList.stream().filter(movie2 -> movie1.getId().equals(movie2.getId()))).count() == 1);


    }

    private static double getDoubleFromList(Function<List<Movie>, Double> func, List<Movie> list) {
        System.out.println("running function");
        return func.apply(list);
    }

    // filter out duplicate ids
    public long findNumberOfMoviesByYear(int year) {
        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie1-> movie1.getYear() == year)
                                 .count();
    }

    public int findRuntimeOfLongestMovie(List<Movie> movieList) {
        return movieList.stream().filter(filterByUniqueId)
                                 .mapToInt(Movie::getRuntime)
                                 .max()
                                 .orElse(0);
    }

    public long findNumberOfUniqueGenresByYear(List<Movie> movieList, int year) {
        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie1-> movie1.getYear() == year)
                                 .map(Movie::getGenres)
                                 .distinct()
                                 .count();
    }

    // FIX HIGHER ORDER FUNCTION IT RUNS EVERY TIME == BAD
    public List<String> findActorsByHighestRatedMovie(List<Movie> movieList) {
        return movieList.stream().filter(filterByUniqueId)
                                 .filter(movie -> movie.getImdbRating() == getDoubleFromList(findHighestRating, movieList))
                                 .map(Movie::getCast)
                                 .flatMap(List::stream)
                                 .distinct()
                                 .collect(Collectors.toList());
    }

    // FIX WITH HIGHER ORDER FUNC
    // join with ", shared place with movie: "
    public String findMovieTitleWithLeastNumberOfActors(List<Movie> movieList) {
        int leastNumberOfActors = movieList.stream().filter(filterByUniqueId).mapToInt(movie -> movie.getCast().size()).min().orElseThrow();
        return movieList.stream().filter(filterByUniqueId).filter(movie -> movie.getCast().size() == leastNumberOfActors).map(Movie::getTitle).collect(Collectors.joining(", shared place with movie: "));
    }

    public long findNumberOfActorsStarringInTwoOrMoreMovies(List<Movie> movieList) {
        return movieList.stream().filter(filterByUniqueId)
                                 .map(Movie::getCast)
                                 .flatMap(List::stream)
                                 .distinct()
                                 .filter(actor -> movieList.stream().filter(movie -> movie.getCast()
                                                                          .contains(actor))
                                                                          .count() > 1)
                                 .count();
    }

    // HORRIBLE FIX IT
    public String findActorsFoundInMostMovies(List<Movie> movieList) {
        Map<String,Long> mapOfActorsCountedInMovies = movieList.stream().filter(filterByUniqueId).map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

        long max = mapOfActorsCountedInMovies.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getValue();

        return mapOfActorsCountedInMovies.entrySet().stream().filter(entry -> entry.getValue() == max).map(Map.Entry::getKey).collect(Collectors.joining(", shared place with actor: "));
    }

    public long findNumberOfUniqueLanguagesInMovies(List<Movie> movieList) {
        return movieList.stream().filter(filterByUniqueId).map(Movie::getLanguages).flatMap(List::stream).distinct().count();
    }

    // contestant for higher level function
    public boolean moviesHaveDuplicatesOfTitles(List<Movie> movieList) {
        return movieList.stream().filter(filterByUniqueId).map(Movie::getTitle).distinct().count() < movieList.stream().filter(filterByUniqueId).count();
    }




}
