import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        assertEquals(8, dataHandler.findNumberOfAttributesByYear(movieListPositiveTestFlow, 1975, AttributeExtractor.UNIQUE_MOVIE_IDS.func));
        assertEquals(0, dataHandler.findNumberOfAttributesByYear(emptyMovieList, 1975, AttributeExtractor.UNIQUE_MOVIE_IDS.func));
        assertEquals(13, dataHandler.findNumberOfAttributesByYear(movieListEdgeCaseTestFlow, 1975, AttributeExtractor.UNIQUE_MOVIE_IDS.func));

    }

    @Test
    void findRuntimeOfLongestMovie() {
        assertEquals(220, dataHandler.findRuntimeStatistics(movieListPositiveTestFlow, NumberTarget.MAX_RUNTIME.func));
        assertEquals(0, dataHandler.findRuntimeStatistics(emptyMovieList, NumberTarget.MAX_RUNTIME.func));
        assertEquals(220, dataHandler.findRuntimeStatistics(movieListEdgeCaseTestFlow, NumberTarget.MAX_RUNTIME.func));

    }

    @Test
    void findNumberOfUniqueGenresByYear() {
        assertEquals(2, dataHandler.findNumberOfAttributesByYear(movieListPositiveTestFlow,1975, AttributeExtractor.UNIQUE_GENRES.func));
        assertEquals(0, dataHandler.findNumberOfAttributesByYear(emptyMovieList, 1975, AttributeExtractor.UNIQUE_GENRES.func));
        assertEquals(3, dataHandler.findNumberOfAttributesByYear(movieListEdgeCaseTestFlow, 1975, AttributeExtractor.UNIQUE_GENRES.func));

    }

    @Test
    void findActorsByHighestRatedMovie() {
        assertEquals(List.of("Single Actor in most movies", "What"), dataHandler.findAttributesByNumberComparison(movieListPositiveTestFlow, AttributeExtractor.UNIQUE_ACTORS.func, NumberComparable.RATING.func, NumberTarget.MAX_RATING.func));
        assertEquals(List.of(), dataHandler.findAttributesByNumberComparison(emptyMovieList, AttributeExtractor.UNIQUE_ACTORS.func, NumberComparable.RATING.func, NumberTarget.MAX_RATING.func));
        assertEquals(List.of("Actor 2 in most movies", "What 1","Actor 1 in most movies", "What 2"), dataHandler.findAttributesByNumberComparison(movieListEdgeCaseTestFlow, AttributeExtractor.UNIQUE_ACTORS.func, NumberComparable.RATING.func, NumberTarget.MAX_RATING.func));

    }

    @Test
    void findMovieWithLeastNumberOfActors() {
        assertEquals(List.of("Movie Title With Least Actors"), dataHandler.findAttributesByNumberComparison(movieListPositiveTestFlow, AttributeExtractor.UNIQUE_TITLES.func, NumberComparable.CAST_SIZE.func, NumberTarget.MAX_CAST_SIZE.func));
        assertEquals(List.of(), dataHandler.findAttributesByNumberComparison(emptyMovieList, AttributeExtractor.UNIQUE_TITLES.func, NumberComparable.CAST_SIZE.func, NumberTarget.MAX_CAST_SIZE.func));
        assertEquals(List.of("Movie 1 Title With Least Actors","Movie 2 Title With Least Actors", "Movie 3 Title With Least Actors"), dataHandler.findAttributesByNumberComparison(movieListEdgeCaseTestFlow, AttributeExtractor.UNIQUE_TITLES.func, NumberComparable.CAST_SIZE.func, NumberTarget.MAX_CAST_SIZE.func));
    }

    @Test
    void findNumberOfActorsStarringInMultipleMovies() {
        assertEquals(2, dataHandler.findNumberOfAttributes(movieListPositiveTestFlow, AttributeExtractor.DUPLICATE_ACTORS.func));
        assertEquals(0, dataHandler.findNumberOfAttributes(emptyMovieList, AttributeExtractor.DUPLICATE_ACTORS.func));
    }

    @Test
    void findActorsFoundInMostMovies() {
        assertEquals(List.of("Single Actor in most movies"), dataHandler.findMappedAttributeByNumberComparison(movieListPositiveTestFlow, NumberTarget.MAX_ACTOR_APPEARANCE.func, Mapper.ACTOR_APPEARANCE.func));
        assertEquals(List.of(), dataHandler.findMappedAttributeByNumberComparison(emptyMovieList, NumberTarget.MAX_ACTOR_APPEARANCE.func, Mapper.ACTOR_APPEARANCE.func));
        assertEquals(List.of("Actor 1 in most movies", "Actor 2 in most movies"), dataHandler.findMappedAttributeByNumberComparison(movieListEdgeCaseTestFlow, NumberTarget.MAX_ACTOR_APPEARANCE.func, Mapper.ACTOR_APPEARANCE.func));
    }

    @Test
    void findNumberOfUniqueLanguagesInMovies() {
        assertEquals(2, dataHandler.findNumberOfAttributes(movieListPositiveTestFlow, AttributeExtractor.UNIQUE_LANGUAGES.func));
        assertEquals(0, dataHandler.findNumberOfAttributes(emptyMovieList, AttributeExtractor.UNIQUE_LANGUAGES.func));
        assertEquals(3, dataHandler.findNumberOfAttributes(movieListEdgeCaseTestFlow, AttributeExtractor.UNIQUE_LANGUAGES.func));
    }

    @Test
    void moviesHaveDuplicatesOfTitles() {
        assertTrue(dataHandler.moviesHaveDuplicatesOfAttributes(movieListPositiveTestFlow, AttributeExtractor.ALL_TITLES.func));
        assertFalse(dataHandler.moviesHaveDuplicatesOfAttributes(emptyMovieList, AttributeExtractor.ALL_TITLES.func));
        assertFalse(dataHandler.moviesHaveDuplicatesOfAttributes(movieListEdgeCaseTestFlow, AttributeExtractor.ALL_TITLES.func));
    }
}