SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(DATE_OF_BIRTH, "%Y-%m-%d")
FROM MEMBER_PROFILE
WHERE GENDER = "W" AND TLNO IS NOT NULL AND date_format(DATE_OF_BIRTH,'%m') = '03';
