CREATE TABLE pf_user
(
    id       CHAR(20) NOT NULL PRIMARY KEY,
    username VARCHAR(255) COMMENT '登录名',
    password VARCHAR(255) COMMENT '密码'
);
CREATE TABLE pf_test
(
    id         CHAR(20)     NOT NULL PRIMARY KEY,
    created_dt DATETIME COMMENT '创建时间',
    created_by VARCHAR(255) NOT NULL default '' COMMENT '创建人',
    updated_dt DATETIME COMMENT '更新时间',
    updated_by VARCHAR(255) NOT NULL default '' COMMENT '更新人',
    name       VARCHAR(255) NOT NULL default '' COMMENT '名称',
    age        INT(10)               DEFAULT NULL COMMENT '年龄',
    photo      VARCHAR(300)          DEFAULT '' COMMENT '头像',
    marry      BIT(1)       NOT NULL default 0 COMMENT '是否结婚',
    birthday   DATE COMMENT '生日',
    status     VARCHAR(255) NOT NULL default 'VALID' COMMENT '状态'
);
