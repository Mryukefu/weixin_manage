package com.example.weixin_manage.vo;

/**
 * class desc
 * todo
 *
 * @author ykf
 * @date 2021/9/2 18:01
 */

public class WxUserVO {

    private long pageSize;
    private long pageNum;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信号码
     */

    private String weixinCode;

    /**
     * 微信号码
     */

    private String qqCode;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证信息
     */

    private String idCard;

    /**
     * 其他信息
     */

    private String otherInfo;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeixinCode() {
        return weixinCode;
    }

    public void setWeixinCode(String weixinCode) {
        this.weixinCode = weixinCode;
    }

    public String getQqCode() {
        return qqCode;
    }

    public void setQqCode(String qqCode) {
        this.qqCode = qqCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
