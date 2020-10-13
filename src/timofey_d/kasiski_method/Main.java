package timofey_d.kasiski_method;

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
        Text text1 = new Text("שלום, שמי טימופיי! מה נשמע?");
        Key key1 = new Key("כלב");
        printVinegarMethod(hebrew, text1, key1);

        Alphabet english = new Alphabet("En");
        Text text2 = new Text("Hello, my name is Timofey! I have a sister and two brothers.");
        Key key2 = new Key("dogiscat");
        printVinegarMethod(english, text2, key2);

        Alphabet russian = new Alphabet("Ru");
        Text text3 = new Text("Привет, меня зовут Тимофей.");
        Key key3 = new Key("можно пожалуйста без мата");
        printVinegarMethod(russian, text3, key3);
        
        System.out.println("Number of words in text: " + text2.getNumberOfWords());
        System.out.println("Number of letters in text: " + text2.getEachLetters());
        System.out.println("Clear text length: " + text2.getClearTextLength());
        System.out.println("Primary text length: " + text2.getPrimaryTextLength());
    }

    public static void printVinegarMethod(Alphabet russian, Text text, Key key) {
        VinegarMethod encryption = new VinegarMethod(text, key, russian, true);
        VinegarMethod decryption = new VinegarMethod(new Text(encryption.getEncryption()), key, russian, false);
        System.out.println("Key: " + key);
        System.out.println("encryption: " + encryption);
        System.out.println("decryption: " + decryption);
        System.out.println("____________________________________");
    }
}
