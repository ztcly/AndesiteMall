CREATE TABLE `andesitemall`.`tbl_user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` VARCHAR(20) NULL COMMENT '用户登录名',
  `password` VARCHAR(45) NULL COMMENT '用户密码',
  `nickname` VARCHAR(45) NULL COMMENT '用户昵称',
  `avater` VARCHAR(45) NULL COMMENT '用户头像',
  `phoneNumber` CHAR(11) NULL,
  `email` VARCHAR(45) NULL COMMENT '用户电子邮箱',
  `permissionLevel` VARCHAR(45) NULL COMMENT '用户权限等级',
  `isDeleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否被删除（逻辑删除）\n0为未删除，1为已删除',
  `addTime` TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加记录时间戳',
  `updateTime` TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新记录时间戳',
  PRIMARY KEY (`id`))
COMMENT = '用户表';

CREATE TABLE `andesitemall`.`tbl_userinfo` (
  `id` INT NOT NULL COMMENT '用户信息ID 外键来源user表',
  `birthday` DATETIME NULL COMMENT '用户生日',
  `lastLoginTime` DATETIME NULL COMMENT '上次登录时间',
  `lastLoginIP` VARCHAR(15) NULL COMMENT '上次登录IP',
  `gender` VARCHAR(20) NULL COMMENT '性别',
  `wxid` VARCHAR(45) NULL,
  `isDeleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `addTime` TIMESTAMP(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  `updateTime` TIMESTAMP(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  INDEX `id_idx` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`id`)
    REFERENCES `andesitemall`.`tbl_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = '用户账户其它信息';
