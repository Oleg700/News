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