package timofey_d.kasiski_method;

import java.util.Locale;

/**
* <i><strong>Alphabet class allows to build an object of certain alphabet.<strong/><i/>
* The class contains several methods for working with item of alphabet.
* The class can be extended different tools.
* The class can still work with 3 languages: Russian, English, Hebrew.
* */
public class Alphabet {
    /**
    * The variable indicates what languages a user choose.
    * The main variable, which determines values remain variables.
     * The variable is not mutable.
    * @Value Ru/ru - russian language
     * @Value En/en - english language
     * @Value He/he - hebrew language
    * */
    private final String language;
    /**
     * The variable is array of capital letters.
     * The variable is not mutable.
     * The array contains a code point of capital english letters.
     * */
    private char[] capitalLetters;
    /**
     * The variable is array of low letters.
     * The variable is not mutable.
     * The array contains a code point of low english letters.
     * */
    private char[] lowerLetters;
    /**
     * The variable is length of alphabet.
     * The variable is not mutable.
     * */
    private int alphabetLength;

    /**
     * The array is table of frequency of characters in English language.
     * It means what possibility of coincidence each letters have in texts.
     * The first element belongs to letter 'A' and next in order by alphabetical.
    * */
    protected static final float[] ENGLISH_STATISTIC_OF_FREQUENCIES = {
            0.08167f, 0.01492f, 0.02782f, 0.04253f, 0.12702f,
            0.02228f, 0.02015f, 0.06094f, 0.06966f, 0.00153f,
            0.00772f, 0.0425f, 0.02406f, 0.06749f, 0.07507f,
            0.01929f, 0.0095f, 0.05987f, 0.06327f, 0.09056f,
            0.02758f, 0.00978f, 0.02360f, 0.00150f, 0.01974f,
            0.0074f};

    /**
     * The array is table of frequency of characters in Russian language.
     * It means what possibility of coincidence each letters have in texts.
     * The first element belongs to letter 'A' and next in order by alphabetical.
     * */
    protected static final float[] RUSSIAN_STATISTIC_OF_FREQUENCIES = {
            0.0764f, 0.0201f, 0.0438f, 0.0172f, 0.0309f,
            0.0875f, 0.0101f, 0.0148f, 0.0709f, 0.0121f,
            0.0330f, 0.0496f, 0.0317f, 0.0678f, 0.1118f,
            0.0247f, 0.0423f, 0.0497f, 0.0609f, 0.0222f,
            0.0021f, 0.0095f, 0.0039f, 0.0140f, 0.0072f,
            0.0030f, 0.0002f, 0.0236f, 0.0184f, 0.0036f,
            0.0047f, 0.0196f};

    /**
     * The array is table of frequency of characters in Russian language.
     * It means what possibility of coincidence each letters have in texts.
     * The first element belongs to letter 'A' and next in order by alphabetical.
     * */
    protected static final float[] HEBREW_STATISTIC_OF_FREQUENCIES = {
            0.0634f, 0.0474f, 0.0130f, 0.0259f, 0.1087f,
            0.1038f, 0.0133f, 0.0248f, 0.0124f, 0.1106f,
            0.0081f, 0.0270f, 0.0739f, 0.0303f, 0.0459f,
            0.0110f, 0.0286f, 0.0148f, 0.0323f, 0.0027f,
            0.0169f, 0.0012f, 0.0124f, 0.0214f, 0.0561f,
            0.0441f, 0.0501f};

    /**
    * Default constructor.
     * Constructor creates english language by default.
    * */
    public Alphabet() {
        int index = 0;
        this.language = "En";
        this.alphabetLength = 26;
        capitalLetters = new char[alphabetLength];
        lowerLetters = new char[alphabetLength];
        for (int i = 65; i <= 90; i++)
            capitalLetters[index++] = (char) i;
        index = 0;
        for (int i = 97; i <= 122; i++)
            lowerLetters[index++] = (char) i;
    }

    /**
    * Constructor with parameters.
    * For initialize an alphabet object.
    * @Create an object alphabet for interact with classes of VinegarMethod, Text and KasiskiMethod
    * */
    public Alphabet(String language) {
        this.language = language;
        int index;
        switch (this.language) {
            case "Ru", "ru" -> {
                index = 0;
                this.alphabetLength = 32;
                capitalLetters = new char[alphabetLength];
                lowerLetters = new char[alphabetLength];
                for (int i = 1040, j = 1072; i < 1072 && j < 1104; i++, j++) {
                    capitalLetters[index] = (char) i;
                    lowerLetters[index++] = (char) j;
                }
            }
            case "He", "he" -> {
                index = 0;
                this.alphabetLength = 27;
                capitalLetters = new char[alphabetLength];
                lowerLetters = new char[alphabetLength];
                for (int i = 1488; i < 1515; i++) {
                    lowerLetters[index] = (char) i;
                    capitalLetters[index++] = (char) i;
                }
            }
            case "En", "en" -> {
                index = 0;
                this.alphabetLength = 26;
                capitalLetters = new char[alphabetLength];
                lowerLetters = new char[alphabetLength];
                for (int i = 65, j = 97; i < 91 && j < 123; i++, j++) {
                    capitalLetters[index] = (char) i;
                    lowerLetters[index++] = (char) j;
                }
            }
        }
    }

