# 文章表
CREATE TABLE `article` (
                           `id` varchar(32) NOT NULL COMMENT '主键',
                           `title` varchar(50) NOT NULL COMMENT '标题',
                           `summary` varchar(256) DEFAULT NULL COMMENT '摘要',
                           `content` text DEFAULT NULL COMMENT '内容',
                           `author` varchar(20) DEFAULT NULL COMMENT '作者',
                           `word_count` int(5) DEFAULT NULL COMMENT '字数',
                           `creation_type` varchar(5) DEFAULT NULL COMMENT '创作类型 原创 | 转载',
                           `source` varchar(150) DEFAULT NULL COMMENT '转载的来源地址',
                           `published` varchar(1) DEFAULT 'N' COMMENT '是否发布 Y:是 N:否',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
                           `deleted` varchar(1) DEFAULT 'N' COMMENT '删除状态，Y：删除 N：未删除',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

# 分类表
CREATE TABLE `category` (
                            `id` varchar(32) NOT NULL COMMENT '主键',
                            `name` varchar(50) NOT NULL COMMENT '标签名',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
                            `deleted` varchar(1) DEFAULT 'N' COMMENT '删除状态，Y：删除 N：未删除',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

# 分类关系表
CREATE TABLE `category_relationships` (
                                          `id` varchar(32) NOT NULL COMMENT '主键',
                                          `article_id` varchar(32) DEFAULT NULL COMMENT '文章id',
                                          `category_id` varchar(32) DEFAULT NULL COMMENT '分类id',
                                          `sort_order` int(2) DEFAULT 0 COMMENT '排序',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

# 标签关系表
CREATE TABLE `tag` (
                       `id` varchar(32) NOT NULL COMMENT '主键',
                       `name` varchar(50) NOT NULL COMMENT '标签名',
                       `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                       `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
                       `deleted` varchar(1) DEFAULT 'N' COMMENT '删除状态，Y：删除 N：未删除',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

# 标签关系表
CREATE TABLE `tag_relationships` (
                                     `id` varchar(32) NOT NULL COMMENT '主键',
                                     `article_id` varchar(32) DEFAULT NULL COMMENT '文章id',
                                     `tag_id` varchar(32) DEFAULT NULL COMMENT '标签id',
                                     `sort_order` int(2) DEFAULT 0 COMMENT '排序',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

# 文件表
CREATE TABLE `file` (
                        `id` varchar(32) NOT NULL COMMENT '主键',
                        `path` varchar(256) NOT NULL COMMENT '路径',
                        `name` varchar(32) NOT NULL COMMENT '文件名',
                        `suffix` varchar(8) DEFAULT NULL COMMENT '后缀',
                        `full_name` varchar(296) DEFAULT NULL COMMENT '完整文件名',
                        `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
                        `deleted` varchar(1) DEFAULT 'N' COMMENT '删除状态，Y：删除 N：未删除',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;
# 站点信息
CREATE TABLE `site` (
                        `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                        `motto` varchar(255) DEFAULT NULL COMMENT '签名',
                        `github` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

# 用户
CREATE TABLE `user` (
                        `id` varchar(32) NOT NULL,
                        `name` varchar(32) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `token` varchar(255) DEFAULT NULL,
                        `expired` varchar(20) DEFAULT NULL COMMENT 'token过期时间 YYYY-MM-DD hh:mm:ss',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;