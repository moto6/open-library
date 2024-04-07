create table if not exists `open-library`.ACCOUNTS
(
    ACCOUNT_ID      bigint auto_increment
    primary key,
    ACCOUNT_BARCODE varchar(255) null,
    IAM_COMDE       varchar(255) not null,
    note            varchar(255) null,
    constraint UK_242h5xb9sctwxqnm0nmn2jifm
    unique (IAM_COMDE)
    );

create table if not exists `open-library`.ADMINISTRATOR
(
    ADMINISTRATOR_ID   bigint auto_increment
    primary key,
    ADMINISTRATOR_CODE varchar(255) not null,
    constraint UK_d7ttrg5tr7t91xbxexw2lf81l
    unique (ADMINISTRATOR_CODE)
    );

create table if not exists `open-library`.BOOK_DETAIL
(
    BOOK_DETAIL_ID bigint auto_increment
    primary key
);

create table if not exists `open-library`.BOOK_MASTER
(
    BOOK_MASTER_ID   bigint auto_increment
    primary key,
    AUTHOR           varchar(255) null,
    INFO_URL         varchar(255) null,
    ISBN_CODE        varchar(255) null,
    KDC_CODE         varchar(20)  null,
    PUBLICATION_YEAR int          null,
    PUBLISHER        varchar(255) null,
    TITLE            varchar(500) null
    );

create fulltext index IDX_FULLTEXT_TITLE
    on `open-library`.BOOK_MASTER (TITLE);

create index IDX_ISBN_CODE
    on `open-library`.BOOK_MASTER (ISBN_CODE);

create table if not exists `open-library`.BOOK_STOCK
(
    BOOK_STOCK_ID        bigint auto_increment
    primary key,
    TOTAL_RENT_COUNT     bigint       null,
    bookBarCode          varchar(255) null,
    BOOK_MASTER_ID       bigint       null,
    LAST_RENT_ACCOUNT_ID bigint       null,
    note                 varchar(255) null,
    state                int          null,
    STOCK_COUNT          varchar(255) null
    );

create index IDX_BOOK_MASTER_ID
    on `open-library`.BOOK_STOCK (BOOK_MASTER_ID);

create table if not exists `open-library`.CONNECT_LOG
(
    CONNECT_LOG_ID    bigint auto_increment
    primary key,
    durationMills     bigint       null,
    requestBody       varchar(500) null,
    requestHeader     varchar(500) null,
    REQUEST_ID        bigint       null,
    requestTimestamp  bigint       null,
    responseBody      varchar(500) null,
    responseTimestamp bigint       null
    );

create table if not exists `open-library`.FAULT_LOG
(
    FAULT_LOG_ID    bigint auto_increment
    primary key,
    DATA            varchar(500) null,
    FAULT_TIMESTAMP bigint       null,
    TRANSACTION_ID  bigint       null
    );

create table if not exists `open-library`.LENT
(
    LEND_ID                 bigint auto_increment
    primary key,
    ACCOUNT_ID              bigint null,
    BOOK_STOCK_ID           bigint null,
    IS_RETRIEVED            bit    null,
    LEND_TIMESTAMP          bigint null,
    RETRIEV_EXTENSION_COUNT bigint null,
    RETRIEV_TIMESTAMP       bigint null
);

create table if not exists `open-library`.PERSIST_LOG
(
    PERSIST_LOG_ID      bigint auto_increment
    primary key,
    DURATION_TIME_MILLS bigint null,
    BEGIN_TIMESTAMP     bigint null,
    CONNECT_LOG_ID      bigint null,
    END_TIMESTAMP       bigint null,
    IS_SUCCESS          bit    null,
    TRANSACTION_ID      bigint null
);

create table if not exists `open-library`.RESERVATION
(
    RESERVATION_ID        binary(16) not null
    primary key,
    ACCOUNT_ID            bigint     not null,
    BOOK_STOCK_ID         bigint     not null,
    RESERVATION_STATUS    int        null,
    RESERVATION_TIMESTAMP bigint     null
    );

