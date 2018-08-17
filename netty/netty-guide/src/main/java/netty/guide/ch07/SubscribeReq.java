package netty.guide.ch07;

import java.io.Serializable;

/**
 * Created by frank on 2018-08-13.
 */
public class SubscribeReq implements Serializable {
    private int id;
    private String username;
    private String productName;
    private String productNumber;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscriberReq{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", productName='" + productName + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
