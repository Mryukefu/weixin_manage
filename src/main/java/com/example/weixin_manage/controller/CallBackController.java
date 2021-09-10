package com.example.weixin_manage.controller;

import com.example.weixin_manage.service.UserDetailService;
import com.example.weixin_manage.support.CheckSignatureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2021-09-02
 */
@RestController
@RequestMapping(value = "call/back")
public class CallBackController {

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("test")
    public String test(){
       return "ok";
    }


    /**
     * 验证微信消息
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping()
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if(CheckSignatureUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 验证微信消息
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping()
    public void ask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        userDetailService.ask(request,response);
    }

}
