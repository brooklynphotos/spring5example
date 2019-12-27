package photos.brooklyn.spring5example.kafka;

public class WordCount {
    private String word;

    public String getWord() {
        return word;
    }

    public Long getCount() {
        return count;
    }

    private Long count;

    public WordCount(final String word, final Long count) {
        this.word = word;
        this.count = count;
    }
}
