package com.example.weixin_manage.support;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckSignatureUtil {
    
    //token 令牌标识、和微信开发者中心配置服务器一致
    private static String token = "yukefu_123";
    
    
    /**
     * 校验微信加密签名
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce    随机数
     * @return 签名是否正确
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
         // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuffer content = new StringBuffer();
        for (String temp : arr) {
            content.append(temp);
        }
         // 将三个参数字符串拼接成一个字符串进行sha1加密
        String currSignature = getSha1(content.toString());
        return currSignature!=null?currSignature.equals(signature.toUpperCase()):false;
    }
    
    
    /**
     * 字符串 sha1 加密
     * @param str 需加密字符串 
     * @return 加密后字符串
     */
    public static String getSha1(String str){
       if (null == str || 0 == str.length()){
            return null;
        }
       String byteToStr = "";
       try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(str.getBytes("UTF-8"));
            byte[] digest = md.digest();
            byteToStr = byteToStr(digest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteToStr;
    }
    

    /**
     * 将字节数组转换为十六进制字符串
     * @param byteArray 字节数组
     * @return 十六进制字符串
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (byte mByte : byteArray) {
            strDigest += byteToHexStr(mByte);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * @param mByte 字节
     * @return 十六进制字符串
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
    
}