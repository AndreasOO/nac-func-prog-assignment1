import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


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

    MovieDataHandler dataHandler = new MovieDataHandler();

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
            new Movie("573a139ef29313caabcfc179", "Movie 3 Title With Least Actors", 1975, List.of("Comedy"), "John Joe", List.of("Jimmy"), 3.8, List.of("English"), 120)
           );


    List<Movie> emptyMovieList = new ArrayList<>();


    @Test
    void findNumberOfMoviesByYear() {
        assertEquals(8, dataHandler.findNumberOfMoviesByYear(movieListPositiveTestFlow, 1975));
        assertEquals(0, dataHandler.findNumberOfMoviesByYear(emptyMovieList, 1975));
        assertEquals(13, dataHandler.findNumberOfMoviesByYear(movieListEdgeCaseTestFlow, 1975));

    }

    @Test
    void findRuntimeOfLongestMovie() {
        assertEquals(220, dataHandler.findRuntimeStatistics(movieListPositiveTestFlow, IntegerTarget.MAX_RUNTIME.func));
        assertEquals(0, dataHandler.findRuntimeStatistics(emptyMovieList, IntegerTarget.MAX_RUNTIME.func));
        assertEquals(220, dataHandler.findRuntimeStatistics(movieListEdgeCaseTestFlow, IntegerTarget.MAX_RUNTIME.func));

    }

    @Test
    void findNumberOfUniqueGenresByYear() {
        assertEquals(2, dataHandler.findNumberOAttributesByYear(movieListPositiveTestFlow,1975, Extractor.UNIQUE_GENRES.func));
        assertEquals(0, dataHandler.findNumberOAttributesByYear(emptyMovieList, 1975, Extractor.UNIQUE_GENRES.func));
        assertEquals(3, dataHandler.findNumberOAttributesByYear(movieListEdgeCaseTestFlow, 1975, Extractor.UNIQUE_GENRES.func));

    }

    @Test
    void findActorsByHighestRatedMovie() {
        assertEquals(List.of("Single Actor in most movies", "What"), dataHandler.findAttributesByDoubleComparison(movieListPositiveTestFlow, Extractor.UNIQUE_ACTORS.func, DoubleComparable.RATING.func, DoubleTarget.MAX_RATING.func));
        assertEquals(List.of(), dataHandler.findAttributesByDoubleComparison(emptyMovieList, Extractor.UNIQUE_ACTORS.func, DoubleComparable.RATING.func, DoubleTarget.MAX_RATING.func));
        assertEquals(List.of("Actor 2 in most movies", "What 1","Actor 1 in most movies", "What 2"), dataHandler.findAttributesByDoubleComparison(movieListEdgeCaseTestFlow, Extractor.UNIQUE_ACTORS.func, DoubleComparable.RATING.func, DoubleTarget.MAX_RATING.func));

    }

    @Test
    void findMovieWithLeastNumberOfActors() {
        assertEquals(List.of("Movie Title With Least Actors"), dataHandler.findAttributeByIntegerComparison(movieListPositiveTestFlow, Extractor.UNIQUE_TITLES.func, IntegerComparable.CAST_SIZE.func, IntegerTarget.MAX_CAST_SIZE.func));
        assertEquals(List.of(), dataHandler.findAttributeByIntegerComparison(emptyMovieList, Extractor.UNIQUE_TITLES.func, IntegerComparable.CAST_SIZE.func, IntegerTarget.MAX_CAST_SIZE.func));
        assertEquals(List.of("Movie 1 Title With Least Actors","Movie 2 Title With Least Actors", "Movie 3 Title With Least Actors"), dataHandler.findAttributeByIntegerComparison(movieListEdgeCaseTestFlow, Extractor.UNIQUE_TITLES.func, IntegerComparable.CAST_SIZE.func, IntegerTarget.MAX_CAST_SIZE.func));
    }

    @Test
    void findNumberOfActorsStarringInMultipleMovies() {
        assertEquals(2, dataHandler.findNumberOfAttributesPresentInMultipleMovies(movieListPositiveTestFlow, Mapper.ACTOR_APPEARANCE.func));
        assertThrows(NoSuchElementException.class, () ->  dataHandler.findNumberOfAttributesPresentInMultipleMovies(emptyMovieList, Mapper.ACTOR_APPEARANCE.func));
    }

    @Test
    void findActorsFoundInMostMovies() {
        assertEquals(List.of("Single Actor in most movies"), dataHandler.findAttributeFoundInMostMovies(movieListPositiveTestFlow, Mapper.ACTOR_APPEARANCE.func));
        assertThrows(NoSuchElementException.class, () ->  dataHandler.findAttributeFoundInMostMovies(emptyMovieList, Mapper.ACTOR_APPEARANCE.func));
        assertEquals(List.of("Actor 1 in most movies", "Actor 2 in most movies"), dataHandler.findAttributeFoundInMostMovies(movieListEdgeCaseTestFlow, Mapper.ACTOR_APPEARANCE.func));
    }

    @Test
    void findNumberOfUniqueLanguagesInMovies() {
        assertEquals(2, dataHandler.findNumberOfAttributesInMovies(movieListPositiveTestFlow, Extractor.UNIQUE_LANGUAGES.func));
        assertEquals(0, dataHandler.findNumberOfAttributesInMovies(emptyMovieList, Extractor.UNIQUE_LANGUAGES.func));
        assertEquals(3, dataHandler.findNumberOfAttributesInMovies(movieListEdgeCaseTestFlow, Extractor.UNIQUE_LANGUAGES.func));
    }

    @Test
    void moviesHaveDuplicatesOfTitles() {
        assertTrue(dataHandler.moviesHaveDuplicatesOfAttributes(movieListPositiveTestFlow, Extractor.UNIQUE_TITLES.func, Extractor.ALL_TITLES.func));
        assertFalse(dataHandler.moviesHaveDuplicatesOfAttributes(emptyMovieList, Extractor.UNIQUE_TITLES.func, Extractor.ALL_TITLES.func));
        assertFalse(dataHandler.moviesHaveDuplicatesOfAttributes(movieListEdgeCaseTestFlow, Extractor.UNIQUE_TITLES.func, Extractor.ALL_TITLES.func));
    }
}