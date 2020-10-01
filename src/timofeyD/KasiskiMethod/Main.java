package timofeyD.KasiskiMethod;

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
        Alphabet hebrew = new Alphabet("He");
        Text text_1 = new Text("שלום, שמי טימופיי! מה נשמע?");
        Key key_1 = new Key("כלב");
        VinegarMethod encryption_1 = new VinegarMethod(text_1, key_1, hebrew, true);
        System.out.println("encryption: " + encryption_1);
        VinegarMethod decryption_1 = new VinegarMethod(new Text(encryption_1.getEncryption()), key_1, hebrew, false);
        System.out.println("decryption: " + decryption_1);

        System.out.println("____________________________________");

        Alphabet english = new Alphabet("En");
        Text text_2 = new Text("Hello, my name is Timofey! I have a sister and two brothers.");
        Key key_2 = new Key("fgvhjk");
        VinegarMethod encryption_2 = new VinegarMethod(text_2, key_2, english, true);
        System.out.println("encryption: " + encryption_2);
        VinegarMethod decryption_2 = new VinegarMethod(new Text(encryption_2.getEncryption()), key_2, english, false);
        System.out.println("decryption: " + decryption_2);

        System.out.println("____________________________________");

        Alphabet russian = new Alphabet("Ru");
        Text text_3 = new Text("Привет, меня зовут Тимофей, как твое имя?");
        Key key_3 = new Key("педик");
        VinegarMethod encryption_3 = new VinegarMethod(text_3, key_3, russian, true);
        System.out.println("encryption: " + encryption_3);
        VinegarMethod decryption_3 = new VinegarMethod(new Text(encryption_3.getEncryption()), key_3, russian, false);
        System.out.println("decryption: " + decryption_3);
    }
}
