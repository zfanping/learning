package java8InAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**

 */
public class AnnotationTest {
    public static <E> void printArray(E[] elements) {
    }

    public static <E extends Number> void printNumArray(E[] elements) {

    }

    public static <T extends Comparable<T>> T max(T... elements) {
        return null;
    }

    public static void printData(List<?> data) {

    }

    public static void printUpperData(List<? extends Number> data) {
        
    }

    public static void testAnimal(Animal<? extends Number> animal) {

    }

    public static void main(String[] args) {
        printArray(new String[]{});
        printNumArray(new Number[0]);

        max("a", "b");


        List<Number> numberList = new ArrayList<Number>();
        printUpperData(numberList);
        printData(Collections.emptyList());
        printUpperData(Collections.<Number>emptyList());

        List<? extends Number> numberList2 = new ArrayList<Integer>();


        Dog<String> dog1 = new Dog<String>();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog("x");
        Dog dog4 = new Dog(1);


//        Animal<Number> animal1 = new Dog<Integer>(); // compile error
        Animal<? extends Number> animal2 = new Dog<Integer>();
        Animal<? extends Number> animal3 = new Dog<Number>();
        testAnimal(animal2);
        testAnimal(animal3);
        testAnimal(new Dog<Integer>());


    }

    public void testExtend() {
        List<? extends Number> numList1 = new ArrayList<Integer>();
        List<? extends Number> numList2 = new ArrayList<Long>();
//        numList1.add(Long.valueOf(1)); // compile error
//        numList1.add(Integer.valueOf(1)) // compile error
    }

    public void testSuper() {
        List<? super Number> list = new ArrayList<Number>();
        list.add(1L);
        list.add(1.0);
    }

    public void testCollectionAddAll() {
        List<Number> list1 = new ArrayList<Number>();
        ArrayList<? extends Number> arrayList = new ArrayList<Number>();
        list1.addAll(arrayList);
//        arrayList.addAll(list1);
    }

}
