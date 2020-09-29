package timofeyD.KasiskiMethod;

public class Key {
    private String key;
    private int length;

    public Key(String key) {
        this.key = key;
        this.length = this.key.length();
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return key;
    }
}
