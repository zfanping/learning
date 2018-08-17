package guava;

import com.google.common.base.Predicate;
import com.google.common.collect.*;
import junit.framework.TestCase;

import java.util.List;
import java.util.Set;

/**
 * Created by frank on 2018-08-03.
 */
public class CollectionsTest extends TestCase {
    public void test_immutable() {
        Set<String> immutableNamedColors = ImmutableSet.<String>builder()
                .add("red", "green", "black", "white", "grey")
                .build();

        ImmutableSet.of("a", "b", "c");

        ImmutableSet.copyOf(new String[]{"a", "b", "c"});
    }

    public void test_multiSet() {
        Multiset multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");

        assertEquals(2, multiset.size());
        assertEquals(2, multiset.contains("a"));
    }

    public void test_biMap() {
        BiMap map = ImmutableBiMap.of("a", 1, "b", 2);

        assertEquals(1, map.get("a"));
        assertEquals("a", map.inverse().get(1));
    }

    public void test_multiMap() {
        Multimap<String, String> map = HashMultimap.create();
        map.put("a", "1");
        map.put("a", "2");

        assertEquals(2, map.size());
        assertEquals(2, map.get("a").size());
    }

    public void test_table() {
        Table table = HashBasedTable.create();
        table.put("x1", "y1", "x1y1");
        table.put("x1", "y2", "x1y2");
        table.put("x2", "y1", "x2y1");
        table.put("x2", "y2", "x2y2");

        assertEquals("x1y1", table.row("x1").get("y1"));
        assertEquals("x1y2", table.row("x1").get("y2"));
    }

    public void test_iterators() {
        List<String> list = Lists.newArrayList("a", "b", "c");

        Predicate<String> condition = new Predicate<String>() {
            public boolean apply(String input) {
                return "a".equals(input);
            }
        };
        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
        assertEquals(false, Iterators.all(list.iterator(), condition));
    }
}
