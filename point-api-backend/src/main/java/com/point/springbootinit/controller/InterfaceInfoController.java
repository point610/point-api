package com.point.springbootinit.controller;

import java.lang.reflect.Method;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.google.gson.Gson;
import com.point.apicommon.model.entity.InterfaceInfo;
import com.point.apicommon.model.entity.User;
import com.point.apicommon.model.entity.UserInterfaceInfo;
import com.point.apicommon.model.enums.InterfaceInfoStatusEnum;
import com.point.apicommon.model.enums.PageEnum;
import com.point.apicommon.model.vo.InterfaceInfoVO;
import com.point.apicommon.model.vo.UserVO;
import com.point.apisdk.client.PointApiClient;
import com.point.springbootinit.annotation.AuthCheck;
import com.point.springbootinit.common.*;
import com.point.springbootinit.exception.BusinessException;
import com.point.springbootinit.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.point.springbootinit.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.point.springbootinit.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.point.springbootinit.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.point.springbootinit.service.InterfaceInfoService;
import com.point.springbootinit.service.UserInterfaceInfoService;
import com.point.springbootinit.service.UserService;
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
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;


    // region 增删改查

    /**
     * 创建
     *
     * @param interfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        // 校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = interfaceInfoService.save(interfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long newInterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = interfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param interfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest,
                                                     HttpServletRequest request) {
        if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = interfaceInfoUpdateRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfoVO> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 获取接口信息和用户信息
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        Long userId = interfaceInfo.getUserId();
        User user = userService.getById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        // 封装
        InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
        BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
        interfaceInfoVO.setUserVO(userVO);

        return ResultUtils.success(interfaceInfoVO);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @PostMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        if (interfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResultUtils.success(interfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param interfaceInfoQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request) {
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        // 限制爬虫
        if (size > PageEnum.OnceLimit.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size),
                interfaceInfoService.getQueryWrapper(interfaceInfoQueryRequest));
        return ResultUtils.success(interfaceInfoPage);
    }

    // endregion


    /**
     * 下线
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/offline")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> offlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                      HttpServletRequest request) {
        if (idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = idRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 测试调用
     *
     * @param interfaceInfoInvokeRequest
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeRequest interfaceInfoInvokeRequest,
                                                    HttpServletRequest request) {
        if (interfaceInfoInvokeRequest == null || interfaceInfoInvokeRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = interfaceInfoInvokeRequest.getId();
        String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();

        // 判断是否存在or能够使用
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (oldInterfaceInfo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
        }
        User loginUser = userService.getLoginUser(request);

        // 判断用户和接口的关系是否从存在
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId", oldInterfaceInfo.getId());
        queryWrapper.eq("userId", loginUser.getId());
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(queryWrapper);
        if (userInterfaceInfo == null || userInterfaceInfo.getLeftNum() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请申请接口调用次数");
        }

        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        PointApiClient pointApiClient = new PointApiClient(accessKey, secretKey);

        // 调用哪个方法
        Object result = reflectionInvoking(pointApiClient, oldInterfaceInfo.getMethodName(), interfaceInfoInvokeRequest.getUserRequestParams());
        System.out.println("result");
        System.out.println(result);
        return ResultUtils.success(result);
    }

    /**
     * 使用反射调用sdk中的方法
     *
     * @param pointApiClient
     * @param interfaceInfoMethodName
     * @param userRequestParams
     * @return
     */
    private Object reflectionInvoking(PointApiClient pointApiClient, String interfaceInfoMethodName, String userRequestParams) {
        // 获取 PointApiClient 类的所有方法
        Method[] methods = PointApiClient.class.getDeclaredMethods();
        // 遍历所有方法
        Object result = null;
        for (Method method : methods) {
            // 获取方法名
            String methodName = method.getName();

            System.out.println("methodName:" + methodName);
            System.out.println("interfaceInfoMethodName:" + interfaceInfoMethodName);

            // 如果方法名为指定的方法名
            if (methodName.equals(interfaceInfoMethodName)) {
                // 输出该方法的调用对象
                System.out.println(method);

                // 使用反射调用该方法
                try {
                    // 设置方法可访问性
                    method.setAccessible(true);
                    // 调用方法
                    if (userRequestParams == null) {
                        result = method.invoke(pointApiClient);
                    } else {
                        result = method.invoke(pointApiClient, userRequestParams);
                    }
                    System.out.println("Method result: " + result);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
