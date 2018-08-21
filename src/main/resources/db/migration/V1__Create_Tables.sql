create table artist (bio varchar(500), is_deleted boolean not null, id int4 not null, primary key (id));

create table configuration (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, key varchar(100), value varchar(255), primary key (id));

create table email_template (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, body varchar(255), name varchar(50) not null, subject varchar(50), primary key (id));

create table record_label (about_info varchar(500), id int4 not null, primary key (id));

create table record_label_artists (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, artist_id int4, record_label_id int4, primary key (id));

create table role (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, name varchar(20) not null, primary key (id));

create table song (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, album_name varchar(50), date_of_release timestamp, genre varchar(255), image varchar(50), lyrics varchar(255), name varchar(50), vimeo_link varchar(100), youtube_link varchar(100), artist_id int4, primary key (id));

create table user_roles (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, role_id int4, user_id int4, primary key (id));

create table users (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, email varchar(320) not null, is_activated boolean not null, name varchar(50) not null, password varchar(30) not null, picture varchar(50) not null, primary key (id));

create table verification_token (id  serial not null, created_by int4 not null, date_created timestamp not null, date_modified timestamp, modified_by int4 not null, token varchar(255), user_id int4, primary key (id));

alter table configuration add constraint UK_5wejhym1uspe4klluscbwo9r unique (key);

alter table email_template add constraint UK_4wd86hd0sdp3broyq2f2wmn6f unique (name);

alter table role add constraint UK_8sewwnpamngi6b1dwaa88askk unique (name);

alter table song add constraint UK_qlrxoaw9dtaxpy88pjgsh3kgh unique (image);

alter table users add constraint UK_48a4m91x6cihr54hdat04hkc9 unique (picture);

alter table artist add constraint FKsxoaek4yt2qagbuk31cp0rk6p foreign key (id) references users;

alter table record_label add constraint FKhikw38i77tqipv4kdftop8lni foreign key (id) references users;

alter table record_label_artists add constraint FK8n2q030fum23tsvs29n5sv5uu foreign key (artist_id) references artist;

alter table record_label_artists add constraint FK86hojjuoslil852066t6md2fp foreign key (record_label_id) references record_label;

alter table song add constraint FKa21ft97nj7thwrp5d31xdaxr foreign key (artist_id) references artist;

alter table user_roles add constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key (role_id) references role;

alter table user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;

alter table verification_token add constraint FK3asw9wnv76uxu3kr1ekq4i1ld foreign key (user_id) references users;
