package Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab3 {
    public static List<List<Integer>> array = new ArrayList<>();
    public static List<Integer> array1 = new ArrayList<>();
    public static List<Integer> array2 = new ArrayList<>();
    static int size = ThreadLocalRandom.current().nextInt(5, 10);

    public static void main(String[] args) {
        for (int i=0; i<size; i++) {
            array1.add(ThreadLocalRandom.current().nextInt(-10, 10));
            array2.add(ThreadLocalRandom.current().nextInt(-10, 10));
        }

        //array.add(array1);
        //array.add(array2);

        System.out.println(array1 + "\n" + array2);

        Processor proc = new Processor();
        List<Integer> l = new ArrayList<>();
        //CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 1);

        array1 = proc.sortList(proc.isMoreThanMax(array1));
        array2 = proc.sortList(proc.isOdd(array2));
        //array.forEach(list -> cf.thenApply(fn -> (list.equals(array1))? proc.isMoreThanMax(list): proc.isOdd(list)));
        //array.forEach(list -> cf.thenApply(fn -> proc.sortList(list)));
        List<Integer> finalList = array2.stream().filter(i -> !array1.contains(i)).toList();


        //cf.join();
        System.out.println("\n" + array1 + "\n" + array2 + "\n" + finalList);
    }

}


class Processor {
    public ArrayList<Integer> isMoreThanMax(List<Integer> intArr) {
        int max = intArr.stream().mapToInt(i -> i).max().getAsInt();

        System.out.println("\nMax element: " + max);
        int halfmax = (int) (max*0.5);
        System.out.println("0.5 of max element: " + halfmax);
        System.out.println("Elements: ");

        Supplier<Stream<Integer>> suppl1 = () -> intArr.stream().filter(i -> (i > halfmax));
        suppl1.get().forEach(i -> System.out.print(i+" "));
        return suppl1.get().collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> isOdd(List<Integer> intArr) {
        System.out.println("\nOdd elements: ");
        List<Integer> list = intArr.stream().filter(i -> (i%2==0)).toList();
        list.forEach(i -> System.out.printf("%s ",i));
        return list;
    }

    public List<Integer> sortList(List<Integer> intArr) {
        System.out.println("\nSorted elements: ");
        List<Integer> list = intArr.stream().sorted(Integer::compare).toList();
        list.forEach(i -> System.out.printf("%s ",i));
        return list;
    }
}
