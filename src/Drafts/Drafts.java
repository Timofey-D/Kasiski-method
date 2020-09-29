package Drafts;

import java.util.*;
import java.util.stream.Collectors;

public class Drafts {
    static char[] letters = {'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};
    static ArrayList<String> keys = new ArrayList<String>();

    public static void main(String[] args) {
        String text = "Hello, I am Timofey, what is your name?";
        text = "QPWKALVRXCQZIKGRBPFAEOMFLJMSDZVDHXCXJYEBIMTRQWNMEAIZRVKCVKVLXNEICFZPZCZZHKMLVZVZIZRRQWDKECHOSNYXXLSPMYKVQXJTDCIOMEEXDQVSRXLRLKZHOV";
//        getIndexOfCoincidenceExperimentallyView(text);
        getApproximateLettersOfKeyView(getQuantityLettersInText(text));
        System.out.println(getDecryptionTextEnterKey(text));
        System.out.println(getDecryptionTextEnterKey(text));
//        System.out.println(getApproximateLettersOfKey(getQuantityLettersInText(text)));
    }

    /*
     * This method allows to encrypt some texts using Vinegere cipher.
     * Method has a loop for encryption text several keys.
     * Keys must consist only letters english alphabet without whitespace.
     * */
    private static String encryptData(String text) {
        StringBuilder line = getEditText(text);
        StringBuilder encryption = new StringBuilder();
        String key;
        Scanner input = new Scanner(System.in);
        System.out.print("Rounds: ");
        int round = input.nextInt();
        input.nextLine();
        for (int j = 1; j <= round; j++) {
            do {
                System.out.print("Enter key: ");
                key = input.nextLine();
            } while (key.isEmpty() || key.contains(" "));
            key = key.toUpperCase();
            keys.add(key);
            encryption.delete(0, encryption.length());
            for (int i = 0; i < line.length(); i++) {
                int plainText = letters[line.charAt(i) % letters.length] % letters.length;
                int cipherText = (plainText + key.charAt(i % key.length()) - 65) % letters.length;
                encryption.append(letters[cipherText]);
            }
            line.delete(0, line.length());
            line.append(encryption);
        }
        return line.toString();
    }

    /*
     * This method allows to decrypt some texts.
     * Method has a loop for decryption texts, which were encrypted several keys.
     * All keys, which was encrypted a text, are located by global ArrayList is called keys.
     * */
    private static String getDecryptionTextListKeys(String encryption) {
        StringBuilder line = new StringBuilder(encryption);
        StringBuilder decryption = new StringBuilder();
        for (int i = keys.size() - 1; i >= 0; i--) {
            decryption.delete(0, encryption.length());
            String key = keys.get(i);
            for (int j = 0; j < line.length(); j++) {
                int cipherText = letters[line.charAt(j) % letters.length] % letters.length;
                int plainText = (( letters[cipherText] - letters[ key.charAt(j % key.length()) - 65 ] ) + letters.length) % letters.length;
                decryption.append(letters[plainText]);
            }
            line.delete(0, line.length());
            line.append(decryption);
        }
        return line.toString();
    }

    /*
     * This method allows to decrypt some texts.
     * Method has also a loop for decryption texts, which were encrypted several keys.
     * Method allows to choose how many keys were used for encryption and to enter these keys self.
     * Keys must consist only letters english alphabet without whitespace.
     * */
    private static String getDecryptionTextEnterKey(String encryption) {
        StringBuilder line = new StringBuilder(encryption);
        ArrayList<String> listKey = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        String enterKey;
        System.out.print("How many are keys?\nQuantity: ");
        int number = input.nextInt();
        input.nextLine();
        do {
            System.out.print("Enter key: ");
            enterKey = input.nextLine();
            if (!enterKey.isEmpty() && !enterKey.contains(" ")) {
                System.out.println("Key was added into list!");
                enterKey = enterKey.toUpperCase();
                listKey.add(enterKey);
                number--;
            }
        } while (enterKey.isEmpty() || enterKey.contains(" ") || number != 0);
        StringBuilder decryption = new StringBuilder();
        for (int i = listKey.size() - 1; i >= 0; i--) {
            decryption.delete(0, encryption.length());
            String key = listKey.get(i);
            for (int j = 0; j < line.length(); j++) {
                int cipherText = letters[line.charAt(j) % letters.length] % letters.length;
                int plainText = (letters[cipherText] - letters[ key.charAt(j % key.length()) - 65 ] + letters.length) % letters.length;
                decryption.append(letters[plainText]);
            }
            line.delete(0, line.length());
            line.append(decryption);
        }
        return line.toString();
    }

    /*
     * Method allows to compute quantity letters in text
     * */
    private static HashMap<Character, Integer> getQuantityLettersInText(String text) {
        HashMap<Character, Integer> result = new HashMap<Character, Integer>();
        for (char letter : letters) {
            int number = 0;
            for (int j = 0; j < text.length(); j++) {
                if (letter == text.toUpperCase().charAt(j)) number++;
                if (number > 0) result.put(letter, number);
            }
        }
        return result;
    }

    /*
     * Method allows to compute quantity letters in text, this method outputs data.
     * */
    private static void getQuantityLettersInTextView(String text) {
        System.out.print("Quantity of each letter in the text:\n\tLetter\t\t||\t\tQuantity\t\t||\t\t\t%\n__________________________________________________________\n");
        for (char letter : letters) {
            int number = 0;
            for (int j = 0; j < text.length(); j++) if (letter == text.toUpperCase().charAt(j)) number++;
            System.out.printf("%7s\t\t\t||%10d\t\t\t||\t\t%7.2f %%\n", letter, number, ((float) number/ (float) text.length()) * 100);
        }
        System.out.print("__________________________________________________________\n");
    }

    /*
     * Method allows to compute all distances between the same letters.
     * Method returns ArrayList, which consists integers these distances.
     * After this method another method takes ArrayList and to create new ArrayList, which
     * consists gcd for each distance value.
     * */
    private static ArrayList<Integer> getDistanceBetweenLetters(String encryption) {
        Stack<Character> letter = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Character> previous = new ArrayList<>();
        for (int i = 0; i < encryption.length(); i++) {
            letter.push(encryption.charAt(i));
            int count = 0;
            if (!previous.contains(letter.peek())) {
                for (int j = i + 1; j < encryption.length(); j++) {
                    if (encryption.charAt(j) != letter.peek()) count++;
                    if (encryption.charAt(j) == letter.peek()) {
                        if (count != 0) result.add(count);
                        count = 0;
                    }
                }
            }
            previous.add(encryption.charAt(i));
        }
        Collections.sort(result);
        getListGCD(result);
        return result;
    }

    /*
     * Method allows to get GCD for each value a distance.
     * Method gets ArrayList, which consists all distance values between the same letter for each letters.
     * */
    private static ArrayList<Integer> getListGCD(ArrayList<Integer> distance) {
        ArrayList<Integer> gcd = new ArrayList<>();
        int result = 0;
        Collections.sort(distance);
        for (int current : distance) {
            int min = 0;
            for (int j = 0; j < current; j++) {
                if (j > min) {
                    if (current % j == 0) {
                        result = j;
                        min = j;
                    }
                }
            }
            gcd.add(result);
        }
        return gcd;
    }

    /*
     * This method allows to compute index of coincidence.
     * Formula: CI = E Ai * (Ai - 1) / Nt * (Nt - 1).
     * E = sum;
     * Ai = coincidence each letter from text;
     * Nt = length of text.
     * */
    private static Double getIndexOfCoincidence(String text) {
        StringBuilder editText = getEditText(text);
        HashMap<Character, Integer> quantityLettersInText = getQuantityLettersInText(editText.toString());
        Object[] keysForHashMap = quantityLettersInText.keySet().toArray();
        int length_CipherText = editText.length();
        double numerator = 0;
        for (Object o : keysForHashMap) numerator += (quantityLettersInText.get((char) o) * (quantityLettersInText.get((char) o) - 1));
        System.out.print("Index of coincidence: ");
        return Double.parseDouble(String.format("%.6f", numerator / (length_CipherText * (length_CipherText - 1))));
    }

    /*
     * This method allows to compute index of coincidence.
     * Formula: CI = E Ai * (Ai - 1) / Nt * (Nt - 1) / C.
     * E = sum;
     * Ai = coincidence each letter from text;
     * Nt = length of text.
     * C = letters in alphabet
     * */
    private static Double getIndexOfCoincidenceExpected(String text) {
        StringBuilder editText = getEditText(text);
        HashMap<Character, Integer> quantityLettersInText = getQuantityLettersInText(editText.toString());
        Object[] keysForHashMap = quantityLettersInText.keySet().toArray();
        int length_CipherText = editText.length();
        double numerator = 0;
        for (Object o : keysForHashMap) numerator += quantityLettersInText.get((char) o) * (quantityLettersInText.get((char) o) - 1);
        double divider = (length_CipherText * (length_CipherText - 1)) / 26f;
        System.out.print("Index of coincidence expected: ");
        return Double.parseDouble(String.format("%.6f", numerator / divider));
    }

    /*
     * this method allows to compute experimentally index of coincidence:
     * Formula: CI = E Ai * (Ai - 1) / Nt * (Nt - 1) / C.
     * E = sum;
     * Ai = coincidence each letter from text;
     * Nt = length of text.
     * C = letters in alphabet
     * */
    private static Float getIndexOfCoincidenceExperimentally(String text) {
        StringBuilder editText = getEditText(text);
        HashMap<Character, Integer> quantityLettersInText = getQuantityLettersInText(editText.toString());
        Object[] keysForHashMap = quantityLettersInText.keySet().toArray();
        int length_CipherText = editText.length();
        float IC = 0.0f;
        for (int index = 0; index < keysForHashMap.length; index++) IC += ( (quantityLettersInText.get(keysForHashMap[index]) * (quantityLettersInText.get(keysForHashMap[index]) - 1)) / (length_CipherText * (length_CipherText - 1) / 26f) );
        return IC;
    }

    /*
     * this method allows to compute experimentally index of coincidence:
     * Formula: CI = E Ai * (Ai - 1) / Nt * (Nt - 1) / C.
     * E = sum;
     * Ai = coincidence each letter from text;
     * Nt = length of text.
     * C = letters in alphabet
     * */
    private static Float getIndexOfCoincidenceExperimentallyView(String text) {
        StringBuilder editText = getEditText(text);
        HashMap<Character, Integer> quantityLettersInText = getQuantityLettersInText(editText.toString());
        Object[] keysForHashMap = quantityLettersInText.keySet().toArray();
        int length_CipherText = editText.length();
        float IC = 0.0f;
        System.out.print("Index of Coincidence for each letters:\n\tletter\t\t||\t\t\tIC\n_____________________________________\n");
        for (int index = 0; index < keysForHashMap.length; index++) {
            System.out.printf("%7s\t\t\t||%15f\n", keysForHashMap[index],( (quantityLettersInText.get(keysForHashMap[index]) * (quantityLettersInText.get(keysForHashMap[index]) - 1)) / (length_CipherText * (length_CipherText - 1) / 26f) ));
            IC += ( (quantityLettersInText.get(keysForHashMap[index]) * (quantityLettersInText.get(keysForHashMap[index]) - 1)) / (length_CipherText * (length_CipherText - 1) / 26f) );
        }
        System.out.printf("_____________________________________\nGeneral IC: %f\n", IC);
        return IC;
    }

    /*
     * This method allows to find approximate letters of key
     * Formula: X = E An * Fn
     * An - letter
     * Fn - statistic of coincidence this letter
     * */
    private static void getApproximateLettersOfKeyView(HashMap<Character, Integer> letters) {
        final float[] statisticOfLetters = {0.08167f, 0.01492f, 0.02782f, 0.04253f, 0.12702f,
                0.02228f, 0.02015f, 0.06094f, 0.06966f, 0.00153f,
                0.00772f, 0.0425f, 0.02406f, 0.06749f, 0.07507f,
                0.01929f, 0.0095f, 0.05987f, 0.06327f, 0.09056f,
                0.02758f, 0.00978f, 0.02360f, 0.00150f, 0.01974f, 0.0074f};
        HashMap<Character, Float> statistic = new HashMap<>();
        for (int i = 0; i < Drafts.letters.length; i++) statistic.put(Drafts.letters[i], statisticOfLetters[i]);
        Object[] keysForHashMap = letters.keySet().toArray();
        System.out.printf("The data is about approximate letters of key\n\tPrimary letter" +
                "\t\t||\t\t\tQuantity in text" +
                "\t\t||\t\tStatistic %%" +
                "\t\t||\t\tValue forecast %%" +
                "\t||\t\tCharacter forecast\n" +
                "_______________________________________________________________________________________________________________________________________________\n");
        ArrayList<Character> neededLetters = new ArrayList<>();
        float sum = 0.0f;
        for (Object symbol:keysForHashMap) {
            float expectedValue = letters.get(symbol) * statistic.get(symbol);
            char expectedCharacter = getExpectedCharacter(expectedValue, letters);
            neededLetters.add(expectedCharacter);
            System.out.printf("%12s\t\t\t||\t\t\t\t\t%d\t\t\t\t||\t\t\t%.3f%%\t\t||\t\t\t%.3f%%\t\t\t||\t\t\t\t%s\n", symbol, letters.get(symbol), statistic.get(symbol) * 100, expectedValue * 100,  expectedCharacter);
        }
        System.out.printf("_______________________________________________________________________________________________________________________________________________\n");
    }

    /*
     * This method allows to find approximate letters of key
     * Formula: X = E An * Fn
     * An - letter
     * Fn - statistic of coincidence this letter
     * */
    private static List<Character> getApproximateLettersOfKey(HashMap<Character, Integer> letters) {
        final float[] statisticOfLetters = {0.08167f, 0.01492f, 0.02782f, 0.04253f, 0.12702f,
                0.02228f, 0.02015f, 0.06094f, 0.06966f, 0.00153f,
                0.00772f, 0.0425f, 0.02406f, 0.06749f, 0.07507f,
                0.01929f, 0.0095f, 0.05987f, 0.06327f, 0.09056f,
                0.02758f, 0.00978f, 0.02360f, 0.00150f, 0.01974f, 0.0074f};
        HashMap<Character, Float> statistic = new HashMap<>();
        for (int i = 0; i < Drafts.letters.length; i++) statistic.put(Drafts.letters[i], statisticOfLetters[i]);
        Object[] keysForHashMap = letters.keySet().toArray();
        ArrayList<Character> neededLetters = new ArrayList<>();
        for (Object symbol:keysForHashMap) {
            float expectedValue = letters.get(symbol) * statistic.get(symbol);
            char expectedCharacter = getExpectedCharacter(expectedValue, letters);
            neededLetters.add(expectedCharacter);
        }
        List<Character> result = neededLetters.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) if (result.get(i) == ' ') result.remove(i);
        Collections.sort(result);
        return result;
    }

