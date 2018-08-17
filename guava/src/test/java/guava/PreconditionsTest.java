package guava;

import com.google.common.base.Preconditions;
import junit.framework.TestCase;

/**
 * Created by frank on 2018-08-03.
 */
public class PreconditionsTest extends TestCase {
    public void test() {
        Preconditions.checkNotNull("name", "not null");
        Preconditions.checkNotNull("test", "not null", 'c', 10);

    }

}
