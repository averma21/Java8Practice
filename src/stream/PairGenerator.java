package stream;

import java.util.Arrays;
import java.util.List;

public class PairGenerator {

    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> listB = Arrays.asList(3, 4);
        listA.stream().flatMap(a -> listB.stream().map(b -> new int[] {a, b})).
                forEach(arr -> System.out.println("[" + arr[0] + ", " + arr[1] +"]"));
    }

}
