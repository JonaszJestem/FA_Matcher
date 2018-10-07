import java.util.*;
import java.util.logging.Logger;

class Matcher {
    private final String pattern;
    private final int acceptanceState;
    private final String word;
    private final Map<Integer, Map<Character, Integer>> stateMachine;
    private final char[] alphabet;
    private int currentState;

    private Logger logger = Logger.getLogger("Matcher_debug");

    Matcher(String pattern, String word) {
        this.pattern = pattern;
        this.acceptanceState = pattern.length();
        this.word = word;

        alphabet = getAlphabet(word);
        log("Alphabet: \t" + Arrays.toString(alphabet));
        this.stateMachine = buildStateMachine();
        currentState = 0;
    }

    public List<Integer> match() {
        List<Integer> matches = new ArrayList<>();

        for (int currentCharIndex = 0; currentCharIndex < word.length(); currentCharIndex++) {
            Map<Character, Integer> paths = stateMachine.get(currentState);
            currentState = paths.get(word.charAt(currentCharIndex));

            if (currentState == acceptanceState) {
                matches.add(getMatchBeginning(currentCharIndex));
            }
        }

        return matches;
    }

    private int getMatchBeginning(int i) {
        return i + 1 - pattern.length();
    }

    private Map<Integer, Map<Character, Integer>> buildStateMachine() {
        Map<Integer, Map<Character, Integer>> stateMachine = new HashMap<>();

        for (int state = 0; state <= acceptanceState; state++) {
            log("\nBuilding step:\t" + state);
            Map<Character, Integer> possiblePaths = findPossiblePaths(state);
            stateMachine.put(state, possiblePaths);
        }

        return stateMachine;
    }

    private Map<Character, Integer> findPossiblePaths(int currentState) {
        Map<Character, Integer> paths = new HashMap<>();

        log("Current pattern: " + pattern.substring(0, currentState));
        for (char character : alphabet) {
            if (matchesNextState(character, currentState)) {
                paths.put(character, currentState + 1);

                log(character + " matches next state " + (currentState + 1));
            } else {
                int nextStep = SuffixFunction.find(pattern.substring(0, currentState) + character);
                paths.put(character, nextStep);

                log("Next step for " + character + " is " + nextStep);
            }
        }

        return paths;
    }

    private boolean matchesNextState(char usingCharacter, int currentState) {
        return currentState < acceptanceState && usingCharacter == pattern.charAt(currentState);
    }


    static char[] getAlphabet(String word) {
        int[] distinctChars = word.chars().distinct().toArray();

        StringBuilder alphabet = new StringBuilder();
        for (int distinctChar : distinctChars) {
            alphabet.append((char) distinctChar);
        }
        return alphabet.toString().toCharArray();
    }

    private void log(String message) {
        logger.finest(message);
    }
}
