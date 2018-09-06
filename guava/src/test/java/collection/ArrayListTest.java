package collection;

import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wshcatkin on 2018-09-01.
 */
public class ArrayListTest extends TestCase{
    public void test() throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        Class c = ArrayList.class;

        Field field = c.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] elementData = (Object[]) field.get(list);

        assertEquals(0, elementData.length);

        list.add("a");

        assertEquals(0,elementData.length);
        elementData = (Object[]) field.get(list);
        assertEquals(10, elementData.length);

        list.add("b");

        assertEquals(10, elementData.length);
        elementData = (Object[]) field.get(list);
        assertEquals(10, elementData.length);
    }

}
