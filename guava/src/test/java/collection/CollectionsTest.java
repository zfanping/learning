package collection;

import junit.framework.TestCase;

import java.util.*;

/**
 * Created by wshcatkin on 2018-08-28.
 */
public class CollectionsTest extends TestCase {
    public void testReadOnly() {
        List<String> list = new ArrayList<String>();
        list.add("a");

        List<String> unmodifiableList = Collections.unmodifiableList(list);

        list.add("b");

        assertTrue(unmodifiableList.contains("a"));
        assertTrue(unmodifiableList.contains("b"));

    }

    public void test_thread_safe() {
        List<String> list = new ArrayList<String>();

        List<String> synchronizedList = Collections.synchronizedList(list);
    }

    public void test_singleton(){
        Collections.singleton("String");
        Collections.emptyList();
    }

}
