package collection;

import junit.framework.Test;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wshcatkin on 2018-08-30.
 */
public class HashCodeTest extends TestCase {
    public void test() {
        TestObj obj1 = new TestObj();
        TestObj obj2 = new TestObj();
        assertFalse(obj1.equals(obj2));
        System.out.println("obj1.hashCode() = " + obj1.hashCode());
        System.out.println("obj2.hashCode() = " + obj2.hashCode());

        TestObj obj3 = new TestObjWithEqual();
        TestObj obj4 = new TestObjWithEqual();

        Set<TestObj> objs = new HashSet<TestObj>();
        objs.add(obj3);
        assertFalse(objs.contains(obj4));


        TestObj obj5 = new TestObjectWithEqualAndHash();
        TestObj obj6 = new TestObjectWithEqualAndHash();
        objs.add(obj5);
        assertTrue(objs.contains(obj6));
    }

    private class TestObj {
        protected int value;

        public TestObj() {
        }

        public TestObj(int value) {
            this.value = value;
        }


    }

    private class TestObjWithEqual extends TestObj {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestObj testObj = (TestObj) o;

            return value == testObj.value;
        }

    }

    private class TestObjectWithEqualAndHash extends TestObjWithEqual {
        @Override
        public int hashCode() {
            return value;
        }
    }
}
