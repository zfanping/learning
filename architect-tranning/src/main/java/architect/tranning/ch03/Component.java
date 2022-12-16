package architect.tranning.ch03;

public abstract class Component {
    private String name;

    protected Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(String.format("print %s(%s)", getClass().getSimpleName(), name));
    }
}
