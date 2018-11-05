package stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciTuple {

    public static void main(String[] args) {
        System.out.print("Tuples - ");
        Stream.iterate(new int [] {0, 1}, t -> new int [] {t[1], t[0] + t[1]}).limit(10).forEach(Util::print);
        System.out.print("\nTuples - ");
        Stream.iterate(new int [] {0, 1}, t -> new int [] {t[1], t[0] + t[1]}).limit(10).map(x -> x[0]).
                forEach(Util::print);
        System.out.print("\nTuples via supplier - ");
        IntSupplier supplier = new IntSupplier() {
            int a = 0;
            int b = 1;

            @Override
            public int getAsInt() {
                int oldA = a, oldB = b;
                b = a+b;
                a = oldB;
                return oldA;
            }
        };
        IntStream.generate(supplier).limit(10).forEach(Util::print);
    }

}
