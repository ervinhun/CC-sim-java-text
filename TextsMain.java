import java.util.HashMap;

public abstract class TextsMain {
    protected HashMap<String, String> texts;

    public TextsMain(HashMap<String, String> texts) {
        this.texts = texts;
    }
    public String getTexts(String key) {
        return texts.get(key);
    }
}
