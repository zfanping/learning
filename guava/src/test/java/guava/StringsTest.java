package guava;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frank on 2018-08-03.
 */
public class StringsTest extends TestCase {
    public void test_isNullOrEmpty() {
        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty(""));
        assertFalse(Strings.isNullOrEmpty(" "));
    }

    public void test_commonPrefix() {
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        assertEquals("com.jd.coo.H", Strings.commonPrefix(a, b));
    }

    public void test_commonSuffix() {
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        assertEquals(".Hello", Strings.commonSuffix(c, d));
    }

    public void test_pad() {
        assertEquals("12300", Strings.padEnd("123", 5, '0'));
        assertEquals("00123", Strings.padStart("123", 5, '0'));
    }

    public void test() {
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }

    public void test_split_twice() {
        String s = "a=1;;b=2,c=3";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator("=").split(s);
        assertEquals(3, kvs.size());
        assertEquals("1", kvs.get("a"));
        assertEquals("2", kvs.get("b"));
        assertEquals("3", kvs.get("c"));
    }

    public void test_join() {
        String joinResult = Joiner.on(" ").join(new String[]{"hello", "world"});
        assertEquals("hello world", joinResult);

        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        assertEquals("a=b,c=d", mapJoinResult);
    }

    public void test_equal() {
        assertEquals(true, Objects.equal(null, null));
        assertEquals(false, Objects.equal(null, new Object()));
    }

    public void test_md5(){

    }
}
