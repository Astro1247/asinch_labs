import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Lab1 {
    public static void main(String[] args) {
        int[] intArr1 = {15, 11, 0, 7, 11, 45, -2, 1, 1, 15};
        Arrays.stream(intArr1).forEach(System.out::println);
        System.out.println("Array");
        System.out.println("\nTask #1 (10). Increasing each element by its index\n");
        List<Integer> intStream1 = IntStream.range(0, intArr1.length).mapToObj(i -> intArr1[i] + i).toList();
        intStream1.forEach(System.out::println);
        System.out.println("Oops.. Picked wrong variant..");

        System.out.println("\nTask #1 (12). Set element to 0 if its index is odd\n");
        List<Integer> intStream2 = IntStream.range(0, intStream1.size()).mapToObj(i -> (i%2==0)? intStream1.get(i) :0).toList();
        intStream2.forEach(System.out::println);

        System.out.println("\nTask #2 (8). Find the max element, its index and value\n");
        IntStream.range(0, intStream2.size())
                .reduce((a,b)->intStream2.get(a)<intStream2.get(b)? b: a)
                .ifPresent(ix->System.out.println("Index "+ix+", value "+intStream2.get(ix)));

        System.out.println("\nTask #3 (16). Check if arr is ordered in descending way.\n");
        boolean sortedDescending =
                IntStream.range(0, intStream2.size()-1)
                        .allMatch(i -> intStream2.get(i).compareTo(intStream2.get(i+1)) >= 0);
        System.out.printf("Stream is ordered in descending check result: %s", sortedDescending);

        System.out.println("\nTask #4 (7). Find sum of all elements.\n");
        System.out.printf("Sum is: %s", intStream2.stream().mapToInt(Integer::intValue).sum());

        System.out.println("\nTask #5 (5). Find count of elements bigger than 0.\n");
        System.out.printf("Count of elements bigger than 0: %s", intStream2.stream().filter(i -> i>0).count());
    }
}
