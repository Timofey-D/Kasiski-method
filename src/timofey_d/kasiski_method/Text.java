package timofey_d.kasiski_method;

import java.util.SortedMap;
import java.util.TreeMap;

public class Text {
    private String string;
    private final String primaryText;
    private int clearTextLength;
    private final int primaryTextLength;
    private final Alphabet language;

    public Text() {
        this(null);
    }

    public Text(String string) {
        this.primaryText = string;
        this.language = checkLanguage(this.primaryText);
        this.string = clearText(string);
        this.primaryTextLength = this.primaryText.length();
    }

    public int getClearTextLength() {
        return clearTextLength;
    }

    public int getPrimaryTextLength() {
        return primaryTextLength;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void numberOfWords() {
        System.out.println(computeNumberOfWords());
    }

    public int getNumberOfWords() {
        return computeNumberOfWords();
    }

    public Text getClearText() {
        return new Text(clearText(this.string));
    }

    public void numberOfLetters() {
        System.out.println(this.string.length());
    }

    public int getNumberOfLetters() {
        return this.string.length();
    }

    public SortedMap<Character, Integer> getEachLetters() {
        return computeEachLetters(this.string);
    }

    @Override
    public String toString() {
        return string;
    }

    private Alphabet checkLanguage(String text) {
        for (int i = 0; i < text.length(); i++) {
            if ((primaryText.charAt(i) >= 65 && primaryText.charAt(i) <= 90) ||
                    (primaryText.charAt(i) >= 97 && primaryText.charAt(i) <= 125)) {
                return new Alphabet("En");
            }
            else if ((primaryText.charAt(i) >= 1040 && primaryText.charAt(i) <= 1071) ||
                    (primaryText.charAt(i) >= 1072 && primaryText.charAt(i) <= 1103))
                return new Alphabet("Ru");
            else if (primaryText.charAt(i) >= 1488 && primaryText.charAt(i) <= 1514)
                return new Alphabet("He");
        }
        return null;
    }

    private boolean checkSymbol(char symbol) {
        for (int i = 0; i < language.getCapitalLetters().length; i++) {
            if (language.getCapitalLetters()[i] == symbol ||
                    language.getLowerLetters()[i] == symbol)
                return true;
        }
        return false;
    }

    private int computeNumberOfWords() {
        int words = 0;
        for (int i = 0; i < primaryText.length(); i++) {
            if (checkSymbol(primaryText.charAt(i))) {
                for (int j = i; j < primaryText.length(); j++) {
                    if (!checkSymbol(primaryText.charAt(j)) || j == primaryText.length() - 1) {
                        words++;
                        i = j;
                        break;
                    }
                }
            }
        }
        return words;
    }

    private String clearText(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (checkSymbol(text.charAt(i)))
                result.append(text.charAt(i));
        }
        this.clearTextLength = result.length();
        this.string = result.toString();
        return this.string;
    }

    /*
    * Method computes number of each letters in text and returns a TreeMap<Character, Integer>.
    *
    * */
    private TreeMap<Character, Integer> computeEachLetters(String string) {
        TreeMap<Character, Integer> statistic = new TreeMap<>();
        int count;
        string = string.toLowerCase();
        for (int i = 0; i < string.length(); i++) {
            count = 1;
            if (!statistic.containsKey(string.charAt(i))) {
                for (int j = i + 1; j < string.length(); j++) {
                    if ((language.getLetterPosition(string.charAt(i)) ==
                            language.getLetterPosition(string.charAt(j))))
                        count++;
                }
                statistic.put(string.charAt(i), count);
            }
        }
        return statistic;
    }

}
