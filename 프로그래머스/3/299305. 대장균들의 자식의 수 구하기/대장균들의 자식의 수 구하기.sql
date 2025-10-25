-- 코드를 작성해주세요
select ID, (
    select count(*)
    from ECOLI_DATA as child
    where parent.id = child.parent_id
) as CHILD_COUNT
from ECOLI_DATA as parent
order by id;