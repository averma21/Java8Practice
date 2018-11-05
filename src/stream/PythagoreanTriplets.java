package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanTriplets {

    public static void main(String[] args) {
        List<Integer> aList = new ArrayList<>(100);
        List<Integer> bList = new ArrayList<>(100);
        for (int i = 1; i <= 100; i++) {
            aList.add(i);
            bList.add(i);
        }
        IntStream aStream = IntStream.rangeClosed(1, 100);
        IntStream bStream = IntStream.rangeClosed(100, 200);
//        Stream<int []> triplets = aStream.boxed().flatMap(
//                a -> bStream.filter(b -> (Math.sqrt(a*a + b*b) % 1.0 == 0)).
//                mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a*a + b*b)})
//        );
        //aStream.boxed().flatMap(a -> bStream.boxed()).forEach(System.out::print);
        //triplets.limit(5).forEach(Util::print);
        aList.stream().flatMap(a -> bList.stream().filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .map(b -> new int[] {a, b, (int)Math.sqrt(a*a + b*b)})).forEach(Util::print);
    }

}
