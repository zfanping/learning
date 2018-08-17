package netty.guide.ch07;

/**
 * Created by frank on 2018-08-13.
 */
public class SubscribeResp {
    private int id;
    private int respCode;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "id=" + id +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
