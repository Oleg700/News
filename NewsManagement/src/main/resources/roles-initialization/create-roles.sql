insert into users (id, username,  password, enabled) values (1, 'admin',  '$2a$10$dK8PC462iX6ea8dcngTxtupDYFEC3UcGxXbaxrLS.mkVQUPjz6q.6', 1)

insert into roles(id, name) values (1, 'ROLE_ADMIN')


insert into privileges (id, name) values (1, 'PRIVILEGE_WRITE_USER')
insert into privileges (id, name) values (2, 'PRIVILEGE_WRITE_ROLE')
insert into privileges (id, name) values (3, 'PRIVILEGE_WRITE_PRIVILEGE')
insert into privileges (id, name) values (4, 'PRIVILEGE_READ_USER')
insert into privileges (id, name) values (5, 'PRIVILEGE_READ_ROLE')
insert into privileges (id, name) values (6, 'PRIVILEGE_READ_PRIVILEGE')
insert into privileges (id, name) values (7, 'PRIVILEGE_WRITE_COMMENT')



insert into roles_privileges values(1,1)
insert into roles_privileges values(1,2)
insert into roles_privileges values(1,3)
insert into roles_privileges values(1,4)
insert into roles_privileges values(1,5)
insert into roles_privileges values(1,6)
insert into roles_privileges values(1,7)

insert into users_roles values(1,1)



insert into users (id, username,  password, enabled) values (2, 'editor',  '$2a$10$ImQ1cAL0JnXCMwojGnvGzOscT5adZrjEHqIIynGqfXhDRM.pN/2Ua', 1)

insert into roles(id, name) values (2, 'ROLE_EDITOR')

insert into privileges (id, name) values (8, 'PRIVILEGE_WRITE_NEWS')
insert into privileges (id, name) values (9, 'PRIVILEGE_UPDATE_NEWS')
insert into privileges (id, name) values (10, 'PRIVILEGE_DELETE_NEWS')

insert into roles_privileges values(2,7)
insert into roles_privileges values(2,8)
insert into roles_privileges values(2,9)
insert into roles_privileges values(2,10)


insert into users_roles values(2,2)