    /**
     * Method gets a codePoint any symbols from alphabet and returns true or false.
     * @true: if symbol is a capital letter.
     * @false: if symbol isn't a capital letter.
     * */
    protected boolean capitalLetter(int codePoint) {
        for (int i = 0; i < alphabetLength; i++) {
            if (capitalLetters[i] == codePoint)
                return true;
        }
        return false;
    }

    /**
     * Method gets a codePoint any symbols from alphabet and returns true or false.
     * @true: if symbol is a low letter.
     * @False: if symbol isn't a low letter.
     * */
    public boolean lowerLetter(int codePoint) {
        for (int i = 0; i < alphabetLength; i++) {
            if (lowerLetters[i] == codePoint)
                return true;
        }
        return false;
    }

    /**
    * Method gets a codePoint of a symbol and returns its a place in array.
    * @Return value is more or equally 0: if a symbol is in an array
     * @Return -1: if symbol isn't in an array.
    * */
    public int getLetterPosition(char symbol) {
        for (int i = 0; i < alphabetLength; i++) {
            if (lowerLetters[i] == symbol)
                return i + 1;
            if (capitalLetters[i] == symbol)
                return i + 1;
        }
        return  -1;
    }

    /**
    * Method gets a number position and returns symbol by this position.
    * @Return symbol: if position is part of array.
     * @Return ' ': if position isn't part of array.
    * */
    public char getLowerLetterByPosition(int position) {
        return getLetterByPosition(position, lowerLetters);
    }

    /**
     * Method gets a number position and returns symbol by this position.
     * @Return symbol: if position is a part of array.
     * @Return ' ': if position isn't a part of array.
     * */
    public char getCapitalLetterByPosition(int position) {
        return getLetterByPosition(position, capitalLetters);
    }

    /**
    * Method gets a position and alphabet and returns a letter by this position.
    * @Return symbol: if position is a part of array.
    * @Return ' ': if position isn't a part of array.
    * */
    private char getLetterByPosition(int position, char[] alphabet) {
        if (position - 1 == -1) position += alphabetLength ;
        for (int i = 0; i < alphabetLength; i++) {
            if (i == position - 1)
                return alphabet[(position - 1) % alphabetLength];
        }
        return ' ';
    }

    /**
    * Method returns value of alphabet length.
    * @Returns int value.
    * */
    public int getAlphabetLength() {
        return alphabetLength;
    }

    /**
     * Method returns array of capital letters
     * @Returns array of characters (char).
     * */
    public char[] getCapitalLetters() {
        return capitalLetters;
    }

    /**
     * Method returns array of lower letters
     * @Returns array of characters (char).
     * */
    public char[] getLowerLetters() {
        if (lowerLetters == null)
            return new char[1];
        else
            return lowerLetters;
    }

    /**
     * Method returns current language, which is used by user.
     * @Returns English, if alphabet is english.
     * @Returns Russian, if alphabet is russian.
     * @Returns Hebrew, if alphabet is hebrew.
     * @Returns Unknown, if alphabet is unknown.
     * */
    public String getLanguage() {
        switch (language) {
            case "En", "en" -> {return "English";}
            case "Ru", "ru" -> {return "Russian";}
            case "He", "he" -> {return "Hebrew";}
        }
        return "Unknown";
    }

    /**
     *
     * */
    public void tableFrequencyOfLetters() {
        float percentage;
        if (language.equalsIgnoreCase("He"))
            System.out.printf("%-10s|%-10s%s", "Frequency", "Letter", "\n");
        else
            System.out.printf("%10s|%10s%s", "Letter", "Frequency", "\n");
        switch (language) {
            case "En", "en" -> {
                for (int i = 0; i < this.alphabetLength; i++) {
                    percentage = ENGLISH_STATISTIC_OF_FREQUENCIES[i] * 100;
                    System.out.printf("%10s|%10.2f%c%s", this.capitalLetters[i], percentage, '\u0025', "\n");
                }
            }
            case "Ru", "ru" -> {
                for (int i = 0; i < this.alphabetLength; i++) {
                    percentage = RUSSIAN_STATISTIC_OF_FREQUENCIES[i] * 100;
                    System.out.printf(Locale.UK,"%10s|%9.2f%c%s", this.capitalLetters[i], percentage, '\u0025', "\n");
                }
            }
            case "He", "he" -> {
                for (int i = 0; i < this.alphabetLength; i++) {
                    percentage = HEBREW_STATISTIC_OF_FREQUENCIES[i] * 100;
                    System.out.printf("%s|", this.capitalLetters[i]);
                    System.out.printf("%9.2f%c%s", percentage, '\u0025', "\n");
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + language);
        }
    }
}
