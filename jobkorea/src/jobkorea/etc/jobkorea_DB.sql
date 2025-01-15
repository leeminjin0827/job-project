drop database if exists jobkorea;
create database jobkorea;
use jobkorea;

# 일반회원관리
create table member(
	mno int unsigned auto_increment,
	mid varchar(12) not null unique,
    mpwd varchar(20) not null,
    mname varchar(30) not null,
    mgender boolean default false,
    mdate varchar(30
    ) not null,
    maddr varchar(100) not null,
    constraint primary key(mno)
);
insert into member(mid, mpwd, mname, mgender, mdate, maddr) values('test1', '123123', '유재석', false, '1972-08-14', '서울 강남구');
insert into member(mid, mpwd, mname, mgender, mdate, maddr) values('test2', '456456', '강호동', false, '1970-07-14', '서울 마포구');
insert into member(mid, mpwd, mname, mgender, mdate, maddr) values('test3', '789789', '신동엽', false, '1971-02-17', '서울 노원구');
insert into member(mid, mpwd, mname, mgender, mdate, maddr) values('test4', '147147', '전은서', true, '2025-01-06', '서울 부평구');
insert into member(mid, mpwd, mname, mgender, mdate, maddr) values('test5', '258258', '이민진', false, '2025-01-01', '서울 남동구');
insert into member(mid, mpwd, mname, mgender, mdate, maddr) values('test6', '369369', '김도하', true, '2024-12-31', '서울 연수구');


# 기업회원관리
create table enterprise(
	eno int unsigned auto_increment,
    eid varchar(12) not null unique,
    epwd varchar(20) not null,
    ename varchar(30) not null,
    eaddr varchar(100) not null,
    constraint primary key(eno)
);
insert into enterprise(eid, epwd, ename, eaddr) values('qwer1', '112233', '반다이남코코리아', '중구 서소문로 89');
insert into enterprise(eid, epwd, ename, eaddr) values('qwer2', '445566', '(주)덴티움', '수원시 영토구 76');
insert into enterprise(eid, epwd, ename, eaddr) values('qwer3', '556677', '(주)지학사', '마포구 신촌로 6');
insert into enterprise(eid, epwd, ename, eaddr) values('qwer4', '778899', '엔테크서비스', '분당구 123');
insert into enterprise(eid, epwd, ename, eaddr) values('qwer5', '101010', '신한라이프생명보험', '강남구 123');
insert into enterprise(eid, epwd, ename, eaddr) values('qwer6', '111111', '(주)코비엔', '영등포구123');

# 카테고리(직종)관리
create table category(
   cno int unsigned auto_increment ,
    cname varchar(30) not null unique,
    constraint primary key(cno)
);
insert into category (cname) values ('법무');
insert into category (cname) values ('마케팅');
insert into category (cname) values ('디자인');
insert into category (cname) values ('영업');
insert into category (cname) values ('금융');
insert into category (cname) values ('제조');
insert into category (cname) values ('교육');
insert into category (cname) values ('개발');


# 공고관리
create table post(
   pno int unsigned auto_increment,
    ptitle varchar(100) not null,
   pcontent text not null,
    phistory varchar(20) not null,
    pcount  varchar(20) not null,
    psalary varchar(20) not null,
    pstart datetime default now(),
    pend datetime not null,
    cno int unsigned,
    eno int unsigned,
    constraint primary key (pno),
    constraint foreign key(cno) references category (cno),
    constraint foreign key(eno) references enterprise (eno)
);
insert into post (ptitle,pcontent, phistory, pcount,psalary, pend, cno , eno) 
values('국민은행','JAVA / 클라우드 유경험자','경력무관','0명','회사내규에 따름','2025-02-01','8','6');
insert into post (ptitle,pcontent, phistory, pcount,psalary, pend, cno , eno)
   values('법무담당자 경력직','서류전형 > 인적성검사 > 임원면접 > 채용검진 > 합격으로 진행',' 5년 이상','00명','회사내규에 따름','2025-02-18', '1' , '2');
insert into post (ptitle,pcontent, phistory, pcount,psalary, pend, cno , eno)
   values('2025년 1월 수시채용','JAVA / JS / 웹 서버 운영경험자','경력무관','00명','회사내규에 따름','2025-03-05', '8' , '3');
