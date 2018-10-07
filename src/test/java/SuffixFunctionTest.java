import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuffixFunctionTest {
    @Test
    public void findsSuffixes() {
        String word = "baba";

        int actual = SuffixFunction.find(word);

        assertEquals(2, actual);
    }

    @Test
    public void findsSuffixOfLengthOne() {
        String word = "abbbbbba";

        int actual = SuffixFunction.find(word);

        assertEquals(1, actual);
    }

    @Test
    public void findsNoSuffix() {
        String word = "abcdefgh";

        int actual = SuffixFunction.find(word);

        assertEquals(0, actual);
    }


}