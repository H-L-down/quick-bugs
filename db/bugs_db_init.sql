create DATABASE IF NOT EXISTS bugs;

use bugs;

CREATE TABLE IF NOT EXISTS user(
   id VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL,
   PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS bug(
   id VARCHAR(256) NOT NULL,
   content longtext COMMENT ' 内容',
   create_time date DEFAULT NULL COMMENT '创建时间',
   update_time date DEFAULT NULL COMMENT '修改时间',
   PRIMARY KEY ( id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user_bug(
    user_id VARCHAR(256) NOT NULL,
    bug_id VARCHAR(256) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;