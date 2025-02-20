import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class MovieDataHandler {


    public MovieDataHandler() {

    }

    public int findRuntimeStatistics(List<Movie> movieList,
                                     Function<List<Movie>, Integer> target) {

        return target.apply(movieList);
    }


    public long findNumberOfAttributes(List<Movie> movieList,
                                       Function<Stream<Movie>, Stream<String>> attributeExtractor) {

        return attributeExtractor.apply(movieList.stream()).count();
    }


    public long findNumberOAttributesByYear(List<Movie> movieList,
                                            int year,
                                            Function<Stream<Movie>, Stream<String>> attributeExtractor) {

        return attributeExtractor.apply(movieList.stream().filter(movie1-> movie1.getYear() == year))
                        .count();
    }



    public List<String> findAttributesByDoubleComparison(List<Movie> movieList,
                                                         Function<Stream<Movie>, Stream<String>> attributeExtractor,
                                                         Function<Movie, Double> comparable,
                                                         Function<List<Movie>, Double> target) {

        return attributeExtractor.apply(movieList.stream().filter(movie -> (double) comparable.apply(movie) == target.apply(movieList)))
                        .toList();
    }


    public List<String> findAttributeByIntegerComparison(List<Movie> movieList,
                                                         Function<Stream<Movie>, Stream<String>> attributeExtractor,
                                                         Function<Movie, Integer> comparable,
                                                         Function<List<Movie>, Integer> target) {

        return attributeExtractor.apply(movieList.stream().filter((movie) -> (int) comparable.apply(movie) == target.apply(movieList)))
                        .toList();
    }

    public List<String> findMappedAttributeByLongComparison(List<Movie> movieList,
                                                            Function<List<Movie>, Long> comparable,
                                                            Function<List<Movie>, Map<String,Long>> mapper) {


        long max = comparable.apply(movieList);

        return mapper.apply(movieList)
                .entrySet()
                .stream().filter(entry -> entry.getValue() == max)
                .map(Map.Entry::getKey)
                .toList();
    }

    public long findNumberOfAttributesPresentInMultipleMovies(List<Movie> movieList,
                                                              Function<List<Movie>, Map<String,Long>> mapper) {
        return mapper.apply(movieList).values().stream()
                                               .filter(count -> count > 1)
                                               .count();
    }







    public boolean moviesHaveDuplicatesOfAttributes(List<Movie> movieList,
                                                    Function<Stream<Movie>, Stream<String>> uniqueAttributeExtractor,
                                                    Function<Stream<Movie>, Stream<String>> allAttributeExtractor) {

        return uniqueAttributeExtractor.apply(movieList.stream()).count() < allAttributeExtractor.apply(movieList.stream()).count();

    }




}
