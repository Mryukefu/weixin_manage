package com.example.weixin_manage.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.weixin_manage.mapper.UserDetailMapper;
import com.example.weixin_manage.pojo.UserDetail;
import com.example.weixin_manage.service.UserDetailService;
import com.example.weixin_manage.support.MessageUtil;
import com.example.weixin_manage.support.StringUtils;
import com.example.weixin_manage.vo.WxUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2021-09-02
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {


    /**
     *
     *  添加用户信息
     * @param wxUserVO
     * @return {@code java.lang.Boolean}
     * @author ykf
     * @date 2021/9/2 18:05
     */
    @Override
    public Boolean postWxUser(WxUserVO wxUserVO) {
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(wxUserVO,userDetail);
        userDetail.setCtime(System.currentTimeMillis());
        return this.save(userDetail);
    }

    @Override
    public Boolean updateWxUser(WxUserVO wxUserVO) {
        UserDetail userDetail = this.getById(wxUserVO.getUserId());
        if (userDetail==null){
            return false;
        }
        UserDetail detail = new UserDetail();
        BeanUtils.copyProperties(wxUserVO,detail);
        return this.updateById(detail);
    }

    @Override
    public IPage wxUserList(WxUserVO wxUserVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        String name = wxUserVO.getName();
        if (StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }

        String idCard = wxUserVO.getIdCard();
        if (StringUtils.isNotBlank(idCard)){
            queryWrapper.eq("idCard",idCard);
        }

        String phone = wxUserVO.getPhone();
        if (StringUtils.isNotBlank(phone)){
            queryWrapper.eq("phone",phone);
        }


        String qqCode = wxUserVO.getQqCode();
        if (StringUtils.isNotBlank(qqCode)){
            queryWrapper.like("qqCode",qqCode);
        }

        String weixinCode = wxUserVO.getWeixinCode();
        if (StringUtils.isNotBlank(weixinCode)){
            queryWrapper.like("weixinCode",weixinCode );
        }


        String otherInfo = wxUserVO.getOtherInfo();
        if (StringUtils.isNotBlank(otherInfo)){
            queryWrapper.like("otherInfo",otherInfo );
        }
        queryWrapper.like("state",1 );
        IPage page = this.page(new Page(wxUserVO.getPageNum(),wxUserVO.getPageSize()), queryWrapper);
        return page;

    }

    @Override
    public Boolean deleteWxUser(Integer userId) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userId);
        userDetail.setState(2);
        return this.updateById(userDetail);
    }

    @Override
    public void ask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(req);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            System.out.println(toUserName + fromUserName + msgType + content);
            String text = null;
            if ("text".equals(msgType)) {
                String[] split = content.split("/");
                if (split==null||split.length!=2){
                    text = MessageUtil.replyByKeyWord(toUserName, fromUserName, MessageUtil.Message_Text,MessageUtil.mainMenu());
                }
                QueryWrapper queryWrapper = new QueryWrapper();
                String key = split[0];
                if (MessageUtil.name.equals(key)){
                    queryWrapper.like("name",split[1]);
                }
                if (MessageUtil.idCard.equals(key)){
                    queryWrapper.eq("idCard",split[1]);
                }
                if (MessageUtil.phone.equals(key)){
                    queryWrapper.like("phone",split[1]);
                }
                if (MessageUtil.qq.equals(key)){
                    queryWrapper.like("qqCode",split[1]);
                }
                if (MessageUtil.weixin.equals(key)){
                    queryWrapper.like("weixinCode",split[1]);
                }
                List list = this.listMaps(queryWrapper);
                String s = MessageUtil.mainMenu1(list);
                text = MessageUtil.replyByKeyWord(toUserName, fromUserName, MessageUtil.Message_Text, s);

               } else if (MessageUtil.Message_Event.equals(msgType)) {
                if ("subscribe".equals(map.get("Event"))) {
                    text = MessageUtil.replyByKeyWord(toUserName, fromUserName, MessageUtil.Message_Text,MessageUtil.mainMenu());
                }
            }
            System.out.println(text);
            writer.print(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }


}
