import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class MovieDataHandler {


    public MovieDataHandler() {

    }

    public int findRuntimeStatistics(List<Movie> movieList,
                                     Function<List<Movie>, ? extends Number> target) {

        return target.apply(movieList).intValue();
    }

    public long findNumberOfAttributes(List<Movie> movieList,
                                       Function<Stream<Movie>, Stream<String>> attributeExtractor) {

        return attributeExtractor.apply(movieList.stream()).count();
    }


    public long findNumberOfAttributesByYear(List<Movie> movieList,
                                             int year,
                                             Function<Stream<Movie>, Stream<String>> attributeExtractor) {

        return attributeExtractor.apply(movieList.stream().filter(movie1-> movie1.getYear() == year))
                        .count();
    }



    public List<String> findAttributesByNumberComparison(List<Movie> movieList,
                                                         Function<Stream<Movie>, Stream<String>> attributeExtractor,
                                                         Function<Movie, ? extends Number> comparable,
                                                         Function<List<Movie>, ? extends Number> target) {

        return attributeExtractor.apply(movieList.stream().filter(movie -> comparable.apply(movie).equals(target.apply(movieList))))
                        .toList();
    }




    public List<String> findMappedAttributeByNumberComparison(List<Movie> movieList,
                                                              Function<List<Movie>, ? extends Number> target,
                                                              Function<List<Movie>, Map<String,Long>> mapper) {

        return mapper.apply(movieList).entrySet().stream().filter(entry -> entry.getValue().equals(target.apply(movieList)))
                                                          .map(Map.Entry::getKey)
                                                          .toList();
    }



    public boolean moviesHaveDuplicatesOfAttributes(List<Movie> movieList,
                                                    Function<Stream<Movie>, Stream<String>> attributeExtractor) {

        return attributeExtractor.apply(movieList.stream()).distinct().count() < attributeExtractor.apply(movieList.stream()).count();

    }
}
