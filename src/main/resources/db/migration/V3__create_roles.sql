INSERT INTO role(
	created_by, date_created, date_modified, modified_by, name)
	VALUES ( -1, '2018-08-23 00:00:00', NULL, -1, 'ADMIN');

INSERT INTO role(
	created_by, date_created, date_modified, modified_by, name)
	VALUES ( -1, '2018-08-23 00:00:00', NULL, -1, 'LISTENER');

INSERT INTO role(
	created_by, date_created, date_modified, modified_by, name)
	VALUES ( -1, '2018-08-23 00:00:00', NULL, -1, 'ARTIST');

INSERT INTO role(
	created_by, date_created, date_modified, modified_by, name)
	VALUES ( -1, '2018-08-23 00:00:00', NULL, -1, 'RECORDLABEL');

ALTER TABLE public.users
    ALTER COLUMN picture DROP NOT NULL;
