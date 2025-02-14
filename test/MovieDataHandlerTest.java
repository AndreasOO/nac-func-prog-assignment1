import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MovieDataHandlerTest {

    MovieDataHandler movieDataHandler = new MovieDataHandler();

    List<Movie> movieListPositiveTestFlow = List.of(
            new Movie("573a139ef29313caabcfc151", "Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne", "Trick Name"), 8.5, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc152", "Movie Title With Most Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne", "Patrick"), 2.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc153", "Movie Title With Least Actors", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Marc Dwayne"), 3.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc154", "Duplicate Movie Title", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Its late"), 5.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc155", "Movie Title With Longest Runtime", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Bad Name"), 8.8, List.of("English", "Swedish"), 220),
            new Movie("573a139ef29313caabcfc156", "Movie Title With Highest IMDB rating", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "What"), 9.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc157", "Movie Title 3", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Texas Ranger"), 1.8, List.of("English", "Swedish"), 120),
            new Movie("573a139ef29313caabcfc166", "Movie Title 4", 1975, List.of("Romance", "Comedy"), "John Joe", List.of("Single Actor in most movies", "Milky Way"), 3.8, List.of("English", "Swedish"), 120)
    );


    List<Movie> emptyMovieList = new ArrayList<>();


    @Test
    void findNumberOfMoviesByYear() {
        assertEquals(8, movieDataHandler.findNumberOfMoviesByYear(movieListPositiveTestFlow, 1975));
        assertEquals(0, movieDataHandler.findNumberOfMoviesByYear(emptyMovieList, 1975));

    }

    @Test
    void findRuntimeOfLongestMovie() {
        assertEquals(220, movieDataHandler.findRuntimeOfLongestMovie(movieListPositiveTestFlow));
        assertEquals(0, movieDataHandler.findRuntimeOfLongestMovie(emptyMovieList));

    }

    @Test
    void findNumberOfUniqueGenresByYear() {
        assertEquals(2, movieDataHandler.findNumberOfUniqueGenresByYear(movieListPositiveTestFlow, 1975));
        assertEquals(0, movieDataHandler.findNumberOfUniqueGenresByYear(emptyMovieList, 1975));

    }

    @Test
    void findActorsByHighestRatedMovie() {
        assertEquals(List.of("Single Actor in most movies", "What"), movieDataHandler.findActorsByHighestRatedMovie(movieListPositiveTestFlow));
        assertEquals(List.of(), movieDataHandler.findActorsByHighestRatedMovie(emptyMovieList));

    }

    @Test
    void findMovieWithLeastNumberOfActors() {
        assertEquals("Movie Title With Least Actors", movieDataHandler.findMovieWithLeastNumberOfActors(movieListPositiveTestFlow));

        //replace with NoSuchElementException that comes with Optional.elseThrow()?
        assertNull(movieDataHandler.findMovieWithLeastNumberOfActors(emptyMovieList));
    }

    @Test
    void findNumberOfActorsStarringInTwoOrMoreMovies() {
        assertEquals(2, movieDataHandler.findNumberOfActorsStarringInTwoOrMoreMovies(movieListPositiveTestFlow));
        assertEquals(0, movieDataHandler.findNumberOfActorsStarringInTwoOrMoreMovies(emptyMovieList));
    }

    @Test
    void findActorsFoundInMostMovies() {
        assertEquals("Single Actor in most movies", movieDataHandler.findActorsFoundInMostMovies(movieListPositiveTestFlow));

        //replace with NoSuchElementException that comes with Optional.elseThrow()?
        assertNull(movieDataHandler.findActorsFoundInMostMovies(emptyMovieList));

        // add duplicate with the joining...
    }

    @Test
    void findNumberOfUniqueLanguagesInMovies() {
        assertEquals(2, movieDataHandler.findNumberOfUniqueLanguagesInMovies(movieListPositiveTestFlow));
        assertEquals(0, movieDataHandler.findNumberOfUniqueLanguagesInMovies(emptyMovieList));
    }

    @Test
    void moviesHaveDuplicatesOfTitles() {
        assertTrue(movieDataHandler.moviesHaveDuplicatesOfTitles(movieListPositiveTestFlow));
        assertFalse(movieDataHandler.moviesHaveDuplicatesOfTitles(emptyMovieList));
    }
}