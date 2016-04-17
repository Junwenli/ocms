create table T_SYS_ICON
(
  icon_id  int auto_increment not null,
  icon_url VARCHAR(255),
  title    VARCHAR(255),
  url      VARCHAR(255),
  CONSTRAINT PK_T_SYS_ICON PRIMARY KEY (ICON_ID)
);


create table T_SYS_ICON_ROLE
(
  item_id  int auto_increment not null,
  icon_id int,
  role_id int,
  CONSTRAINT PK_T_SYS_ICON_ROLE PRIMARY KEY (ITEM_ID)
);

/*==============================================================*/
/* Table: T_SYS_USERS  用户表                                         */
/*==============================================================*/
CREATE TABLE T_SYS_USERS 
(
   USER_ID              int   auto_increment       NOT NULL,
   USER_CODE            VARCHAR(30) Unique,
   AUTHENTICATOR        VARCHAR(250),
   DEPT_ID              int,
   NAME                 VARCHAR(30),
   EMAIL                VARCHAR(50),
   MOBILE               VARCHAR(30),
   CREATE_TIME          VARCHAR(14),
   WRONG_NUMBER  int default 0,
   STATUS               SMALLINT,
   CONSTRAINT PK_T_SYS_USERS PRIMARY KEY (USER_ID)
);

/*==============================================================*/
/* Table: T_SYS_ROLE_PERMISSION      角色权限中间表                        */
/*==============================================================*/
CREATE TABLE T_SYS_ROLE_PERMISSION 
(
   ITEM_ID              int   auto_increment     NOT NULL,
   PERMISSION_ID        int,
   ROLE_ID              int,
   CONSTRAINT PK_T_SYS_ROLE_PERMISSION PRIMARY KEY (ITEM_ID)
);

/*==============================================================*/
/* Table: T_SYS_ROLE       角色表                                     */
/*==============================================================*/
CREATE TABLE T_SYS_ROLE 
(
   ROLE_ID               int   auto_increment      NOT NULL,
   ROLE_CODE            VARCHAR(30) Unique,
   TITLE                VARCHAR(50),
   DESCRIPTION          VARCHAR(250),
   STATUS               SMALLINT,
   CONSTRAINT PK_T_SYS_ROLE PRIMARY KEY (ROLE_ID)
);


/*==============================================================*/
/* Table: T_SYS_PERMISSION      权限表         */
/*==============================================================*/
CREATE TABLE T_SYS_PERMISSION 
(
   PERMISSION_ID        int    auto_increment      NOT NULL,
   PERMISSION_CODE      VARCHAR(60)  Unique,
   TITLE                VARCHAR(100),
   PERMISSION_TYPE      SMALLINT,
   PARENT_ID		   int,
   URL                VARCHAR(255),
   DELETABLE       int default 0,
   SYSTEM_ID        VARCHAR(255) not null,
   CONSTRAINT PK_T_SYS_PERMISSION PRIMARY KEY (PERMISSION_ID)
);

/*==============================================================*/
/* Table: T_SYS_MEMBERSHIP          用户角色中间表      */
/*==============================================================*/
CREATE TABLE T_SYS_MEMBERSHIP 
(
   MEMBERSHIP_ID        int   auto_increment      NOT NULL,
   ROLE_ID              VARCHAR(30),
   USER_ID              int,
   CONSTRAINT PK_T_SYS_MEMBERSHIP PRIMARY KEY (MEMBERSHIP_ID)
);

/*==============================================================*/
/* Table: T_SYS_DEPARTMENT       部门表                               */
/*==============================================================*/
CREATE TABLE T_SYS_DEPARTMENT 
(
   DEPT_ID            int   auto_increment      NOT NULL,
   DEPT_CODE            VARCHAR(30)   Unique      NOT NULL,
   TITLE                VARCHAR(50),
   DESCRIPTION          VARCHAR(250),
   RES_RATE             INT,
   AUDIT_VOLUMN         INT,
   CONSTRAINT PK_T_SYS_DEPARTMENT PRIMARY KEY (DEPT_ID)
);

/*==============================================================*/
/* Table: T_SYS_USERS_PERMISSION  用户权限中间表                              */
/*==============================================================*/
CREATE TABLE T_SYS_USERS_PERMISSION
(
	ITEM_ID int auto_increment     NOT NULL,
	USER_ID int,
	PERMISSION_ID int,
	CONSTRAINT PK_T_STY_USERS_PERMISSION PRIMARY KEY (ITEM_ID)
);
/*==============================================================*/
/* Table: T_SYS_AREA       区域表                            */
/*==============================================================*/

CREATE TABLE T_SYS_AREA 
(
   AREA_ID            int   auto_increment      NOT NULL,
   AREA_CODE            VARCHAR(30)   Unique      NOT NULL,
   AREA_NAME                VARCHAR(50),
   DESCRIPTION          VARCHAR(250),
   CONSTRAINT PK_T_SYS_AREA PRIMARY KEY (AREA_ID)
);



