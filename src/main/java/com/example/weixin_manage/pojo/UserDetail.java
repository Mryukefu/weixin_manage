package com.example.weixin_manage.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2021-09-02
 */

@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信号码
     */
    @TableField("weixinCode")
    private String weixinCode;

    /**
     * 微信号码
     */
    @TableField("qqCode")
    private String qqCode;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证信息
     */
    @TableField("idCard")
    private String idCard;

    /**
     * 其他信息
     */
    @TableField("otherInfo")
    private String otherInfo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Long ctime;

    @TableField("state")
    private Integer state;
    @TableField("recommendName")
    private String recommendName;
    @TableField("entryTime")
    private String entryTime;



}
