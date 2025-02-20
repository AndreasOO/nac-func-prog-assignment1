import com.mongodb.client.*;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Main {

    public static void main(String[] args) {
        //Skriv in rätt url!
        String uri = "mongodb+srv://andreasohlander:test1234@cluster0.tmn3y.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            MovieDataHandler movieDataHandler = new MovieDataHandler();

            long resultQ1 = movieDataHandler.findNumberOAttributesByYear(movieList, 1975, Extractor.UNIQUE_MOVIE_IDS.func);
            System.out.println(resultQ1);


            int resultQ2 = movieDataHandler.findRuntimeStatistics(movieList, IntegerTarget.MAX_RUNTIME.func);
            System.out.println(resultQ2);

            long resultQ3 = movieDataHandler.findNumberOAttributesByYear(movieList, 1975, Extractor.UNIQUE_GENRES.func);
            System.out.println(resultQ3);

            List<String> resultQ4 = movieDataHandler.findAttributesByDoubleComparison(movieList, Extractor.UNIQUE_ACTORS.func, DoubleComparable.RATING.func, DoubleTarget.MAX_RATING.func);
            System.out.println(resultQ4);

            List<String> resultQ5 = movieDataHandler.findAttributeByIntegerComparison(movieList, Extractor.UNIQUE_TITLES.func, IntegerComparable.CAST_SIZE.func, IntegerTarget.MAX_CAST_SIZE.func);
            System.out.println(resultQ5);

            long resultQ6 = movieDataHandler.findNumberOfAttributesPresentInMultipleMovies(movieList, Mapper.ACTOR_APPEARANCE.func);
            System.out.println(resultQ6);

            List<String> resultQ7 = movieDataHandler.findAttributeFoundInMostMovies(movieList, Mapper.ACTOR_APPEARANCE.func);
            System.out.println(resultQ7);

            long resultQ8 = movieDataHandler.findNumberOfAttributesInMovies(movieList, Extractor.UNIQUE_LANGUAGES.func);
            System.out.println(resultQ8);

            boolean resultQ9 = movieDataHandler.moviesHaveDuplicatesOfAttributes(movieList, Extractor.UNIQUE_TITLES.func, Extractor.ALL_TITLES.func);
            System.out.println(resultQ9);


        } catch (NoSuchElementException e) {
            System.out.println("Empty movie list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
