package timofeyD.KasiskiMethod;

public class Text {
    private String text;
    private final String primaryText;
    private int length;
    private final int primaryLength;
    private final Alphabet language;

    public Text() {
        this(null);
    }

    public Text(String text) {
        this.primaryText = text;
        this.language = checkLanguage(this.primaryText);
        this.text = getClearText(text);
        this.primaryLength = this.text.length();
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

    public int getLength() {
        return length;
    }

    public int getPrimaryLength() {
        return primaryLength;
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

    private boolean getCheckSymbol(char symbol) {
        for (int i = 0; i < language.getAlphabetCapital().length; i++) {
            if (language.getAlphabetCapital()[i] == symbol ||
                    language.getAlphabetLow()[i] == symbol)
                return true;
        }
        return false;
    }

    private int computeNumberOfWords() {
        int words = 0;
        for (int i = 0; i < primaryText.length(); i++) {
            if (getCheckSymbol(primaryText.charAt(i))) {
                for (int j = i; j < primaryText.length(); j++) {
                    if (!getCheckSymbol(primaryText.charAt(j)) || j == primaryText.length() - 1) {
                        words++;
                        i = j;
                        break;
                    }
                }
            }
        }
        return words;
    }

    private String getClearText(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (getCheckSymbol(text.charAt(i)))
                result.append(text.charAt(i));
        }
        this.length = result.length();
        return this.text = result.toString();
    }

    public void numberOfLetters() {
        System.out.println(this.text.length());;
    }

    public int getNumberOfLetters() {
        return this.text.length();
    }

    @Override
    public String toString() {
        return text;
    }
}
