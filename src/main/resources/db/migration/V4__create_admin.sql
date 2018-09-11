INSERT INTO users(
   id,created_by, date_created, date_modified, modified_by, email, is_activated, name, password, picture)
   VALUES (1000,-1, '2018-09-11 00:00:00', NULL, -1, 'admin', true, 'admin', '$2a$10$xUMFlX7sNwnd7blvsng2P.MENMn4qlLUQBFENO6MR.ZgT7pEj.D6S', null);

INSERT INTO user_roles(
   created_by, date_created, date_modified, modified_by, role_id, user_id)
   VALUES (-1, '2018-09-11 00:00:00', NULL, -1, 1, 1000);
