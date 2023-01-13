-- bookshop

select * from book;

select * from author;

select a.no, a.title, a.rent, a.author_no,b.name
from book a
left join author b on a.author_no=b.no
order by no;