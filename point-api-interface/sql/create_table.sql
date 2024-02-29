# 数据库初始化
-- 切换库
create database if not exists point_api;

-- 用户表
use point_api;


alter database point_api character set utf8;

drop table point_boring_talk;

-- 创建库
create table if not exists point_boring_talk
(
    id         bigint auto_increment comment 'id' primary key,
    value      varchar(1024)                      null comment '内容',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '无聊的话' collate = utf8mb4_unicode_ci;


INSERT INTO point_boring_talk (value, userId)
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

-- 创建库
create table if not exists point_soulful_sanctuary
(
    id         bigint auto_increment comment 'id' primary key,
    value      varchar(1024)                      null comment '内容',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '心灵鸡汤' collate = utf8mb4_unicode_ci;

INSERT INTO point_soulful_sanctuary (value, userId)
VALUES ('在沉寂中寻找力量，正如在黑暗中寻找光明。', 1),
       ('相信自己，你已经走过了很远的路。', 1),
       ('生活不会一直如此，但它会继续。', 1),
       ('做你觉得最有意义的事情，永远不会太迟。', 1),
       ('世界上最强大的力量就是持续不懈。', 1),
       ('失败是成功之母。', 1),
       ('即使是最小的行动也比最大的意图强。', 1),
       ('永远不要放弃，因为生活从来没有放弃过你。', 1),
       ('微笑，因为你值得拥有一切美好。', 1),
       ('成功的人从不抱怨，抱怨的人从不成功。', 1),
       ('每一个结束都是新的开始。', 1),
       ('永远记住，你比自己想象的更勇敢，更坚强，更聪明。', 1),
       ('用心生活，你会发现生活中的美好。', 1),
       ('坚持不懈是成功的关键。', 1),
       ('相信自己，你比自己想象的要强大。', 1),
       ('生命中最美好的事情之一是，你永远可以重新开始。', 1),
       ('保持微笑，因为生活会给你很多理由去哭泣。', 1),
       ('你的生活是你创造的艺术品，用心创作。', 1),
       ('永远不要放弃，因为奇迹发生在最不可能的时刻。', 1),
       ('坚持就是胜利。', 1),
       ('成功不是一蹴而就的，它需要时间、坚持和毅力。', 1),
       ('忍耐和坚持总会得到报酬。', 1),
       ('每一次挫折都是一次学习的机会。', 1),
       ('心怀感激，生活将会更美好。', 1),
       ('相信自己的内在力量，它比你所想象的要强大。', 1),
       ('生命中最重要的事情不是发生了什么，而是我们如何应对它。', 1),
       ('过去是过去，未来尚未到来，现在才是生活。', 1),
       ('每一个愿望都是一个希望的种子。', 1),
       ('相信自己，你已经走过了很远的路。', 1),
       ('承认自己的错误，然后继续前进。', 1);
-- 创建库
create table if not exists point_poem
(
    id         bigint auto_increment comment 'id' primary key,
    value      varchar(1024)                      null comment '内容',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '诗词' collate = utf8mb4_unicode_ci;

INSERT INTO point_poem (value, userId)
VALUES ('白日依山尽，黄河入海流。', 1),
       ('欲穷千里目，更上一层楼。', 1),
       ('明月几时有？把酒问青天。', 1),
       ('不知天上宫阙，今夕是何年。', 1),
       ('我欲乘风归去，又恐琼楼玉宇，高处不胜寒。', 1),
       ('起舞弄清影，何似在人间。', 1),
       ('转朱阁，低绮户，照无眠。', 1),
       ('不应有恨，何事长向别时圆？', 1),
       ('人有悲欢离合，月有阴晴圆缺，此事古难全。', 1),
       ('但愿人长久，千里共婵娟。', 1),
       ('春江潮水连海平，海上明月共潮生。', 1),
       ('春眠不觉晓，处处闻啼鸟。', 1),
       ('夜来风雨声，花落知多少。', 1),
       ('朝辞白帝彩云间，千里江陵一日还。', 1),
       ('青山横北郭，白水绕东城。', 1),
       ('江山如此多娇，引无数英雄竞折腰。', 1),
       ('沉舟侧畔千帆过，病树前头万木春。', 1),
       ('大江东去，浪淘尽，千古风流人物。', 1),
       ('风萧萧兮易水寒，壮士一去兮不复还。', 1),
       ('静夜思床前明月光，疑是地上霜。举头望明月，低头思故乡。', 1),
       ('人闲桂花落，夜静春山空。月出惊山鸟，时鸣春涧中。', 1),
       ('白日依山尽，黄河入海流。', 1),
       ('明月几时有？把酒问青天。', 1),
       ('春江潮水连海平，海上明月共潮生。', 1),
       ('春眠不觉晓，处处闻啼鸟。', 1),
       ('夜来风雨声，花落知多少。', 1),
       ('朝辞白帝彩云间，千里江陵一日还。', 1),
       ('青山横北郭，白水绕东城。', 1),
       ('江山如此多娇，引无数英雄竞折腰。', 1),
       ('沉舟侧畔千帆过，病树前头万木春。', 1),
       ('大江东去，浪淘尽，千古风流人物。', 1),
       ('风萧萧兮易水寒，壮士一去兮不复还。', 1),
       ('白日依山尽，黄河入海流。', 1);

