package com.example.weixin_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.weixin_manage.pojo.UserDetail;
import com.example.weixin_manage.vo.WxUserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2021-09-02
 */
public interface UserDetailService extends IService<UserDetail> {

    Boolean postWxUser(WxUserVO wxUserVO);

    Boolean updateWxUser(WxUserVO wxUserVO);

    IPage wxUserList(WxUserVO wxUserVO);

    Boolean deleteWxUser(Integer id);

    void ask(HttpServletRequest request, HttpServletResponse response)  throws Exception;

}
