package effective;

import junit.framework.TestCase;

/**
 * Created by wshcatkin on 2018-09-08.
 */
public class StringTest extends TestCase {
    public void test() {
        String a = "a";
        assertTrue(a == a.intern());
    }
}
