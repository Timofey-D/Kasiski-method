package timofeyD.KasiskiMethod;

import java.util.Arrays;

/**
* <i><h1>This program encrypts or decrypts any texts, which a user has written.<h1/><i/>
 * <p>Program will consist of several classes, which work together.<p/>
 * <p><h2>Architecture of the program<h2/><p/>
 * <p>class Text: creating a text for encryption or decryption<p/>
 * <p>class VinegarMethod: encrypting or decrypting an instance of a class Text using Vinegar method<p/>
 * <p>class CaesarMethod: encrypting or decrypting an instance of a class Text using Caesar method<p/>
 * <p>class KasiskiMethod: hacking a cipher text using Kasiski method<p/>
 * <p>class Alphabet: class contains an alphabet<p/>
 * */
public class Main {
    public static void main(String[] args) {
        Key key = new Key("WhyWhy");

        Text textForEncryption = new Text("Tipidor");
        Alphabet english = new Alphabet("En");
        VinegarMethod encryption = new VinegarMethod(textForEncryption, key, english, true);
        System.out.println("PlainText: " + textForEncryption);
        System.out.println("CipherText: " + encryption);

        Text textForDecryption = new Text(encryption.getEncryption());
        VinegarMethod decryption = new VinegarMethod(textForDecryption, key, english, false);
        System.out.println("CipherText: " + textForDecryption);
        System.out.println("Plaintext: " + decryption);
    }
}
