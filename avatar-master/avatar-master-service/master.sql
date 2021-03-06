INSERT INTO `countries` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `country_code`) 
VALUES ('admin', now(), b'000000', 'admin', NOW(), '0', 'India', 'IN'); 

INSERT INTO `states` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `state_code`, `country_id`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'TamilNadu', 'TN', '1'); 

INSERT INTO `districts` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `district_code`, `state_id`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'Pudukkottai', 'PDK', '1'); 

INSERT INTO `taluks` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `taluk_code`, `district_id`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'Alangudi', 'ALA', '1'); 

INSERT INTO `villages` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `ta`, `village_code`, `taluk_id`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'Mangadu', 'மாங்காடு', 'MAN', '1'); 

INSERT INTO `languages` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `language_code`, `language_name`) 
VALUES ('admin', now(), b'000000', 'admin', NOW(), '0', 'en', 'English');

INSERT INTO `languages` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `language_code`, `language_name`) 
VALUES ('admin', now(), b'000000', 'admin', NOW(), '0', 'ta', 'Tamil'); 

INSERT INTO `genders` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `ta`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'Male', 'ஆண்'); 

INSERT INTO `genders` (`created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `version`, `en`, `ta`) 
VALUES ('admin', NOW(), b'000000', 'admin', NOW(), '0', 'Female', 'பெண்');
