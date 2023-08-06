#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )
Se recomienda eliminar el esquema public y crear uno para el usuario:

    REVOKE CREATE ON SCHEMA public FROM PUBLIC;
    CREATE SCHEMA ${rootArtifactId} AUTHORIZATION ${rootArtifactId};

ver: https://www.postgresql.org/docs/10/ddl-schemas.html