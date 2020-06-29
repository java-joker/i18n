--h2
DROP TABLE IF EXISTS `i18n`;
CREATE TABLE `i18n` (
`id` int(11) NOT NULL auto_increment,
`ref_id` int(11) NOT NULL,
`ref_type` varchar(4) NOT NULL,
`language_type` varchar(10) NOT NULL,
`translate_text` text NOT NULL,
`created_at` timestamp NOT NULL default CURRENT_TIMESTAMP,
`updated_at` timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE KEY `uniq_ref_id_type_lang` ( `ref_id`, `ref_type`, `language_type` )
)

--mysql
-- DROP TABLE IF EXISTS `i18n`;
-- CREATE TABLE `i18n` (
-- `id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
-- `ref_id` INT ( 11 ) NOT NULL COMMENT '关联id',
-- `ref_type` VARCHAR ( 4 ) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号，不能类型必须唯一',
-- `language_type` VARCHAR ( 10 ) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言类型，国际通用列表:zh-CN(中文)，zh-HK(香港繁体)，zh-TW(台湾繁体)，en-US(英文)，ja-JP(日语)',
-- `translate_text` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '翻译内容',
-- `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
-- `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
-- PRIMARY KEY ( `id` ) USING BTREE,
-- UNIQUE KEY `uniq_ref_id_type_lang` ( `ref_id`, `ref_type`, `language_type` )
-- ) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '国际化翻译';