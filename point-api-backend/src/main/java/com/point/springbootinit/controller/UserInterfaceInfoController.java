package com.point.springbootinit.controller;

import java.util.Date;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.point.apicommon.model.entity.InterfaceInfo;
import com.point.apicommon.model.entity.User;
import com.point.apicommon.model.entity.UserInterfaceInfo;
import com.point.apicommon.model.enums.PageEnum;
import com.point.springbootinit.annotation.AuthCheck;
import com.point.springbootinit.common.*;
import com.point.springbootinit.constant.UserConstant;
import com.point.springbootinit.exception.BusinessException;
import com.point.springbootinit.exception.ThrowUtils;
import com.point.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoAddRequest;
import com.point.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.point.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateRequest;
import com.point.springbootinit.service.InterfaceInfoService;
import com.point.springbootinit.service.UserInterfaceInfoService;
import com.point.springbootinit.service.UserService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口管理
 */
@RestController
@RequestMapping("/userInterfaceInfo")
@Slf4j
public class UserInterfaceInfoController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private UserService userService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    // region 增删改查

    /**
     * 创建
     *
     * @param userInterfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUserInterfaceInfo(@RequestBody UserInterfaceInfoAddRequest userInterfaceInfoAddRequest, HttpServletRequest request) {
        if (userInterfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoAddRequest, userInterfaceInfo);
        // 校验
        userInterfaceInfoService.validUserInterfaceInfo(userInterfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        userInterfaceInfo.setUserId(loginUser.getId());
        boolean result = userInterfaceInfoService.save(userInterfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newUserInterfaceInfoId = userInterfaceInfo.getId();
        return ResultUtils.success(newUserInterfaceInfoId);
    }


    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userInterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = userInterfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param userInterfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUserInterfaceInfo(@RequestBody UserInterfaceInfoUpdateRequest userInterfaceInfoUpdateRequest,
                                                         HttpServletRequest request) {
        if (userInterfaceInfoUpdateRequest == null || userInterfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoUpdateRequest, userInterfaceInfo);
        // 参数校验
        userInterfaceInfoService.validUserInterfaceInfo(userInterfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = userInterfaceInfoUpdateRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userInterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = userInterfaceInfoService.updateById(userInterfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<UserInterfaceInfo> getUserInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getById(id);
        return ResultUtils.success(userInterfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param userInterfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/list")
    public BaseResponse<List<UserInterfaceInfo>> listUserInterfaceInfo(@RequestBody UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest) {
        UserInterfaceInfo userInterfaceInfoQuery = new UserInterfaceInfo();
        if (userInterfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(userInterfaceInfoQueryRequest, userInterfaceInfoQuery);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(userInterfaceInfoQuery);
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoService.list(queryWrapper);
        return ResultUtils.success(userInterfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param userInterfaceInfoQueryRequest
     * @param request
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/list/page")
    public BaseResponse<Page<UserInterfaceInfo>> listUserInterfaceInfoByPage(@RequestBody UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest, HttpServletRequest request) {
        if (userInterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = userInterfaceInfoQueryRequest.getCurrent();
        long size = userInterfaceInfoQueryRequest.getPageSize();
        // 限制爬虫
        if (size > PageEnum.OnceLimit.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Page<UserInterfaceInfo> interfaceInfoPage = userInterfaceInfoService.page(new Page<>(current, size),
                userInterfaceInfoService.getQueryWrapper(userInterfaceInfoQueryRequest));
        return ResultUtils.success(interfaceInfoPage);
    }

    // endregion

    /**
     * 当前用户申请接口
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/add/my")
    public BaseResponse<UserInterfaceInfo> addLoginUserInterface(@RequestBody IdRequest idRequest,
                                                                 HttpServletRequest request) {
        // 判断参数
        if (idRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取当前用户
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        // 获取申请的接口
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(idRequest.getId());
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UserInterfaceInfo userInterfaceInfo = null;
        userInterfaceInfo = hasUserInterface(loginUser.getId(), idRequest.getId());

        // 判断接口和用户是否被禁止
        if (userInterfaceInfo != null && userInterfaceInfo.getStatus() == 1) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        if (userInterfaceInfo == null) {
            userInterfaceInfo = new UserInterfaceInfo();
            userInterfaceInfo.setUserId(loginUser.getId());
            userInterfaceInfo.setInterfaceInfoId(interfaceInfo.getId());
            userInterfaceInfo.setTotalNum(0);
            userInterfaceInfo.setLeftNum(100);
            userInterfaceInfo.setStatus(0);
            userInterfaceInfo.setCreateTime(new DateTime());
            userInterfaceInfo.setUpdateTime(new DateTime());
            userInterfaceInfo.setIsDelete(0);

            boolean result = userInterfaceInfoService.save(userInterfaceInfo);
            ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
            return ResultUtils.success(userInterfaceInfo);
        } else {
            UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("interfaceInfoId", idRequest.getId());
            updateWrapper.eq("userId", loginUser.getId());

            Integer upper = 100 - userInterfaceInfo.getLeftNum();

            updateWrapper.setSql("leftNum = leftNum + " + upper );

            boolean result = userInterfaceInfoService.update(updateWrapper);
            ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

            return ResultUtils.success(hasUserInterface(loginUser.getId(), idRequest.getId()));
        }
    }

    /**
     * 当前用户获取接口
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/get/my")
    public BaseResponse<UserInterfaceInfo> getLoginUserInterface(@RequestBody IdRequest idRequest,
                                                                 HttpServletRequest request) {
        // 判断参数
        if (idRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取当前用户
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        // 获取申请的接口
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(idRequest.getId());
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UserInterfaceInfo userInterfaceInfo = null;


        userInterfaceInfo = hasUserInterface(loginUser.getId(), idRequest.getId());

        // 判断接口和用户是否被禁止
        if (userInterfaceInfo != null && userInterfaceInfo.getStatus() == 1) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }

        if (userInterfaceInfo == null) {
            userInterfaceInfo = new UserInterfaceInfo();
            userInterfaceInfo.setId(0L);
            userInterfaceInfo.setUserId(0L);
            userInterfaceInfo.setInterfaceInfoId(0L);
            userInterfaceInfo.setTotalNum(0);
            userInterfaceInfo.setLeftNum(0);
            userInterfaceInfo.setStatus(1);
            userInterfaceInfo.setCreateTime(new DateTime());
            userInterfaceInfo.setUpdateTime(new DateTime());
            userInterfaceInfo.setIsDelete(0);
        }

        return ResultUtils.success(userInterfaceInfo);
    }

    private UserInterfaceInfo hasUserInterface(Long userId, Long interfaceInfoId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> getOneWrapper = new UpdateWrapper<>();
        getOneWrapper.eq("interfaceInfoId", interfaceInfoId);
        getOneWrapper.eq("userId", userId);

        return userInterfaceInfoService.getOne(getOneWrapper);
    }

}
