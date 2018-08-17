package netty.guide;

import junit.framework.TestCase;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.UnsupportedEncodingException;

/**
 * Created by frank on 2018-08-17.
 */
public class EncodeTest extends TestCase {
    public void test_utf8() throws UnsupportedEncodingException {
        assertEquals(3, "中".getBytes("UTF-8").length);
        assertEquals(6, "中中".getBytes("UTF-8").length);
        assertEquals(6, "中国".getBytes("UTF-8").length);
    }

    public void test_gbk() throws UnsupportedEncodingException {
        assertEquals(2, "中".getBytes("GBK").length);
        assertEquals(4, "中中".getBytes("GBK").length);
        assertEquals(4, "中国".getBytes("GBK").length);
    }

    public void test_char() {
        char c = '中';
        System.out.println(c);

    }

}
