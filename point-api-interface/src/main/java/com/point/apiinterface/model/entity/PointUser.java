package com.point.apiinterface.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试用户对象
 *
 */
@Data
public class PointUser implements Serializable {


    /**
     * 用户头像
     */
    private String name;

    private static final long serialVersionUID = 1L;
}