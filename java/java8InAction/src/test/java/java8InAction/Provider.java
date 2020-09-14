package java8InAction;

public interface Provider<T extends Number> {
    void prepare(T number);
}
