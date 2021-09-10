package com.example.weixin_manage.support;

import com.example.weixin_manage.pojo.TextMessage;
import com.example.weixin_manage.pojo.UserDetail;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    public static String Message_Text = "text";
    public static String Message_News = "news";
    public static String Message_Image = "image";
    public static String Message_Voice = "voice";
    public static String Message_Video = "video";
    public static String Message_ShortVideo = "shortvideo";
    public static String Message_Location = "location";
    public static String Message_Link = "link";
    public static String Message_Event= "event";
    public static String Message_Event_Sub= "subscribe";

    /**
     * xml转map集合
     * @param request
     * @return
     * @throws IOException
     */

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String,String>();

        SAXReader reader = new SAXReader();

        InputStream is = request.getInputStream();

        Document document = reader.read(is);

        Element root = document.getRootElement();

        List<Element> list = root.elements();

        for (Element e:list) {
            map.put(e.getName(), e.getText());
        }
        is.close();
        return map;
    }

    /**
     * 将文本消息转换成xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    /**
     * 文本回复
     * @param toUserName
     * @param fromUserName
     * @param msgType
     * @param content
     * @return
     */
    public static String replyByKeyWord(String toUserName,String fromUserName,String msgType,String content){
        TextMessage message = new TextMessage();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setMsgType(msgType);
        message.setCreateTime(new Date().getTime());
        message.setContent(content);
        return MessageUtil.textMessageToXml(message);
    }


    public static String name = "姓名";
    public static String idCard = "身份证信息";
    public static String phone = "手机号码";
    public static String weixin = "微信号码";
    public static String qq = "qq号码";


    public static String mainMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("请输入如下格式中一种\n\n");
        sb.append("姓名/您的姓名"+"\n\n");
        sb.append("身份证信息/您的身份证号码"+"\n\n");
        sb.append("手机号码/您的手机号码\n\n");
        sb.append("微信号码/您的微信号码\n\n");
        sb.append("qq号码/qq号码\n\n");
        sb.append("可查询您的所属信息\n\n");
        return sb.toString();
    }

    public static String mainMenuKey(List<UserDetail> userDetails){

        StringBuffer sb = new StringBuffer();
        for (UserDetail userDetail : userDetails) {
            if (StringUtils.isNotBlank(userDetail.getName())){
                sb.append("姓名："+userDetail.getName()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getIdCard())){
                sb.append("身份证信息："+userDetail.getIdCard()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getPhone())){
                sb.append("手机号码："+userDetail.getPhone()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getWeixinCode())){
                sb.append("微信号码："+userDetail.getWeixinCode()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getQqCode())){
                sb.append("qq号码："+userDetail.getQqCode()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getOtherInfo())){
                sb.append("其他信息："+userDetail.getOtherInfo()+"\n\n");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }


}