insert into post (ptitle,pcontent, phistory, pcount,psalary, pend, cno , eno)
   values('SW QA 엔지니어 신입 공채','C++/JAVA/PYTHON 개발 경험이 있는 분','3년 이상','0명','3500만원~','2025-03-05', '8' , '4');
insert into post (ptitle,pcontent, phistory, pcount,psalary, pend, cno , eno)
   values('2025 신한 라이프 ','채용업무 및 TM / 영업','경력무관','00명','회사내규에 따름','2025-04-01', '4' , '5');
insert into post (ptitle,pcontent, phistory, pcount,psalary, pend, cno , eno)
   values('법무 / IP 담당자','특허기술 동향조사 및 시장 모니터링 업무하실 분 찾습니다.','7년 이상','0명','5000만원~','2025-04-01', '1' , '1');



# 지원관리
create table apply(
   ano int unsigned auto_increment ,
    pno int unsigned ,
    mno int unsigned ,
    apass boolean not null default false, ###
    constraint primary key ( ano ) ,
    constraint foreign key ( pno ) references post(pno) ,
    constraint foreign key ( mno ) references member(mno)
); # table end
insert into apply( pno , mno , apass ) values ( '1' , '5' , true );
insert into apply( pno , mno , apass ) values ( '1' , '4' , false );
insert into apply( pno , mno , apass ) values ( '2' , '1' , false );
insert into apply( pno , mno , apass ) values ( '2' , '3' , true );
insert into apply( pno , mno , apass ) values ( '6' , '2' , true );
insert into apply( pno , mno , apass ) values ( '6' , '3' , true );


# 후기관리
create table review(
   mno int unsigned ,
   eno int unsigned ,
   rno int unsigned auto_increment ,
   rcontent varchar(255) not null ,
   rrating int unsigned not null ,
    rdate datetime default now() ,
    constraint primary key ( rno ) ,
   constraint foreign key ( eno ) references enterprise ( eno ),
   constraint foreign key ( mno ) references member ( mno )
); # table end
insert into review( rrating , rcontent , eno , mno ) values ( '5' , '밥이 맛있어요.' , '6' , '5' );
insert into review( rrating , rcontent , eno , mno ) values ( '4' , '돈을 많이 줘요.' , '2' , '4' );
insert into review( rrating , rcontent , eno , mno ) values ( '2' , '가지마세요.' , '1' , '2' );
insert into review( rrating , rcontent , eno , mno ) values ( '2' , '비추합니다.' , '1' , '3' );
insert into review( rrating , rcontent , eno , mno ) values ( '5' , '좋아요.' , '2' , '3' );


-- ******* DML ******* --
-- 우수 기업 리스트 sample --
select e.ename , avg(r.rrating) as ravg from review r left outer join enterprise e on  r.eno = e.eno group by r.eno order by ravg desc;
-- 기업별 후기 리스트 sample --
select e.ename,  r.rcontent, r.rrating from review r join enterprise e on r.eno = e.eno  where e.ename = '(주)코비엔';  
-- 지원리스트 출력 sample --
select a.ano, p.ptitle , p.pend , a.apass , a.ano from apply a join post p on a.pno = p.pno  where a.mno = '3' ; 
-- 카테고리리스트 sample --
select * from category order by cno asc;
-- 입력값에 해당되는 공고리스트 sample --
select p.pno , p.ptitle , p.pcontent ,p.phistory , p.pcount , p.psalary , p.pstart , p.pend , c.cname
	from post p join category c on p.cno = c.cno where p.cno = '8';
-- 로그인된 회원번호로 공고 지원 sample --
insert into apply(pno, mno) values (1,1);
-- 지원 삭제 sample --
delete from apply where ano = 1;
-- 지원 수정 sample --
update member set mpwd = '얍' , mname = '얍', mgender = true , mdate = '얍' , maddr = '얍' where mno = 1;
select * from member;
select * from post;
select p.* , c.cname , e.id from post as p inner join category as c on p.cno = c.cno inner join enterprice as e on p.eno = e.eno;




