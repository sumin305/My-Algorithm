select mcdp_cd, count(pt_no) 
from appointment
where date_format(apnt_ymd, '%Y-%m') = '2022-05'
group by mcdp_cd
order by count(pt_no) asc, mcdp_cd asc ;