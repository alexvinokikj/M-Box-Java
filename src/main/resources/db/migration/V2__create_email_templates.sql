INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('Dear<Your Record Label has invited you to join MBox.<M Box is a web platform where you can customize a page with all your music.<Click here to get started<Regards,<M Box', -1,'2018-08-20 00:00:00', NULL, -1, 'artistSignUpMail', 'MBox account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('Dear <You have registered an account on M Box.<Click here to verify your account.<Regards,<M Box', -1,'2018-08-20 00:00:00', NULL, -1, 'verificationMail', 'MBox Verification Mail');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('Dear <Your M Box account has been terminated.<For more information you can contact us at our email adress.<Regards,<MBox', -1,'2018-08-20 00:00:00', NULL, -1, 'deleteRecordLabelMail', 'MBox Account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('Dear<Your Record Label has been removed.<Your page will remain as it is, but you will not be able to post new songs until you join a record label that cooperates with us.<Regards,<M Box', -1,'2018-08-20 00:00:00', NULL, -1, 'deleteArtistMail', 'MBox Account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('Dear<Your M Box account has benn created!<Click here to set your password<Regards,<M Box', -1,'2018-08-20 00:00:00', NULL, -1, 'RecordLabelSignUp', 'MBox Account');
