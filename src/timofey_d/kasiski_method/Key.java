package timofey_d.kasiski_method;

public class Key {
    private String key;
    private int length;

    public Key(String key) {
        this.key = getCleanKey(key);
        this.length = this.key.length();
    }

    private String getCleanKey(String key) {
        StringBuilder result = new StringBuilder(key);
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == ' ')
                result.deleteCharAt(i);
        }
        return result.toString();
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return key;
    }
}
