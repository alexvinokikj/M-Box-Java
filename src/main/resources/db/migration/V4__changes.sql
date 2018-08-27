INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('Dear < You have requested a password change on M Box. <LINK< you did not request this change, please contact support in the About tab on M Box. <Regards, <M Box', -1,'2018-08-27 00:00:00', NULL, -1, 'ForgotPassword', 'MBox Account');

ALTER TABLE users ALTER COLUMN password TYPE varchar(255);