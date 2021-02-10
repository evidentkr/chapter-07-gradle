insert
    into
        member
        (enabled, name, password, role, id)
    values
        (true, '회원', '{bcrypt}$2a$10$ATXfBS7qSDxoW4Z5Y2ktD.Kn5wpW.ZJKwyz3FzuYoPUMkJJpMpnlm', 'ROLE_MEMBER' , 'member1');

insert
    into
        member
        (enabled, name, password, role, id)
    values
        (true, '메니저', '{bcrypt}$2a$10$kHzlk7eMozkl3ll2BIT9AOrkQ.QAW0Xxpp2XoonVlh1bxfsRLgZ6G' , 'ROLE_MANAGER' , 'manager1');

insert
    into
        member
        (enabled, name, password, role, id)
    values
        (true, '어드민', '{bcrypt}$2a$10$s/cTUKx5DKisgQPWzPbb1eSqQw5RU49eZGQwJ8J8uEDQl2XCyOjoO', 'ROLE_ADMIN' , 'admin1');
