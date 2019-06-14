INSERT INTO `notification_status` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `status`) VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'NEW');
INSERT INTO `notification_status` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `status`) VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'PROCESSING'); 
INSERT INTO `notification_status` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `status`) VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'COMPLETED'); 

INSERT INTO `notification_type` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `type`) VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'SELLER_PRICE_NOTIFICATION');
