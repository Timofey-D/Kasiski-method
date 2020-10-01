package timofeyD.KasiskiMethod;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException {
        Alphabet hebrew = new Alphabet("He");
        Text text_1 = new Text("שלום, שמי טימופיי! מה נשמע?");
        Key key_1 = new Key("כלב");
        printVinegareMethod(hebrew, text_1, key_1);

        Alphabet english = new Alphabet("En");
        Text text_2 = new Text("Hello, my name is Timofey! I have a sister and two brothers.");
        Key key_2 = new Key("dogiscat");
        printVinegareMethod(english, text_2, key_2);

        Alphabet russian = new Alphabet("Ru");
        Text text_3 = new Text("Привет, меня зовут Тимофей, как твое имя?");
        Key key_3 = new Key("можно пожалуйста без мата");
        printVinegareMethod(russian, text_3, key_3);

    }

    public static void printVinegareMethod(Alphabet russian, Text text_3, Key key_3) {
        VinegarMethod encryption = new VinegarMethod(text_3, key_3, russian, true);
        VinegarMethod decryption = new VinegarMethod(new Text(encryption.getEncryption()), key_3, russian, false);
        System.out.println("Key: " + key_3);
        System.out.println("encryption: " + encryption);
        System.out.println("decryption: " + decryption);
        System.out.println("____________________________________");
    }
}
