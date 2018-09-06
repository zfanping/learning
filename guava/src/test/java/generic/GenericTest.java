package generic;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wshcatkin on 2018-08-28.
 */
public class GenericTest extends TestCase {
    public void test() {
        System.out.println(List.class);
        System.out.println(String[].class);
        System.out.println(int[].class);

        Set<?> set = new HashSet<Integer>();

        String[] objs = new String[9];
        print(objs);

    }

    public void print(Object[] objs) {
        Arrays.toString(objs);
    }

    public void test_up_bound() {
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());

        //不能存入任何元素
        // p.set(new Fruit());    //Error
        // p.set(new Apple());    //Error

        //读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruit1 = p.get();
        Object newFruit2 = p.get();
        // Apple newFruit3=p.get();    //Error
    }

    public void test_down_bound() {
        Plate<? super Fruit> p = new Plate<Fruit>(new Fruit());

        //存入元素正常
        p.set(new Fruit());
        p.set(new Apple());

        //读取出来的东西只能存放在Object类里。
        // Apple newFruit1=p.get();    //Error
        // Fruit newFruit2=p.get();    //Error
        Object newFruit3 = p.get();
    }

    class Food {

    }

    class Meat extends Food {

    }

    class Fruit {

    }

    class Apple extends Fruit {

    }

    class Banana extends Fruit {
    }

    class Pork extends Meat {
    }

    class Beef extends Meat {
    }

    class RedApple extends Apple {
    }

    class GreenApple extends Apple {
    }

    class Plate<T> {
        private T item;

        public Plate(T t) {
            item = t;
        }

        public void set(T t) {
            item = t;
        }

        public T get() {
            return item;
        }
    }
}
