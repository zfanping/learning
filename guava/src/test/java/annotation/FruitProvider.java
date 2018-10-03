package annotation;

/**
 * @Target(ElementType.FIELD)
 * @Retention(RetentionPolicy.RUNTIME) Created by wshcatkin on 2018-09-08.
 */
public @interface FruitProvider {
    /**
     * 供应商编号
     *
     * @return
     */
    public int id() default -1;

    /**
     * 供应商名称
     *
     * @return
     */
    public String name() default "";

    /**
     * 供应商地址
     *
     * @return
     */
    public String address() default "";
}
