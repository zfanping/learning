package architect.tranning.ch03;

import java.util.ArrayList;
import java.util.List;

public abstract class Container extends Component {
    private List<Component> children = new ArrayList<>();

    protected Container(String name) {
        super(name);
    }

    public void addChild(Component child) {
        children.add(child);
    }

    @Override
    public void print() {
        super.print();
        for (Component child : children) {
            child.print();
        }
    }
}
