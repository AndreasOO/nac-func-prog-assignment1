import com.mongodb.client.*;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;


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

            MovieDataHandler movieDataHandler = new MovieDataHandler(movieList);

            System.out.println(movieDataHandler.findNumberOfMoviesByYear(1975));
            System.out.println(movieDataHandler.findRuntimeOfLongestMovie());
            System.out.println(movieDataHandler.findNumberOfUniqueGenresByYear(1975));
            System.out.println(movieDataHandler.findActorsByHighestRatedMovie());
            System.out.println(movieDataHandler.findMovieTitleWithLeastNumberOfActors());
            System.out.println(movieDataHandler.findNumberOfActorsStarringInMultipleMovies());
            System.out.println(movieDataHandler.findActorFoundInMostMovies());
            System.out.println(movieDataHandler.findNumberOfUniqueLanguagesInMovies());
            System.out.println(movieDataHandler.moviesHaveDuplicatesOfTitles());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
