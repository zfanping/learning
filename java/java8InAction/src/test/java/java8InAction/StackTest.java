package java8InAction;

import java.util.Collection;
import java.util.HashSet;

import junit.framework.TestCase;

public class StackTest extends TestCase {
    public void test() {
        Stack<Number> stack = new Stack<Number>();
        Iterable<Integer> integers = new HashSet<Integer>();
        stack.pushAll(integers);

        Collection<Object> objects = new HashSet<Object>();
        stack.popAll(objects);
    }
}