public class SuffixFunction {
    public static int find(String word) {
        int offset = 1;
        int length = word.length();

        while (offset < length) {
            String prefix = word.substring(0, length - offset);
            if (word.endsWith(prefix)) {
                return prefix.length();
            }
            offset++;
        }
        return 0;
    }
}
