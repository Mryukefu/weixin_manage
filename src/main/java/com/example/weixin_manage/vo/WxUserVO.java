package com.example.weixin_manage.vo;

import lombok.Data;

/**
 * class desc
 * todo
 *
 * @author ykf
 * @date 2021/9/2 18:01
 */
@Data
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

    private String recommendName;

    private String entryTime;
}
