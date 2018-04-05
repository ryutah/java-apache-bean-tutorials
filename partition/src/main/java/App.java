import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.Partition;
import org.apache.beam.sdk.values.PCollectionList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    private static class SampleDoFn extends DoFn<Integer, Integer> {
        private int index;

        public SampleDoFn(int index) {
            super();
            this.index = index;
        }

        @ProcessElement
        public void process(ProcessContext c) {
            System.out.println(String.format("Index : %d, Value : %d", index, c.element()));
            c.output(c.element());
        }
    }

    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        PCollectionList<Integer> partitions =
                p.apply(createInput()).apply(Partition.of(10, (it, num) -> it / num);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        partitions
                .getAll()
                .forEach(c -> c.apply(ParDo.of(new SampleDoFn(atomicInteger.incrementAndGet()))));

        p.run().waitUntilFinish();
    }

    private static Create.Values<Integer> createInput() {
        List<Integer> inputs =
                IntStream.range(1, 10000).mapToObj(Integer::new).collect(Collectors.toList());
        return Create.of(inputs);
    }
}