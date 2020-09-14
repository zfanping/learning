package java8InAction;

import java.util.Collection;

public class Stack<E> {
    public void push(E e) {

    }

    public E pop() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}
