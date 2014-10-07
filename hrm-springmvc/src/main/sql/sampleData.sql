
/* CONTACTS */
INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (1, 'Pist seksisi', 'Simge', 'Sarigul', 0);

INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (2, 'Adrasanda gezer', 'Simge ', 'Tatilci', 0);

INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (3, 'Ailemizin Baytari', 'Cansu ', 'COMAK', 0);

INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (4, 'Ev seksisi', 'Hasan ', 'COMAK', 0);

INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (5, 'Rencber the emekli', 'Mustafa ', 'COMAK', 0);

INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (6, 'Rencber the emekli 2nd', 'Saadet ', 'COMAK', 0);

INSERT INTO `hrm`.`contact` (`id`, `description`, `first_name`, `last_name`, `version`) VALUES (7, 'The lovely mother', 'Birsen ', 'Hanim', 0);



/*CONTACT PHONE DETAILS*/
INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (1, '5052733333', 'MOBILE', 0, 1);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (2, '5052733334', 'HOME', 0, 1);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (3, '5335333333', 'MOBILE', 0, 2);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (4, '5335323232', 'HOME', 0, 2);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (5, '5335333333', 'MOBILE', 0, 3);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (6, '5335323232', 'HOME', 0, 3);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (7, '5335333333', 'MOBILE', 0, 4);

INSERT INTO `hrm`.`contact_phone_detail` (`id`, `phone_number`, `phone_type`, `version`, `contact_id`) VALUES (8, '5335323232', 'HOME', 0, 4);


/*HOBBIES*/
INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('dancing', 'Dance', 'Salsa dancing');

INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('climbing', 'Climbing', 'Rock climbing');

INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('choir', 'Choir', 'Singing in a choir');

INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('violin', 'Violin', 'Violin playing');

INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('parasailing', 'Para Sailing', 'para sailing');

INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('reading', 'Reading', 'Reading lit..... ');

INSERT INTO `hrm`.`hobby` (`hobby_id`, `name`, `description`) VALUES ('photography', 'Photography', 'Amateur photography');



/*HOBBY CONTACT MAP*/
INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('dancing', 1);

INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('photography', 1);

INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('dancing', 3);

INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('dancing', 2);

INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('photography', 3);

INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('climbing', 3);

INSERT INTO `hrm`.`contact_hobby_detail` (`hobby_id`, `contact_id`) VALUES ('climbing', 4);


/**/
