/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-01-11 17:26:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `bookinfo`;
CREATE TABLE `bookinfo` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `bookconcern` varchar(255) DEFAULT NULL COMMENT '出版社',
  `publish_date` datetime DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `amount` int(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookinfo
-- ----------------------------
INSERT INTO `bookinfo` VALUES ('1', 'Java', '小红', '电子工业出版社', '2016-05-24 00:00:00', '99.00', '100', null);
INSERT INTO `bookinfo` VALUES ('2', 'C++', '小红', '电子工业出版社', '2016-05-24 00:00:00', '99.00', '100', null);
INSERT INTO `bookinfo` VALUES ('3', 'PHP', '小红', '电子工业出版社', '2016-05-24 00:00:00', '99.00', '99', null);

-- ----------------------------
-- Table structure for guestbook
-- ----------------------------
DROP TABLE IF EXISTS `guestbook`;
CREATE TABLE `guestbook` (
  `gis_id` int(11) NOT NULL AUTO_INCREMENT,
  `gst_user` varchar(50) NOT NULL,
  `gst_title` varchar(100) NOT NULL,
  `gst_content` text,
  `gst_time` datetime NOT NULL,
  `gst_ip` varchar(20) NOT NULL,
  PRIMARY KEY (`gis_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of guestbook
-- ----------------------------
INSERT INTO `guestbook` VALUES ('2', '王二丫', '鹅鹅鹅饿饿', '鹅鹅鹅饿饿，曲项向天歌，白毛浮绿水，红掌拨清波', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('3', '李狗蛋', '你我的新时代，全面小康一个都不能少', '2018年，改革开放40周年；2019年，新中国成立70周年；2020年，全面建成小康社会；2021年，中国共产党成立100周年……习近平总书记强调，这些重要的时间节点，是我们工作的坐标，更标志着中国开启圆梦复兴的新征程。\r\n\r\nAfter 40 years of reform, opening up and development, China is standing in a \"new era.\" The ultimate goal of this period is the \"great rejuvenation of the nation,\" a blueprint set forth by Chinese President Xi Jinping, who plans to push his country forwards in a \"new era\" during which \"no one must be left behind.\"\r\n\r\nIn Xi\'s own words\r\n\r\n“中国特色社会主义进入了新时代。\r\n\r\nSocialism with Chinese characteristics has entered a new era.”', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('4', '李狗剩', '习近平集体会见北欧和波罗的海国家议长', '1月10日，国家主席习近平在北京人民大会堂集体会见北欧和波罗的海国家议会领导人。芬兰议长洛赫拉、挪威议长托马森、冰岛议长西格富松、爱沙尼亚议长内斯托尔、拉脱维亚议长穆尔涅采、立陶宛议长普兰茨凯蒂斯、瑞典第一副议长芬内等参加会见。新华社记者 王晔 摄\r\n\r\n　　央视网消息（新闻联播）：国家主席习近平10日在人民大会堂集体会见北欧和波罗的海国家议会领导人。芬兰议长洛赫拉、挪威议长托马森、冰岛议长西格富松、爱沙尼亚议长内斯托尔、拉脱维亚议长穆尔涅采、立陶宛议长普兰茨凯蒂斯、瑞典第一副议长芬尼等参加会见。\r\n\r\n　　习近平说，此次各国议会领导人联合访华，是北欧和波罗的海国家集体同中国开展高层交往的一种新形式，标志着双方政治互信和各领域交流合作进入快速发展的新时代，我对此表示欢迎。\r\n\r\n　　习近平指出，当前，中国同北欧和波罗的海国家关系保持着良好发展势头，双方政治互信稳步提升，务实合作成果丰硕，人文交流日益深入，民意基础更加坚实。中国同北欧和波罗的海国家发展健康、稳定、可持续的双边关系，推进互利共赢的双边和区域合作，不仅符合我们各自国家的利益，也有利于推进中欧全面战略伙伴关系向前发展，契合和平、发展、合作、共赢的时代潮流。双方要始终以相互尊重、平等相待、包容互鉴为原则，尊重彼此核心利益和重大关切，从战略高度和长远角度牢牢把握住双边关系的正确发展方向。要继续保持高层交往势头，加强交流，增进了解，求同化异，扩大共识。要对接各自发展战略，拓展务实合作领域和渠道，特别是加强在“一带一路”倡议框架下的合作，共享亚欧大陆互联互通带来的发展红利。\r\n\r\n　　习近平介绍了中国经济社会发展情况和和平外交政策。他说，中共十九大提出，中国将推动构建新型国际关系，推动构建人类命运共同体。这是中国特色社会主义理念的应有之义，是新时代中国外交追求的目标，也是世界各国共同努力的方向。中国将继续发挥负责任大国作用，积极参与全球治理体系改革和建设，推动全球治理体系朝着更加公正合理的方向发展，不断贡献中国智慧和力量。\r\n\r\n　　在座的各国议会领导人祝贺中共十九大成功召开，对中共十九大提出的以人民为中心的各项目标表示钦佩，祝愿这些目标顺利实现。中国在经济发展、民生改善、减贫脱贫、环境保护等方面取得的成就是历史性的。他们表示，北欧和波罗的海国家珍视同中国的友谊，愿进一步发展同中国的友好合作关系。\r\n\r\n　　中共中央政治局委员、全国人大常委会副委员长兼秘书长王晨参加会见。', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('5', '王大爷', '2018网络大过年 | “瑞犬迎新 幸福中华” 我们来了！', '1月9日下午，北京市互联网信息办公室、首都互联网协会举办了“文化自信大家谈—戌年说狗”专题活动，著名民俗专家、中国民间文艺家协会文联主席王作楫老师通过视频直播对狗年民俗进行了讲解，引发大批网民围观，主题为“瑞犬迎新 幸福中华”的2018网络大过年也正式拉开帷幕。\r\n\r\n“2018年为农历戊戌年，戊戌年的地支属狗，五行属木，是平地木。”王作楫老师声情并茂地将狗年民俗娓娓道来，现场参加活动的各网站代表听得津津有味，对民俗文化的了解又深了一层，对今年的主题设计又有了更多的想法。\r\n\r\n从2010年至今，在北京的互联网管理部门、行业组织指导下，由网信企业、广大网民共同参与的网络大过年活动，也走进了第八个年头。八年来，活动参与的网站越来越多，活动形态越来越丰富，覆盖的人群越来越广泛，点击量也节节攀升，多年参与支持活动的王作楫老师表示，这项活动“聚人气，结人缘、促人和”，而且坚持举办了很多年，是传承民俗文化的难得佳作。', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('6', '灰化肥会挥发', '广州GDP预计突破2万亿 北上广深入主“2万亿俱乐部”', '1月9日下午，北京市互联网信息办公室、首都互联网协会举办了“文化自信大家谈—戌年说狗”专题活动，著名民俗专家、中国民间文艺家协会文联主席王作楫老师通过视频直播对狗年民俗进行了讲解，引发大批网民围观，主题为“瑞犬迎新 幸福中华”的2018网络大过年也正式拉开帷幕。\r\n\r\n“2018年为农历戊戌年，戊戌年的地支属狗，五行属木，是平地木。”王作楫老师声情并茂地将狗年民俗娓娓道来，现场参加活动的各网站代表听得津津有味，对民俗文化的了解又深了一层，对今年的主题设计又有了更多的想法。', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('7', '啊啊啊啊啊', '啊啊啊啊啊啊', '﻿ε≡٩(๑>₃<)۶ 一心向学_φ_(．．) 写作业(。＿ 。） ✎＿学习计划走起*✧⁺˚⁺ପ(๑･ω･)੭ु⁾⁾ 好好学习天天向上(:3_ヽ)_写作业…(:ｪ)|￣|＿上辅导班(っ•̀ω•́)っ✎⁾⁾ 我爱学习…φ(๑˃∀˂๑)♪ 学习是我的全部٩(*Ӧ)و写的全对(๑╹ヮ╹๑)ﾉ Studying makes me happy(:3[▓▓]快醒醒开学了', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('8', '呵呵呵', '外交部：日本两艘舰艇在钓鱼岛海域活动，中国海军全程跟踪监控', '&lt;div&nbsp;style=&#34;background-color:red&#34;&gt;<br>【环球时报-环球网报道&nbsp;记者&nbsp;张鑫】据日本时事通讯社报道，日本政府11日称，中国海军舰艇及潜艇当天上午进入钓鱼岛赤尾屿12海里毗连区。日本首相安倍晋三已向各省厅下达指令，要求全力做好警戒监视工作。日本还表示可能通过外交渠道向中国表达“严重抗议”。<br>&nbsp;<br>　　针对这一事件，中国外交部发言人陆慷在11日例行记者会上回答记者提问时表示，“根据中方掌握的情况，今天上午日本海上自卫队两艘舰艇先后进入赤尾屿东北一侧毗连区活动。中国海军对日方活动实施了全程跟踪监控。目前日方舰艇已经离开了有关毗连区”。<br>&nbsp;<br>　　陆慷表示，钓鱼岛及其附属岛屿是中国的固有领土。中国对钓鱼岛的主权拥有充分的历史和法理依据。<br>&lt;/div&gt;', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('9', '呵呵呵', '呵呵呵呵', '我靠，fuck,&nbsp;他妈的，&nbsp;&nbsp;嘿嘿&nbsp;苦笑&nbsp;呲牙&nbsp;强颜欢笑&nbsp;憨笑笑哭&nbsp;哭笑&nbsp;笑出眼泪&nbsp;破涕为笑&nbsp;笑死&nbsp;笑尿&nbsp;笑cry', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('10', '1111', '111111', '我靠，fuck,&nbsp;他妈的，&nbsp;&nbsp;嘿嘿&nbsp;苦笑&nbsp;呲牙&nbsp;强颜欢笑&nbsp;憨笑笑哭&nbsp;哭笑&nbsp;笑出眼泪&nbsp;破涕为笑&nbsp;笑死&nbsp;笑尿&nbsp;笑cry', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `guestbook` VALUES ('11', '1111', '111111', '我靠，fuck,&nbsp;他妈的，&nbsp;&nbsp;嘿嘿&nbsp;苦笑&nbsp;呲牙&nbsp;强颜欢笑&nbsp;憨笑笑哭&nbsp;哭笑&nbsp;笑出眼泪&nbsp;破涕为笑&nbsp;笑死&nbsp;笑尿&nbsp;笑cry', '2018-01-11 00:00:00', '0:0:0:0:0:0:0:1');
