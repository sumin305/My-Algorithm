-- 코드를 입력하세요
select r.food_type, r.rest_id, r.rest_name, r.favorites
from rest_info as r
where (food_type, favorites) = (select food_type, max(favorites)
from rest_info
group by food_type
having food_type = r.food_type)
order by food_type desc