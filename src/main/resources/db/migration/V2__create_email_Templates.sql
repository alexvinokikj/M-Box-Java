INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('<h3>Dear [NAME] ,</h3> <h3>Your Record Label has invited you to join MBox.</h3> <h3>M Box is a web platform where you can customize a page with all your music.</h3> <h3> <a href="[APPURL]"<Click here to get started</a> <h3> After setting up your password you can login with [EMAILADRESS]</h3> </h3> <br> <h3>Regards, </h3>  <h3>M Box</h3>', -1,'2018-08-27 00:00:00', NULL, -1, 'artistSignUpMail', 'MBox account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('<h3>Dear [NAME],</h3> <h3>You have registered an account on M Box.</h3> <h3> <a href="">Click here to verify your account.</a> </h3><br> <h3>Regards, </h3>  <h3>M Box</h3>', -1,'2018-08-27 00:00:00', NULL, -1, 'verificationMail', 'MBox Verification Mail');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('<h3>Dear [NAME],</h3> <h3>Your M Box account has been terminated.</h3> <h3>For more information you can contact us at our email address.</h3><br> <h3>Regards, </h3>  <h3>M Box</h3>', -1,'2018-08-27 00:00:00', NULL, -1, 'deleteRecordLabelMail', 'MBox Account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('<h3>Dear [NAME],</h3> <h3>Your Record Label has been removed.</h3> <h3>Your page will remain as it is, but you will not be able to post new songs until you join a record label that cooperates with us.</h3><br> <h3>Regards, </h3>  <h3>M Box</h3>', -1,'2018-08-27 00:00:00', NULL, -1, 'deleteArtistMail', 'MBox Account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('<h3>Dear [NAME],</h3> <h3>Your M Box account has benn created!</h3> <h3><a href="[APPURL]">Click here to set your password</a> <h3> After setting up your password you can login with [EMAILADRESS]</h3> </h3><br> <h3>Regards, </h3>  <h3>M Box</h3>', -1,'2018-08-27 00:00:00', NULL, -1, 'RecordLabelSignUp', 'MBox Account');

INSERT INTO public.email_template(body, created_by, date_created, date_modified, modified_by, name, subject)
	VALUES ('<h3>Dear [NAME],</h3> <h3>You have requested a password change on M Box.</h3> <h3> <a href="[APPURL]">Click here </a></h3> <h3> If you did not request this change, please contact support in the About tab on M Box.</h3> <br> <h3>Regards, </h3>  <h3>M Box</h3>', -1,'2018-08-27 00:00:00', NULL, -1, 'ForgotPassword', 'MBox Account');
