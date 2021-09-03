package com.example.weixin_manage.pojo;

/**
 * 消息实体类
 * @author Code猿猿
 *
 */
public class BaseMessage {
     
    /**
     * 开发者微信号
     */
    public String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    public String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    public long CreateTime;
    /**
     * text
     */
    public String MsgType ;
     
    public String getToUserName() {
        return ToUserName;
    }
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    public String getFromUserName() {
        return FromUserName;
    }
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    public long getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }
    public String getMsgType() {
        return MsgType;
    }
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
     
}