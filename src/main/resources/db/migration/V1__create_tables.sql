create table artist (id  serial not null, bio varchar(500), is_deleted boolean not null, user_id int4, primary key (id));

create table configuration (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, key varchar(100), value varchar(255), primary key (id));

create table email_template (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, body varchar(255), name varchar(50) not null, subject varchar(50), primary key (id));

create table record_label (id  serial not null, about_info varchar(500), user_id int4, primary key (id));

create table record_label_artists (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, artist_id int4, record_label_id int4, primary key (id));

create table role (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, name varchar(20) not null, primary key (id));

create table song (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, album_name varchar(50), date_of_release timestamp, genre varchar(255), image varchar(50), lyrics varchar(255), name varchar(50), vimeo_link varchar(100), youtube_link varchar(100), artist_id int4, primary key (id));

create table user_roles (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, role_id int4, user_id int4, primary key (id));

create table users (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, email varchar(320) not null, is_activated boolean not null, name varchar(50) not null, password varchar(30) not null, picture varchar(50) not null, primary key (id));

create table verification_token (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, token varchar(255), user_id int4, primary key (id));

alter table configuration add constraint UC_configuration_key unique (key);

alter table email_template add constraint UC_emailTemplate_name unique (name);

alter table role add constraint UC_role_name unique (name);

alter table song add constraint UC_song_image unique (image);

alter table users add constraint UC_users_picture unique (picture);

alter table artist add constraint FK_artist_user foreign key (user_id) references users;

alter table record_label add constraint FK_recodLabel_User foreign key (user_id) references users;

alter table record_label_artists add constraint FK_recordLabelArtist_artist foreign key (artist_id) references artist;

alter table record_label_artists add constraint FK_recordLabelArtists_recordLabel foreign key (record_label_id) references record_label;

alter table song add constraint FK_song_artist foreign key (artist_id) references artist;

alter table user_roles add constraint FK_role_userRoles foreign key (role_id) references role;

alter table user_roles add constraint FK_user_userRoles foreign key (user_id) references users;

alter table verification_token add constraint FK_verificationToken_user foreign key (user_id) references users;