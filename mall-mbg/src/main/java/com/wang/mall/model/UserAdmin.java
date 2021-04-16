package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UserAdmin implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String userName;

    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "最后登陆时间")
    private Date logintime;

    @ApiModelProperty(value = "账号启用状态： 0->启用 ，1->禁用")
    private Integer status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAdmin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", emall='" + email + '\'' +
                ", nick_name='" + nickName + '\'' +
                ", note='" + note + '\'' +
                ", create_time=" + createtime +
                ", login_time=" + logintime +
                ", status=" + status +
                '}';
    }
}
