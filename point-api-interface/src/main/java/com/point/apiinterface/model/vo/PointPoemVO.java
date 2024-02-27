package com.point.apiinterface.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 无聊的话
 *
 * @TableName point_boring_talk
 */
@Data
public class PointPoemVO implements Serializable {

    /**
     * 内容
     */
    private String value;

    private static final long serialVersionUID = 1L;
}