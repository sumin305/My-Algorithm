-- 코드를 입력하세요
SELECT concat('/home/grep/src/', b.board_id, '/',f.file_id, f.file_name,f.FILE_EXT) as file_path 
from USED_GOODS_BOARD b
join USED_GOODS_FILE f
on b.board_id = f.board_id
where b.views = (
select max(VIEWS)
          from USED_GOODS_BOARD
)
order by f.file_id desc