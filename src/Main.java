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

            // Skriver ut alla filmer
            for (Movie movie : movieList) {
                System.out.println(movie);
            }

            MovieDataHandler movieDataHandler = new MovieDataHandler();


            System.out.println(movieDataHandler.findNumberOfMoviesByYear(movieList, 1975));

            System.out.println(movieDataHandler.findRuntimeOfLongestMovie(movieList));

            System.out.println(movieDataHandler.findNumberOfUniqueAttributesByYear(movieList, 1975, Extractor.UNIQUE_GENRES.extractFunc));

            System.out.println(movieDataHandler.findAttributesByHighestRatedMovie(movieList, Extractor.UNIQUE_ACTORS.extractFunc));

            System.out.println(movieDataHandler.findMovieTitleWithLeastNumberOfActors(movieList));

            System.out.println(movieDataHandler.findNumberOfAttributesPresentInMultipleMovies(movieList, Mapper.ACTOR_APPEARANCE.mapFunc));

            System.out.println(movieDataHandler.findAttributeFoundInMostMovies(movieList, Mapper.ACTOR_APPEARANCE.mapFunc));

            System.out.println(movieDataHandler.findNumberOfUniqueAttributesInMovies(movieList, Extractor.UNIQUE_LANGUAGES.extractFunc));

            System.out.println(movieDataHandler.moviesHaveDuplicatesOfTitles(movieList));


        } catch (NoSuchElementException e) {
            System.out.println("Empty movie list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