    /*
     * A Method allows to find expected character.
     * Method returns character, which is possibly part of key.
     * */
    private static char getExpectedCharacter(float expectedValue, HashMap<Character, Integer> letters) {
        final float[] statisticOfLetters = {0.08167f, 0.01492f, 0.02782f, 0.04253f, 0.12702f,
                0.02228f, 0.02015f, 0.06094f, 0.06966f, 0.00153f,
                0.00772f, 0.0425f, 0.02406f, 0.06749f, 0.07507f,
                0.01929f, 0.0095f, 0.05987f, 0.06327f, 0.09056f,
                0.02758f, 0.00978f, 0.02360f, 0.00150f, 0.01974f, 0.0074f};
        HashMap<Float, Character> collection = new HashMap<>();
        for (int i = 0; i < Drafts.letters.length; i++) collection.put(statisticOfLetters[i], Drafts.letters[i]);
        ArrayList<Character> LETTERS = new ArrayList<>();
        ArrayList<Float> STATISTIC = new ArrayList<>();
        for (char letter : Drafts.letters) LETTERS.add(letter);
        for (float value : statisticOfLetters) STATISTIC.add(value);
        float max = Collections.max(STATISTIC);
        float min = Collections.min(STATISTIC);
        char character = ' ';
        for (int i = 0; i < LETTERS.size(); i++) {
            if (expectedValue > max) return collection.get(max);
            else if (expectedValue < min) return collection.get(min);
        }
        double[][] valueBetweenInteger = {{0.0, 0.01}, {0.01, 0.02}, {0.02, 0.03}, {0.03, 0.04}, {0.04, 0.05},
                {0.05, 0.06}, {0.06, 0.07}, {0.07, 0.08}, {0.08, 0.09}, {0.09, 0.1}, {0.1, 0.11}, {0.11, 0.12}, {0.12, 0.13}};
        double[] valueBetweenPart = {0.5, 0.015, 0.025, 0.035, 0.045, 0.055, 0.065, 0.075, 0.085, 0.095, 0.105, 0.115, 0.125};
        for (int i = 0; i < valueBetweenInteger.length; i++) {
            if (expectedValue >= valueBetweenInteger[i][0] && expectedValue <= valueBetweenInteger[i][1]) {
                double low = valueBetweenInteger[i][0];
                double top = valueBetweenInteger[i][1];
                for (double value: valueBetweenPart) {
                    if (value > low && value < top && value > expectedValue) {
                        for (float stat: STATISTIC) {
                            if      (low < stat && value > stat) character = collection.get(stat);
                            else if (top > stat && value < stat) character = collection.get(stat);
                        }
                    }
                    else if (value > low && value < top && value < expectedValue) {
                        for (float stat: STATISTIC) {
                            if      (low < stat && value > stat) character = collection.get(stat);
                            else if (top > stat && value < stat) character = collection.get(stat);
                        }
                    }
                }
            }
        }
        return character;
    }

