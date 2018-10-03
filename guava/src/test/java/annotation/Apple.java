package annotation;

/**
 * Created by wshcatkin on 2018-09-08.
 */
public class Apple {
    @FruitName("Apple")
    private String apppleName;

    @FruitColor(FruitColor.Color.BULE)
    private String appleColor;

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public String getApppleName() {
        return apppleName;
    }

    public void setApppleName(String apppleName) {
        this.apppleName = apppleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
