create table whereismyhome.dongcode
(
    dongCode  varchar(10) not null
        primary key,
    sidoName  varchar(30) null,
    gugunName varchar(30) null,
    dongName  varchar(30) null
);

create table whereismyhome.houseinfo
(
    aptCode              bigint      not null
        primary key,
    buildYear            int         null,
    roadName             varchar(40) null,
    roadNameBonbun       varchar(5)  null,
    roadNameBubun        varchar(5)  null,
    roadNameSeq          varchar(2)  null,
    roadNameBasementCode varchar(1)  null,
    roadNameCode         varchar(7)  null,
    dong                 varchar(40) null,
    bonbun               varchar(4)  null,
    bubun                varchar(4)  null,
    sigunguCode          varchar(5)  null,
    eubmyundongCode      varchar(5)  null,
    dongCode             varchar(10) null,
    landCode             varchar(1)  null,
    apartmentName        varchar(40) null,
    jibun                varchar(10) null,
    lng                  varchar(30) null,
    lat                  varchar(30) null,
    constraint `UNIQUE`
        unique (buildYear, apartmentName, jibun, sigunguCode, eubmyundongCode),
    constraint houseinfo_dongCode_dongcode_dongCode_fk
        foreign key (dongCode) references whereismyhome.dongcode (dongCode)
);

create table whereismyhome.housedeal
(
    no             bigint      not null
        primary key,
    dealAmount     varchar(20) null,
    dealYear       int         null,
    dealMonth      int         null,
    dealDay        int         null,
    area           varchar(20) null,
    floor          varchar(4)  null,
    cancelDealType varchar(1)  null,
    aptCode        bigint      null,
    constraint housedeal_aptCode_houseinfo_aptCode_fk
        foreign key (aptCode) references whereismyhome.houseinfo (aptCode)
);

create index housedeal_aptCode_houseinfo_aptCode_fk_idx
    on whereismyhome.housedeal (aptCode);

create index houseinfo_dongCode_dongcode_dongCode_fk_idx
    on whereismyhome.houseinfo (dongCode);

create table whereismyhome.members
(
    user_id       varchar(16)                         not null
        primary key,
    user_name     varchar(20)                         not null,
    user_password varchar(255)                        not null,
    email_id      varchar(20)                         null,
    email_domain  varchar(45)                         null,
    join_date     timestamp default CURRENT_TIMESTAMP not null,
    token         varchar(1000)                       null
);

create table whereismyhome.board
(
    article_no    int auto_increment
        primary key,
    user_id       varchar(16)                         null,
    subject       varchar(100)                        null,
    content       varchar(2000)                       null,
    hit           int       default 0                 null,
    register_time timestamp default CURRENT_TIMESTAMP not null,
    constraint board_to_members_user_id_fk
        foreign key (user_id) references whereismyhome.members (user_id)
);

create table whereismyhome.file_info
(
    idx           int auto_increment
        primary key,
    article_no    int         null,
    save_folder   varchar(45) null,
    original_file varchar(50) null,
    save_file     varchar(50) null,
    constraint file_info_to_board_article_no_fk
        foreign key (article_no) references whereismyhome.board (article_no)
);

create table whereismyhome.rooms
(
    id            bigint auto_increment
        primary key,
    lat           varchar(255)  not null,
    lng           varchar(255)  not null,
    comment       varchar(1000) null,
    user_id       varchar(16)   not null,
    subject       varchar(255)  null,
    register_time timestamp     null,
    start_date    date          null,
    end_date      date          null,
    deposit       int           null,
    monthly       int           null,
    constraint rooms_ibfk_1
        foreign key (user_id) references whereismyhome.members (user_id)
);

create table whereismyhome.options
(
    id      bigint auto_increment
        primary key,
    room_id bigint not null,
    opt     int    not null,
    constraint options_rooms_id_fk
        foreign key (room_id) references whereismyhome.rooms (id)
);

