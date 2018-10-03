package annotation;

import java.lang.annotation.*;

/**
 * Created by wshcatkin on 2018-09-08.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}

