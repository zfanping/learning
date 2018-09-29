package com.example.demo.ch02;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by frank on 2018-09-29.
 */
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userame;
    private String password;
    private String email;
    private String info;
    private byte[] headImg;
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserame() {
        return userame;
    }

    public void setUserame(String userame) {
        this.userame = userame;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userame='" + userame + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                ", headImg=" + Arrays.toString(headImg) +
                ", createTime=" + createTime +
                '}';
    }
}
