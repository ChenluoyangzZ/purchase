package com.dingding.purchase.controller;

import com.dingding.purchase.Enum.IsCommentEnum;
import com.dingding.purchase.mapper.OrderItemsMapper;
import com.dingding.purchase.mapper.OrderStatusMapper;
import com.dingding.purchase.pojo.*;
import com.dingding.purchase.pojo.bo.CenterItemCommentBO;
import com.dingding.purchase.pojo.bo.CenterUserBO;
import com.dingding.purchase.service.ItemCommentService;
import com.dingding.purchase.service.OrderService;
import com.dingding.purchase.service.UserService;
import com.dingding.purchase.uitls.CookieUtils;
import com.dingding.purchase.uitls.RespondResultUtils;
import com.dingding.purchase.uitls.UploadUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;

@Api(tags = "个人中心接口")
@RequestMapping("/center")
@RestController
public class CenterController {

    @Autowired
    private UserService usersService;
    @Autowired
    private PassportController passportController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemCommentService itemCommentService;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;

    private final String FILEPATH = "E:\\Apache Tomcat\\" + File.separator + "webapps" + File.separator + "shop" +
            File.separator + "images" + File.separator + "faces";

    @ApiOperation("查询用户信息")
    @GetMapping("/userInfo")
    public String getUserInfo(@RequestParam String userId) {
        return RespondResultUtils.ok(usersService.queryUserForCenter(userId));
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/userInfo/update")
    @SneakyThrows
    public String updateUserInfo(@RequestParam String userId,
                                 @RequestBody CenterUserBO centerUserBO,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        Users users = usersService.updateUserForCenter(userId, centerUserBO);
        passportController.frontCookieMsg(users);
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "user", objectMapper.writeValueAsString(users), true);
        return RespondResultUtils.ok();
    }

    @ApiOperation("头像上传")
    @PostMapping("/userInfo/uploadFace")
    @SneakyThrows
    public String uploadFace(@RequestParam String userId,
                             MultipartFile file,
                             HttpServletResponse httpServletResponse,
                             HttpServletRequest httpServletRequest) {
        String fileName = UploadUtils.uploadFile(userId, FILEPATH, file,"face");
        Users users = usersService.queryUserForCenter(userId);
        passportController.frontCookieMsg(users);
        users.setFace(fileName);
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "user", objectMapper.writeValueAsString(users), true);
        usersService.updateUserFace(userId, fileName);
        return RespondResultUtils.ok();
    }


    @ApiOperation("查询订单信息")
    @PostMapping("/myorders/query")
    public String queryOrder(@RequestParam String userId,
                             @RequestParam String orderStatus,
                             @RequestParam Integer page,
                             @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return RespondResultUtils.errorMsg("传入id为空");
        }
        if (page == null) {
            page = ItemsController.MIX_PAGE;
        }
        if (pageSize == null) {
            page = ItemsController.MAX_PAGE;
        }
        return RespondResultUtils.ok(orderService.queryCenterOrderByOrderId(userId, orderStatus, page, pageSize));
    }

    @ApiOperation("商家发货")
    @PostMapping("/deliver")
    public String deliver(@RequestParam String orderId) {
        if (StringUtils.isBlank(orderId)) {
            RespondResultUtils.errorMsg("订单id不能为空");
        }
        orderService.updateOrderStatus(orderId);
        return RespondResultUtils.ok();
    }

    @ApiOperation("用户确定收货")
    @PostMapping("/myorders/confirmReceive")
    public String confirmelivery(@RequestParam String orderId,
                                 @RequestParam String userId) {
        if (StringUtils.isBlank(orderId) || StringUtils.isBlank(userId)) {
            RespondResultUtils.errorMsg("订单id不能为空");
        }
        orderService.updateOrderStatusColse(orderId);
        return RespondResultUtils.ok();
    }

    @ApiOperation("删除订单")
    @PostMapping("/myorders/delete")
    public String deleteOrder(@RequestParam String orderId,
                              @RequestParam String userId) {
        if (StringUtils.isBlank(orderId) || StringUtils.isBlank(userId)) {
            RespondResultUtils.errorMsg("订单id不能为空");
        }
        orderService.deleteOrder(orderId);
        return RespondResultUtils.ok();
    }


    @ApiOperation("保存用户评价")
    @PostMapping("/mycomments/saveList")
    public String saveUserComment(@RequestParam String orderId,
                                  @RequestParam String userId,
                                  @RequestBody List<CenterItemCommentBO> centerItemComment) {
        if (StringUtils.isBlank(orderId) || StringUtils.isBlank(userId)) {
            RespondResultUtils.errorMsg("订单id不能为空");
        }
        for (CenterItemCommentBO centerItemCommentBO : centerItemComment) {
            ItemsComments itemsComments = new ItemsComments();
            itemsComments.setId(Sid.nextShort());
            itemsComments.setCommentLevel(centerItemCommentBO.getCommentLevel());
            itemsComments.setItemId(centerItemCommentBO.getItemId());
            itemsComments.setContent(centerItemCommentBO.getContent());
            itemsComments.setItemName(centerItemCommentBO.getItemName());
            itemsComments.setItemSpecName(centerItemCommentBO.getItemSpecName());
            itemsComments.setUserId(userId);
            itemsComments.setCreatedTime(new Date());
            itemCommentService.addItemComments(itemsComments);
        }
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setIsComment(IsCommentEnum.YES.type);
        orders.setUpdatedTime(new Date());
        orderService.updateOrder(orders);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
        return RespondResultUtils.ok();
    }

    @ApiOperation("进行订单评价")
    @PostMapping("/mycomments/pending")
    public String saveUserComment(@RequestParam String orderId,
                                  @RequestParam String userId) {
        if (StringUtils.isBlank(orderId) || StringUtils.isBlank(userId)) {
            RespondResultUtils.errorMsg("订单id不能为空");
        }
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);
        //todo 添加订单的增删查改接口
        return RespondResultUtils.ok(orderItemsMapper.select(orderItems));
    }

    @ApiOperation("查询用户全部评论")
    @PostMapping("/mycomments/query")
    public String queryAllItemComment(@RequestParam String userId,
                                      @RequestParam Integer page,
                                      @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
           return RespondResultUtils.errorMsg("订单id不能为空");
        }
        if (page == null) {
            page = ItemsController.MIX_PAGE;
        }
        if (pageSize == null) {
            pageSize = ItemsController.MAX_PAGE;
        }
        return RespondResultUtils.ok(orderService.queryAllItemComment(userId, page, pageSize));
    }


    @ApiOperation("个人中心订单情况显示")
    @PostMapping("/myorders/statusCounts")
    public String querAllItemComment(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
         return    RespondResultUtils.errorMsg("订单id不能为空");
        }
        return RespondResultUtils.ok(orderService.queryCountByStatus(userId));
    }

    @ApiOperation("运送消息")
    @PostMapping("/myorders/trend")
    public String querAllItemComment(@RequestParam String userId,
                                     @RequestParam Integer page,
                                     @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
          return   RespondResultUtils.errorMsg("订单id不能为空");
        }
        if (page == null) {
            page = ItemsController.MIX_PAGE;
        }
        if (pageSize == null) {
            pageSize = ItemsController.MAX_PAGE;
        }
        return RespondResultUtils.ok(orderService.queryOrderStatus(userId, page, pageSize));
    }
}