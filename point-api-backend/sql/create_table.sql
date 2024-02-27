# 数据库初始化
-- 切换库
create database if not exists point_api;

-- 用户表
use point_api;


alter database point_api character set utf8;

-- 创建库
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    userPassword varchar(512)                           not null comment '密码',
    userProfile  varchar(512)                           null comment '用户简介',
    `accessKey`  varchar(512)                           not null comment 'accessKey',
    `secretKey`  varchar(512)                           not null comment 'secretKey',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

drop table interface_info;

-- 接口信息
create table if not exists `interface_info`
(
    `id`              bigint                             not null auto_increment comment '主键' primary key,
    `name`            varchar(256)                       not null comment '名称',
    `description`     varchar(256)                       null comment '描述',
    `picture`         varchar(256)                       null comment '图片',
    `url`             varchar(512)                       not null comment '接口地址',
    `requestParams`   text                               null comment '请求参数',
    `responseParams`  text                               null comment '响应参数',
    `requestHeader`   text                               null comment '请求头',
    `responseHeader`  text                               null comment '响应头',
    `responseExample` text                               null comment '返回示例',
    `status`          int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
    `method`          varchar(256)                       not null comment '请求类型',
    `userId`          bigint                             not null comment '创建人',
    `createTime`      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime`      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete`        tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息' collate = utf8mb4_unicode_ci;

-- 用户调用接口关系表
create table if not exists `user_interface_info`
(
    `id`              bigint                             not null auto_increment comment '主键' primary key,
    `userId`          bigint                             not null comment '调用用户 id',
    `interfaceInfoId` bigint                             not null comment '接口 id',
    `totalNum`        int      default 0                 not null comment '总调用次数',
    `leftNum`         int      default 0                 not null comment '剩余调用次数',
    `status`          int      default 0                 not null comment '0-正常，1-禁用',
    `createTime`      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime`      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete`        tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞' collate = utf8mb4_unicode_ci;

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏' collate = utf8mb4_unicode_ci;


delete
from user_interface_info
where id > 8;

INSERT INTO point_api.interface_info (id, name, description, picture, url, requestParams, responseParams, requestHeader, responseHeader, responseExample, status, method, userId, createTime, updateTime, isDelete)
VALUES (1, '随机获取无聊的话', '随机获取无聊的话的接口', 'https://i.ibb.co/tc6NXvv/user-avatar-1761214571856367618-Pzq5-Oz-DR-Snipaste-2024-02-21-10-39-26-png317211128082173549.png', 'http://localhost:8090/api/boringtalk/random', null, '[
  {
    "paramName": "code",
    "type": "int",
    "description": "响应码"
  },
  {
    "paramName": "data.value",
    "type": "string",
    "description": "随机土味情话"
  },
  {
    "paramName": "message",
    "type": "string",
    "description": "返回信息描述"
  }
]', null, null, null, 0, 'post', 1761214571856367618, '2024-02-27 09:19:25', '2024-02-27 09:28:55', 0);



