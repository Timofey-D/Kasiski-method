package timofeyD.KasiskiMethod;

import java.util.*;

public class Alphabet {
    private String language;
    private char[] alphabetCapital;
    private char[] alphabetLow;
    private int alphabetLength;
    private TreeMap<Integer[], Character> infoAboutAlphabet = new TreeMap<Integer[], Character>();

    public Alphabet(String language) {
        this.language = language;
        int index;
        switch (this.language) {
            case "He":
                index = 0;
                this.alphabetLength = 27;
                alphabetCapital = new char[alphabetLength];
                alphabetLow = new char[alphabetLength];
                for (int i = 1488; i <= 1514; i++)
                    alphabetCapital[index++] = (char) i;
                break;
            case "En":
            default:
                index = 0;
                this.alphabetLength = 26;
                alphabetCapital = new char[alphabetLength];
                alphabetLow = new char[alphabetLength];
                for (int i = 65; i <= 90; i++)
                    alphabetCapital[index++] = (char) i;
                index = 0;
                for (int i = 97; i <= 122; i++)
                    alphabetLow[index++] = (char) i;
        }
    }


    public boolean capitalLetter(int codePoint) {
        for (int i = 0; i < alphabetLength; i++) {
            if (alphabetCapital[i] == codePoint)
                return true;
        }
        return false;
    }

    public boolean lowLetter(int codePoint) {
        for (int i = 0; i < alphabetLength; i++) {
            if (alphabetLow[i] == codePoint)
                return true;
        }
        return false;
    }

    public int getLetterPosition(char index) {
        for (int i = 0; i < alphabetLength; i++) {
            if (alphabetLow[i] == index)
                return i;
            if (alphabetCapital[i] == index)
                return i;
        }
        return  0;
    }

    public char getLowLetterByPosition(int position) {
        for (int i = 0; i < alphabetLength; i++) {
            if (i == position)
                return alphabetLow[i];
        }
        return '0';
    }

    public char getCapitalLetterByPosition(int position) {
        for (int i = 0; i < alphabetLength; i++) {
            if (i == position)
                return alphabetCapital[i];
        }
        return '0';
    }

    public int getAlphabetLength() {
        return alphabetLength;
    }

    public char[] getAlphabetCapital() {
        return alphabetCapital;
    }

    public char[] getAlphabetLow() {
        return alphabetLow;
    }


}
