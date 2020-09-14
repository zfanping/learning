package java8InAction;

import java.util.ArrayList;
import java.util.List;

public class ProviderClient {
    private List<Provider<? extends Number>> list;

    public <T>void test(Number num) {
        List<? extends Number> numbers = new ArrayList<Number>();
        ArrayList<Number> arrayList = new ArrayList<Number>();
        arrayList.add(num);
        numbers = arrayList;
    
        for (Provider<? extends Number> provider : list) {
            provider.prepare(numbers.get(0));
        }
    }

}
