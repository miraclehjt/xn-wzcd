
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_01','还款中','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_02','提交结算单','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_03','财务审核','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_04','财务审核不通过','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_05','出纳打款','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_06','解除抵押申请','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_07','风控内勤审核','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_08','风控内勤审核不通过','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_09','风控经理审核','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_10','风控经理审核不通过','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_11','打印岗打印','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_12','寄送银行材料','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_13','驻行人员提交材料','020',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('020_14','抵押完成','020',NULL);

INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_01','020_02',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_02','020_03',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_03','020_05','020_04',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_04','020_03',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_05','020_06',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_06','020_07',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_07','020_09','020_08',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_08','020_06',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_09','020_11','020_10',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_10','020_06',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_11','020_12',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_12','020_13',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('020','020_13','020_14',NULL,NULL,NULL);

INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_01','申请拖车','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_02','风控经理审核','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_03','风控经理审核不通过','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_04','分公司总经理审核','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_05','分公司总经理审核不通过','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_06','风控总监审核','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_07','风控总监审核不通过','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_08','财务经理审核','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_09','财务经理审核不通过','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_10','确认放款','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_11','录入拖车结果','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_12','已录入待处理','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_13','已赎回','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_14','出售','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_15','司法诉讼','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_16','诉讼跟进','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_17','诉讼结果录入','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_18','财务确认收款','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_19','坏账','021',NULL);
INSERT INTO `tsys_node` (`code`,`name`,`type`,`remark`) VALUES ('021_20','司法诉讼完成','021',NULL);

INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_01','021_02',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_02','021_04','021_03',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_03','021_02',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_04','021_06','021_05',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_05','021_04',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_06','021_08','021_07',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_07','021_06',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_08','021_10','021_09',NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_09','021_08',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_10','021_11',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_11','021_12',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_15','021_16',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_16','021_17',NULL,NULL,NULL);
INSERT INTO `tsys_node_flow` (`type`,`current_node`,`next_node`,`back_node`,`file_list`,`remark`) VALUES ('021','021_17','021_18',NULL,NULL,NULL);

ALTER TABLE `dev_xn_wzcd`.`tdq_budget_order` 
ADD COLUMN `type` VARCHAR(4) NULL COMMENT '预算单类型 (1正常单2外单)' AFTER `insurance_note`,
ADD COLUMN `out_car_dealer_name` VARCHAR(255) NULL COMMENT '汽车经销商名称（外单）' AFTER `car_dealer_code`;

ALTER TABLE `dev_xn_wzcd`.`tdqt_repoint_detail` 
ADD COLUMN `out_car_dealer_name` VARCHAR(255) NULL COMMENT '汽车经销商名称(外单)' AFTER `car_dealer_code`;
ADD COLUMN `out_account_no` VARCHAR(45) NULL COMMENT '收款账号（外单手动填写的汽车经销商收款账号 ）' AFTER `out_account_no`;
ADD COLUMN `open_bank_name` VARCHAR(255) NULL COMMENT '开户行名称' AFTER `out_account_no`;

