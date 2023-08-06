#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

create sequence ${prefixuppercase}_USER_SEQ start 1 increment 1;

ALTER sequence ${prefixuppercase}_USER_SEQ OWNER TO  ${rootArtifactId};

create sequence ${prefixuppercase}_ROLES_SEQ start 1 increment 1;
ALTER sequence ${prefixuppercase}_ROLES_SEQ OWNER TO  ${rootArtifactId};

CREATE TABLE ${prefixuppercase}_USERS (
    ID INT8 not null,
    USERNAME VARCHAR(20),
    PASSWORD VARCHAR(60),
    SALT VARCHAR(60),
    ITERATION_COUNT INT8,
    constraint ${prefixuppercase}_USERS_PK primary key (ID)
);

ALTER TABLE ${prefixuppercase}_USERS OWNER TO  ${rootArtifactId};

CREATE TABLE ${prefixuppercase}_ROLES (
    ID INT8,
    NAME VARCHAR(20),
    constraint ${prefixuppercase}_ROLES_PK primary key (ID)
                   );
ALTER TABLE ${prefixuppercase}_USERS OWNER TO  ${rootArtifactId};


CREATE TABLE  ${prefixuppercase}_USERS_ROLES (
    USER_ID INT,
    ROLE_ID INT,
    constraint ${prefixuppercase}_USERS_ROLES_PK primary key (USER_ID,ROLE_ID)
);

ALTER TABLE ${prefixuppercase}_USERS_ROLES OWNER TO  ${rootArtifactId};

ALTER TABLE  ${prefixuppercase}_USERS_ROLES add constraint  ${prefixuppercase}_USER_ROLES_USER_FK foreign key (USER_ID) references ${prefixuppercase}_USERS (ID);
ALTER TABLE  ${prefixuppercase}_USERS_ROLES add constraint  ${prefixuppercase}_USER_ROLES_ROLES_FK foreign key (ROLE_ID) references ${prefixuppercase}_ROLES (ID);

