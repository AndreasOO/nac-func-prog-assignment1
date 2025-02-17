import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MovieDataHandlerTest {



    List<Movie> movieListPositiveTestFlow = List.of(
            new Movie("573a139ef29313caabcfc151", "Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne", "Trick Name"), 8.5, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc152", "Movie Title With Most Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne", "Patrick", "Carl"), 2.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc153", "Movie Title With Least Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne"), 3.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc154", "Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Its late"), 5.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc155", "Movie Title With Longest Runtime", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Bad Name"), 8.8, List.of("English", "Swedish"), 220),
            new Movie("573a139ef29313caabcfc156", "Movie Title With Highest IMDB rating", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "What"), 9.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc157", "Movie Title 3", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Texas Ranger"), 1.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc166", "Movie Title 4", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Milky Way"), 3.8, List.of("English", "Swedish"), 120)
    );

    MovieDataHandler movieDataHandlerPositiveTestFlow = new MovieDataHandler(movieListPositiveTestFlow);

    List<Movie> movieListEdgeCaseTestFlow = List.of(
            new Movie("573a139ef29313caabcfc167", "Test 2 non Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 2 in most movies", "Trick Name"), 8.5, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc168", "Movie 1 Title With Most Actors", 1975, List.of("Action", "Comedy"), "John Joe", List.of("Actor 2 in most movies", "Patrick", "Carl"), 2.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc169", "Movie 2 Title With Most Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 2 in most movies", "Patrick", "Sophie"), 3.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc170", "Movie 1 Title With Least Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne"), 3.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc171", "Movie 2 Title With Least Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Philip Phony"), 3.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc172", "Test 1 non Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 2 in most movies", "Its late"), 5.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc173", "Movie Title 1 With Longest Runtime", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 1 in most movies", "Bad Name"), 8.8, List.of("English", "Swedish"), 220),
            new Movie("573a139ef29313caabcfc174", "Movie Title 2 With Longest Runtime", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 1 in most movies", "Bad Name"), 8.8, List.of("English", "Swedish"), 220),
            new Movie("573a139ef29313caabcfc175", "Movie Title 1 With Highest IMDB rating", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 2 in most movies", "What 1"), 9.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc176", "Movie Title 2 With Highest IMDB rating", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 1 in most movies", "What 2"), 9.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc177", "Movie Title With Duplicate Languages", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Actor 1 in most movies", "Texas Ranger"), 1.8, List.of("French", "French"), 120),
            new Movie("573a139ef29313caabcfc178", "Movie Title With Duplicate Genres", 1975, List.of("Comedy", "Comedy"), "John Joe", List.of("Actor 1 in most movies", "Milky Way"), 3.8, List.of("English"), 120),
            new Movie("573a139ef29313caabcfc179", "Movie Title With Duplicate Id", 1975, List.of("Comedy"), "John Joe", List.of("Jimmy"), 3.8, List.of("English"), 120),
            new Movie("573a139ef29313caabcfc179", "Movie Title With Duplicate Id", 1975, List.of("Comedy"), "John Joe", List.of("Jimmy"), 3.8, List.of("English"), 120)
    );

    MovieDataHandler movieDataHandlerEdgeCaseTestFlow = new MovieDataHandler(movieListEdgeCaseTestFlow);

    List<Movie> emptyMovieList = new ArrayList<>();
    MovieDataHandler movieDataHandlerEmptyListTestFlow = new MovieDataHandler(emptyMovieList);

    @Test
    void findNumberOfMoviesByYear() {
        assertEquals(8, movieDataHandlerPositiveTestFlow.findNumberOfMoviesByYear(1975));
        assertEquals(0, movieDataHandlerEmptyListTestFlow.findNumberOfMoviesByYear(1975));
        assertEquals(12, movieDataHandlerEdgeCaseTestFlow.findNumberOfMoviesByYear(1975));

    }

    @Test
    void findRuntimeOfLongestMovie() {
        assertEquals(220, movieDataHandlerPositiveTestFlow.findRuntimeOfLongestMovie());
        assertEquals(0, movieDataHandlerEmptyListTestFlow.findRuntimeOfLongestMovie());
        assertEquals(220, movieDataHandlerEdgeCaseTestFlow.findRuntimeOfLongestMovie());

    }

    @Test
    void findNumberOfUniqueGenresByYear() {
        assertEquals(2, movieDataHandlerPositiveTestFlow.findNumberOfUniqueGenresByYear(1975));
        assertEquals(0, movieDataHandlerEmptyListTestFlow.findNumberOfUniqueGenresByYear(1975));
        assertEquals(3, movieDataHandlerEdgeCaseTestFlow.findNumberOfUniqueGenresByYear(1975));

    }

    @Test
    void findActorsByHighestRatedMovie() {
        assertEquals(List.of("Single Actor in most movies", "What"), movieDataHandlerPositiveTestFlow.findActorsByHighestRatedMovie());
        assertEquals(List.of(), movieDataHandlerEmptyListTestFlow.findActorsByHighestRatedMovie());
        assertEquals(List.of("Actor 2 in most movies", "What 1","Actor 1 in most movies", "What 2"), movieDataHandlerEdgeCaseTestFlow.findActorsByHighestRatedMovie());

    }

    @Test
    void findMovieWithLeastNumberOfActors() {
        assertEquals("Movie Title With Least Actors", movieDataHandlerPositiveTestFlow.findMovieTitleWithLeastNumberOfActors());

        //replace with NoSuchElementException that comes with Optional.elseThrow()?
        assertThrows(NoSuchElementException.class, () -> movieDataHandlerEmptyListTestFlow.findMovieTitleWithLeastNumberOfActors());
        assertEquals("Movie 1 Title With Least Actors, shared place with movie: Movie 2 Title With Least Actors", movieDataHandlerEdgeCaseTestFlow.findMovieTitleWithLeastNumberOfActors());
    }

    @Test
    void findNumberOfActorsStarringInMultipleMovies() {
        assertEquals(2, movieDataHandlerPositiveTestFlow.findNumberOfActorsStarringInMultipleMovies());
        assertThrows(NoSuchElementException.class, () ->  movieDataHandlerEmptyListTestFlow.findNumberOfActorsStarringInMultipleMovies());
    }

    @Test
    void findActorsFoundInMostMovies() {
        assertEquals("Single Actor in most movies", movieDataHandlerPositiveTestFlow.findActorFoundInMostMovies());

        assertThrows(NoSuchElementException.class, () ->  movieDataHandlerEmptyListTestFlow.findActorFoundInMostMovies());

        // add duplicate with the joining...
        assertEquals("Actor 1 in most movies, shared place with actor: Actor 2 in most movies", movieDataHandlerEdgeCaseTestFlow.findActorFoundInMostMovies());
    }

    @Test
    void findNumberOfUniqueLanguagesInMovies() {
        assertEquals(2, movieDataHandlerPositiveTestFlow.findNumberOfUniqueLanguagesInMovies());
        assertEquals(0, movieDataHandlerEmptyListTestFlow.findNumberOfUniqueLanguagesInMovies());
        assertEquals(3, movieDataHandlerEdgeCaseTestFlow.findNumberOfUniqueLanguagesInMovies());
    }

    @Test
    void moviesHaveDuplicatesOfTitles() {
        assertTrue(movieDataHandlerPositiveTestFlow.moviesHaveDuplicatesOfTitles());
        assertFalse(movieDataHandlerEmptyListTestFlow.moviesHaveDuplicatesOfTitles());
        assertFalse(movieDataHandlerEdgeCaseTestFlow.moviesHaveDuplicatesOfTitles());
    }
}