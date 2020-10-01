package timofeyD.KasiskiMethod;

import java.util.*;

public class Alphabet {
    private String language;
    private char[] alphabetCapital;
    private char[] alphabetLow;
    private int alphabetLength;

    public Alphabet() {
        int index = 0;
        this.alphabetLength = 26;
        alphabetCapital = new char[alphabetLength];
        alphabetLow = new char[alphabetLength];
        for (int i = 65; i <= 90; i++)
            alphabetCapital[index++] = (char) i;
        index = 0;
        for (int i = 97; i <= 122; i++)
            alphabetLow[index++] = (char) i;
    }

    public Alphabet(String language) {
        this.language = language;
        int index;
        switch (this.language) {
            case "Ru" -> {
                index = 0;
                this.alphabetLength = 32;
                alphabetCapital = new char[alphabetLength];
                alphabetLow = new char[alphabetLength];
                for (int i = 1040; i < 1072; i++)
                    alphabetCapital[index++] = (char) i;
                index = 0;
                for (int i = 1072; i < 1104; i++)
                    alphabetLow[index++] = (char) i;
            }
            case "He" -> {
                index = 0;
                this.alphabetLength = 27;
                alphabetCapital = new char[alphabetLength];
                alphabetLow = new char[alphabetLength];
                for (int i = 1488; i < 1515; i++) {
                    alphabetLow[index] = (char) i;
                    alphabetCapital[index++] = (char) i;
                }
            }
            case "En" -> {
                index = 0;
                this.alphabetLength = 26;
                alphabetCapital = new char[alphabetLength];
                alphabetLow = new char[alphabetLength];
                for (int i = 65; i < 91; i++)
                    alphabetCapital[index++] = (char) i;
                index = 0;
                for (int i = 97; i < 123; i++)
                    alphabetLow[index++] = (char) i;
            }
        }
    }

    /**
     * Method gets a codePoint any symbols from alphabet and returns true or false.
     * @true: if symbol is a capital letter.
     * @False: if symbol isn't a capital letter.
     * */
    protected boolean capitalLetter(int codePoint) {
        for (int i = 0; i < alphabetLength; i++) {
            if (alphabetCapital[i] == codePoint)
                return true;
        }
        return false;
    }

    /**
     * Method gets a codePoint any symbols from alphabet and returns true or false.
     * @true: if symbol is a low letter.
     * @False: if symbol isn't a low letter.
     * */
    public boolean lowLetter(int codePoint) {
        for (int i = 0; i < alphabetLength; i++) {
            if (alphabetLow[i] == codePoint)
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
            if (alphabetLow[i] == symbol)
                return i + 1;
            if (alphabetCapital[i] == symbol)
                return i + 1;
        }
        return  -1;
    }

    /**
    * Method gets a number position and returns symbol by this position.
    * @Return symbol: if position is part of array.
     * @Return ' ': if position isn't part of array.
    * */
    public char getLowLetterByPosition(int position) {
        return getLetterByPosition(position, alphabetLow);
    }

    /**
     * Method gets a number position and returns symbol by this position.
     * @Return symbol: if position is a part of array.
     * @Return ' ': if position isn't a part of array.
     * */
    public char getCapitalLetterByPosition(int position) {
        return getLetterByPosition(position, alphabetCapital);
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

    public int getAlphabetLength() {
        return alphabetLength;
    }

    public char[] getAlphabetCapital() {
        return alphabetCapital;
    }

    public char[] getAlphabetLow() {
        if (alphabetLow == null)
            return new char[1];
        else
            return alphabetLow;
    }

    public String getLanguage() {
        switch(language) {
            case "En" -> {return "English";}
            case "Ru" -> {return "Russian";}
            case "He" -> {return "Hebrew";}
        }
        return "Unknown";
    }
}
