import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class MatcherTest {
    @Test
    public void givenNonMatchingWord_returnsNoMatch() {
        String pattern = "aaaa";
        String givenWord = "aaab";

        Matcher matcher = new Matcher(pattern, givenWord);
        List<Integer> matches = matcher.match();

        assertEquals(emptyList(), matches);
    }

    @Test
    public void givenMatchingWord_returnsMatch() {
        String pattern = "ababaca";
        String givenWord = "abababacaba";
        List<Integer> expectedMatches = List.of(2);

        Matcher matcher = new Matcher(pattern, givenWord);
        List<Integer> actualMatches = matcher.match();

        assertEquals(expectedMatches, actualMatches);
    }

    @Test
    public void givenMatchingWord_returnsMultipleMatches() {
        String pattern = "ab";
        String givenWord = "cccabcccabcab";
        List<Integer> expectedMatches = List.of(3, 8, 11);

        Matcher matcher = new Matcher(pattern, givenWord);
        List<Integer> actualMatches = matcher.match();

        assertEquals(expectedMatches, actualMatches);
    }


    @Test
    public void wordWithDifferentAlphabet_ReturnsNoMatches() {
        String pattern = "ababab";
        String givenWord = "cdcdcdcd";

        Matcher matcher = new Matcher(pattern, givenWord);
        List<Integer> actualMatches = matcher.match();

        assertEquals(emptyList(), actualMatches);
    }


    @Test
    public void overlappingMatches_ReturnsAllResults() {
        String pattern = "aba";
        String givenWord = "ababa";
        List<Integer> expectedMatches = List.of(0, 2);

        Matcher matcher = new Matcher(pattern, givenWord);
        List<Integer> actualMatches = matcher.match();

        assertEquals(expectedMatches, actualMatches);
    }
}