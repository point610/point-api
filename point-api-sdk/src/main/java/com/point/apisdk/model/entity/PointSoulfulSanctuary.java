package com.point.apisdk.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 心灵鸡汤
 *
 * @TableName point_soulful_sanctuary
 */
@Data
public class PointSoulfulSanctuary implements Serializable {
    /**
     * 内容
     */
    private String value;
}