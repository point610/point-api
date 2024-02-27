package com.point.apiinterface.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 无聊的话
 *
 * @TableName point_boring_talk
 */
@Data
public class PointBoringTalkVO implements Serializable {

    /**
     * 用户头像
     */
    private String value;

    private static final long serialVersionUID = 1L;
}