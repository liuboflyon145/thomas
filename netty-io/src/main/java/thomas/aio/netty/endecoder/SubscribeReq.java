package thomas.aio.netty.endecoder;

import java.io.Serializable;

/**
 * Created by liubo on 16/6/17.
 */
public class SubscribeReq implements Serializable{

    private static final long serialVersionUID = 1L;
    private int subRedID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;

    public int getSubRedID() {
        return subRedID;
    }

    public void setSubRedID(int subRedID) {
        this.subRedID = subRedID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subRedID=" + subRedID +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
