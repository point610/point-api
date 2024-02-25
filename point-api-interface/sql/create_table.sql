# 数据库初始化
-- 切换库
create database if not exists point_api;

-- 用户表
use point_api;


alter database point_api character set utf8;

-- 创建库
create table if not exists point_boring_talk
(
    id         bigint auto_increment comment 'id' primary key,
    content    varchar(1024)                      null comment '用户头像',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '无聊的话' collate = utf8mb4_unicode_ci;


SELECT *
FROM point_boring_talk
ORDER BY RAND()
LIMIT 1;

INSERT INTO point_boring_talk (content, userId)
VALUES ('有时候我觉得我的生活就是一连串无聊的例行公事。', 1),
       ('这么做太无聊了，我真的需要点儿刺激。', 1),
       ('我无聊得要死，有没有什么好玩的东西做？', 1),
       ('每天都是一样的无聊，我都快疯了。', 1),
       ('我已经无聊到了极致，我都不知道该怎么办了。', 1),
       ('这部电影真是太无聊了，我简直无法忍受。', 1),
       ('我现在真的是无聊透顶，完全没有灵感。', 1),
       ('我简直无法忍受这种无聊的氛围。', 1),
       ('无聊的生活真是太让人沮丧了。', 1),
       ('我渴望着一些新鲜的刺激来打破这种无聊的状态。', 1),
       ('我实在无法忍受这种无聊的状态了。', 1),
       ('无聊的生活让我感到绝望。', 1),
       ('我真的需要一些有趣的事情来填补我的生活。', 1),
       ('我觉得我要被这种无聊淹没了。', 1),
       ('这种无聊的生活真是浪费时间。', 1),
       ('无聊使我的生活失去了意义。', 1),
       ('我对这种单调乏味的生活已经感到厌倦了。', 1),
       ('我需要一些刺激来激发我的生活。', 1),
       ('这么无聊的日子真是难熬。', 1),
       ('我已经对这种毫无意义的生活感到厌倦了。', 1),
       ('我渴望着一些新奇的体验来打破这种无聊。', 1),
       ('我实在受够了每天都过着一成不变的生活。', 1),
       ('我觉得我需要做些什么来摆脱这种无聊。', 1),
       ('这样的生活真是令人无法忍受。', 1),
       ('我希望能有一些刺激的事情发生。', 1),
       ('我真的需要一些新鲜的活动来改变现状。', 1),
       ('这种无聊让我感到很沮丧。', 1),
       ('我真的很想逃离这种无聊的状态。', 1),
       ('我觉得我生活中缺少了乐趣。', 1),
       ('我渴望着有一些令人兴奋的事情发生。', 1);

