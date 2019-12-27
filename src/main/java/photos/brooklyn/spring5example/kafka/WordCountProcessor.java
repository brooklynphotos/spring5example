package photos.brooklyn.spring5example.kafka;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.Duration;
import java.util.Arrays;

@EnableBinding(KafkaStreamsProcessor.class)
public class WordCountProcessor {

    /**
     *
     * @param input
     * @return The resulting KStream contains the word and its corresponding count in that time window
     */
    @StreamListener("input")
    @SendTo("output")
    public KStream<?, WordCount> process(KStream<Object, String> input) {
        return input
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .map((k, v) -> new KeyValue<>(v, v))
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                .count(Materialized.as("wordcounts"))
                .toStream()
                .map((k, v) -> new KeyValue<>(null, new WordCount(k.key(), v)));
    }
}