    private static int getLengthText(String text) {
        StringBuilder editText = getEditText(text);
        return editText.length();
    }

    private static HashMap<String, Double> getDistanceBetweenCombination(String text) {
        StringBuilder encryption = getEditText(text);
        HashMap<String, Double> result = new HashMap<>();
        Stack<Double> value = new Stack<>();
        StringBuilder current = new StringBuilder();
        StringBuilder check = new StringBuilder();
        for (int i = 0; i < encryption.length(); i++) {
            double count = 0;
            double loop = 0;
            double sum = 0;
            if (current.length() < encryption.length()) current.append(encryption.charAt(i % encryption.length()));
            String some = encryption.toString();
            for (int k = 0; some.length() >= k + current.length(); k += current.length()) {
                check.delete(0, check.length());
                check.append(encryption.substring(k, k + current.length()));
                if (check.toString().equalsIgnoreCase(current.toString()) ) some = some.replaceAll(check.toString(), "1");
            }
            for (int j = 0; j < some.length(); j++) {
                if (some.charAt(j) != '1') count++;
                else if (some.charAt(j) == '1') {
                    loop++;
                    if (!value.empty()) value.pop();
                    value.push(count);
                }
            }
            while (!value.empty()) sum += value.pop();
            if (sum != 0) result.put(current.toString(), sum);
        }
        return result;
    }

    /*
     * Method creates string only with letters without whitespace
     * */
    private static StringBuilder getEditText(String text) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) if (text.toUpperCase().charAt(i) >= 'A' && text.toUpperCase().charAt(i) <= 'Z') result.append(text.toUpperCase().charAt(i));
        return result;
    }
}
