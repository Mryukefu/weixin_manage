package com.example.weixin_manage.controller;

import com.example.weixin_manage.result.JsonResult;
import com.example.weixin_manage.service.UserDetailService;
import com.example.weixin_manage.vo.WxUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2021-09-02
 */
@RestController
@RequestMapping("wx/user")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping()
    public JsonResult postWxUser(@RequestBody WxUserVO wxUserVO) {
        return JsonResult.successFail(userDetailService.postWxUser(wxUserVO));
    }


    @PutMapping()
    public JsonResult updateWxUser(@RequestBody WxUserVO wxUserVO) {
        return JsonResult.successFail(userDetailService.updateWxUser(wxUserVO));
    }

    @DeleteMapping()
    public JsonResult deleteWxUser(@RequestParam(value = "userId")Integer userId) {
        return JsonResult.successFail(userDetailService.deleteWxUser(userId));
    }

    @GetMapping()
    public JsonResult wxUserList(WxUserVO wxUserVO) {
        return JsonResult.success(userDetailService.wxUserList(wxUserVO));
    }

}
