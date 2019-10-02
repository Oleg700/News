insert into users (id, username,  password, enabled) values (1, 'editor',  '$2a$10$ImQ1cAL0JnXCMwojGnvGzOscT5adZrjEHqIIynGqfXhDRM.pN/2Ua', 1)

insert into roles(id, name) values (1, 'ROLE_EDITOR')

insert into privileges (id, name) values (1, 'PRIVILEGE_WRITE_NEWS')
insert into privileges (id, name) values (2, 'PRIVILEGE_UPDATE_NEWS')
insert into privileges (id, name) values (3, 'PRIVILEGE_DELETE_NEWS')
insert into privileges (id, name) values (4, 'PRIVILEGE_WRITE_COMMENT')

insert into roles_privileges values(1,1)
insert into roles_privileges values(1,2)
insert into roles_privileges values(1,3)
insert into roles_privileges values(1,4)

insert into users_roles values(1,1)