-- 2024-03-01 12:00:00  1709262000
-- 2024-03-01 14:00:00  1709269200

truncate table QES_SPACE;
insert into QES_SPACE (
  space_id, owner_user_id, name, opened_time, end_time
) values (
  '01234567-abcd-abcd-abcd-space0000001'
  , '01234567-abcd-abcd-abcd-user00000001'
  , 'TEST'
  , 1709262000
  , 1709269200
);


truncate table QES_QUESTION;




truncate table QES_ANSWER;



