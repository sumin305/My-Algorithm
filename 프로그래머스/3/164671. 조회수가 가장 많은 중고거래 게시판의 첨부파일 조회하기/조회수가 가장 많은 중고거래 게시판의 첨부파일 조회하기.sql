-- 코드를 입력하세요
SELECT CONCAT('/home/grep/src/', B.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS 'FILE_PATH'
FROM USED_GOODS_BOARD AS B JOIN USED_GOODS_FILE AS F ON B.BOARD_ID  = F.BOARD_ID 
WHERE B.VIEWS = (
SELECT max(VIEWS) from USED_GOODS_BOARD
)
ORDER BY F.FILE_ID DESC