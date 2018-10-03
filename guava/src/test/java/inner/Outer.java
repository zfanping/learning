package inner;

/**
 * Created by wshcatkin on 2018-09-08.
 */
public class Outer {
    private static int i;
    private int j;

    class Inner {
        private int inner_i;

        private void inner_f1() {
        }
    }

    private static class Static_Inner {
        private static int static_inner_i;
        private int static_inner_j;

        private void static_inner_f1() {
            i = 0;
        }

    }

    public void out_f1() {
        Inner inner = new Inner();
        inner.inner_i = 10;
    }

    public static void out_f2() {
        Outer outer = new Outer();
        Inner inner = outer.new Inner();
        inner.inner_i = 0;
        inner.inner_f1();
    }

    public static void main(String[] args) {
        Outer out = new Outer();
        Inner inner = out.new Inner();
        inner.inner_i = 0;
        inner.inner_f1();

        Static_Inner static_inner = new Static_Inner();

    }
}
