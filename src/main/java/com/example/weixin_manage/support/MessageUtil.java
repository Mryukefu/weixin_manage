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
    public static String weixin = "微信号";
    public static String qq = "QQ号";


    public static String mainMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("请输入如下格式中一种，查询会员信息；例如：姓名/张三\n\n");
        sb.append("姓名/会员真实姓名\n\n");
        sb.append("手机号码/会员登记手机号码\n\n");
        sb.append("微信号/会员登记微信号\n\n");
        sb.append("QQ号/会员登记QQ号\n\n");
        return sb.toString();
    }

    public static String mainMenuKey(List<UserDetail> userDetails){

        StringBuffer sb = new StringBuffer();
        for (UserDetail userDetail : userDetails) {
            if (StringUtils.isNotBlank(userDetail.getName())){
                sb.append("姓名："+userDetail.getName()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getPhone())){
                sb.append("手机号码："+userDetail.getPhone()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getWeixinCode())){
                sb.append("微信号："+userDetail.getWeixinCode()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getQqCode())){
                sb.append("qq号："+userDetail.getQqCode()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getRecommendName())){
                sb.append("推荐姓名："+userDetail.getRecommendName()+"\n\n");
            }
            if (StringUtils.isNotBlank(userDetail.getEntryTime())){
                sb.append("入职时间："+userDetail.getEntryTime()+"\n\n");
            }
            sb.append("查询成功，该会员为功诚平台审核所属会员\n\n");
        }
        return sb.toString();
    }


}
