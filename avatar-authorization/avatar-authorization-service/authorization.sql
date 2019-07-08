
INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'ADMIN'); 

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'SELLER');

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'SELLER_AGENT');

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'SELLER_TRUCK_DRIVER');

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'BUYER');

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'BUYER_AGENT');

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'BUYER_TRUCK_DRIVER');

INSERT INTO `roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'WAREHOUSE_QC');

INSERT INTO `users` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `dob`, 
`first_name`, `last_name`, `mobile_number`, `password`, `suspended`, `user_name`, `gender_id`, `language_id`, `village_id`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', NOW(), 
'ArunPrakash', 'Balasundaram', '9566122460', '$2a$10$M9G0NVMMldzdi/66vKpdBuPSvR7d3RByF8o1ZL4ivY8lhMRY7H4Ly', b'000000', 'admin', '1', '1', '1');

INSERT INTO `user_roles` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `role_id`, `user_id`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', '1', '1'); 

INSERT INTO `users_asset_types` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `asset_type_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'PHOTO'); 

INSERT INTO `users_asset_types` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `asset_type_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'VOTER_ID');

INSERT INTO `users_asset_types` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `asset_type_name`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'BANK_BOOK'); 
