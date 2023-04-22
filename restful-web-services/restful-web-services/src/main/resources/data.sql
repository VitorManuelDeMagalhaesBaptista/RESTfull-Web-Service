insert into user_details(id, birthdate, name)
values(10001, current_date(), 'Toni');

insert into user_details(id, birthdate, name)
values(10002, current_date(), 'Dare');

insert into user_details(id, birthdate, name)
values(10003, current_date(), 'Canoas');

insert into post(id, description, user_id)
values(20001, 'I want to learn Digital', 10001);

insert into post(id, description, user_id)
values(20002, 'I want to learn Integration', 10001);

insert into post(id, description, user_id)
values(20003, 'I want to learn Back End', 10002);

insert into post(id, description, user_id)
values(20004, 'I want to learn Front End', 10002);