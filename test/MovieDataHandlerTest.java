import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MovieDataHandlerTest {

    MovieDataHandler movieDataHandler = new MovieDataHandler();

    List<Movie> movieListPositiveTestFlow = List.of(
            new Movie("573a139ef29313caabcfc151", "Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne", "Trick Name"), 8.5, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc152", "Movie Title With Most Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne", "Patrick"), 2.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc153", "Movie Title With Least Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne"), 3.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc154", "Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Its late"), 5.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc155", "Movie Title With Longest Runtime", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Bad Name"), 8.8, List.of("English", "Swedish"), 220),
            new Movie("573a139ef29313caabcfc156", "Movie Title With Highest IMDB rating", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "What"), 9.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc157", "Movie Title 1", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Texas Ranger"), 1.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc166", "Movie Title 1", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Milky Way"), 3.8, List.of("English", "Swedish"), 120)
    );


    List<Movie> emptyMovieList = new ArrayList<>();


    @Test
    void findNumberOfMoviesByYear() {
            // 8
    }

    @Test
    void findRuntimeOfLongestMovie() {
        // 220
    }

    @Test
    void findNumberOfUniqueGenresByYear() {
        // 2
    }

    @Test
    void findActorsByHighestRatedMovie() {
        // "Single Actor in most movies", "What"
    }

    @Test
    void findMovieWithLeastNumberOfActors() {
        // "Movie Title With Least Actors"
    }

    @Test
    void findNumberOfActorsStarringInTwoOrMoreMovies() {
        // 2
    }

    @Test
    void findActorsFoundInMostMovies() {
        // Single Actor in most movies"
    }

    @Test
    void findNumberOfUniqueLanguagesInMovies() {
        // 2
    }

    @Test
    void moviesHaveDuplicatesOfTitles() {
        // true
    }
}