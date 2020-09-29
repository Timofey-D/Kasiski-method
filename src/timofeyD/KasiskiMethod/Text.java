package timofeyD.KasiskiMethod;

public class Text {
    private String text;
    private final String primaryText;
    private int length;
    private final int primaryLength;

    public Text() {
        this(null);
    }
    public Text(String text) {
        this.primaryText = text;
        this.text = getClearText(text);
        this.primaryLength = this.text.length();
    }

    public int getLength() {
        return length;
    }

    public int getPrimaryLength() {
        return primaryLength;
    }

    public String getText() {
        return text;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void qualityOfWords() {
        int length = this.text.length();
        this.text = getClearText(this.text);
    }



    private String getClearText(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if ( (text.charAt(i) >= 65 && text.charAt(i) <= 90) ||
                    (text.charAt(i) >= 97 && text.charAt(i) <= 125))
                result.append(text.charAt(i));
        }
        this.length = result.length();
        return this.text = result.toString();
    }

    @Override
    public String toString() {
        return text;
    }
}
