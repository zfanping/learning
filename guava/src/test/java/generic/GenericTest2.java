package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wshcatkin on 2018-08-28.
 */
public class GenericTest2 {

    public void test() {
        List<? extends Father> list1 = new ArrayList<Father>();
        List<? extends Father> list2 = new ArrayList<Son>();
        List<? extends Father> list3 = new ArrayList<LeiFeng>();

        Father father = list1.get(0);//读取出来的东西只能存放在Father或它的基类里。
        Object object = list1.get(0);//读取出来的东西只能存放在Father或它的基类里。
        Human human = list1.get(0);//读取出来的东西只能存放在Father或它的基类里。
        Son son = (Son)list1.get(0);
    }

    class Human {
    }

    class Father extends Human {
    }

    class Son extends Father {
    }

    class LeiFeng extends Father {
    }
}

