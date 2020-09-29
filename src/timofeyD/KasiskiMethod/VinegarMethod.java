package timofeyD.KasiskiMethod;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class VinegarMethod {
    private Text text;
    private Key key;
    private Alphabet language;
    private String encryption;
    private String dencryption;
    /**
    * <p>Variable determines what action will apply to the text: encryption or decryption<p/>
     * @true: encryption
     * @false: decryption
    * */
    private boolean action = true;

    public VinegarMethod(Text text, Key key) {
        this(text, key, new Alphabet("En"), true);
    }

    public VinegarMethod(Text text, Key key, boolean action) {
        this(text, key, new Alphabet("En"), action);
    }

    public VinegarMethod(Text text, Key key, Alphabet language, boolean action) {
        this.text = text;
        this.key = key;
        this.action = action;
        this.language = language;
        if (action)
            this.encryption = encryptData(this.text, this.key, this.language);
        else
            this.dencryption = decryptData(this.text, this.key, this.language);
    }

    public Text getText() {
        return text;
    }

    public Key getKey() {
        return key;
    }

    public boolean isAction() {
        return action;
    }

    /*
     * This method allows to encrypt some texts using Vinegar cipher.
     * */
    private String encryptData(Text text, Key key, Alphabet language) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.getLength(); i++) {
            int cipherText = (language.getLetterPosition(text.toString().charAt(i)) + language.getLetterPosition(key.toString().charAt(i % key.getLength()))) % language.getAlphabetLength();
            if (language.capitalLetter(text.toString().charAt(i)))
                result.append(language.getCapitalLetterByPosition(cipherText));
            if (language.lowLetter(text.toString().charAt(i)))
                result.append(language.getLowLetterByPosition(cipherText));
        }
        return result.toString();
    }

    private String decryptData(Text text, Key key, Alphabet language) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.getLength(); i++) {
            int difference = (language.getLetterPosition(text.toString().charAt(i)) - language.getLetterPosition(key.toString().charAt(i % key.getLength())));
            int plainText = difference < 0 ? (difference + language.getAlphabetLength()) % language.getAlphabetLength() : difference % language.getAlphabetLength();
            if (language.capitalLetter(text.toString().charAt(i)))
                result.append(language.getCapitalLetterByPosition(plainText));
            if (language.lowLetter(text.toString().charAt(i)))
                result.append(language.getLowLetterByPosition(plainText));
        }
        return result.toString();
    }

    public String getEncryption() {
        return encryption;
    }

    public String getDencryption() {
        return dencryption;
    }

    @Override
    public String toString() {
        if (action)
            return encryption;
        else
            return dencryption;
    }
}















