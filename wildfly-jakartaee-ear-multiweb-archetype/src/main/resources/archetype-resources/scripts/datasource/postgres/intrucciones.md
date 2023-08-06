Configuración del sistema para JBOSS / WILDFLY  
=====
¿Cómo añadir un driver  java  al sistema?
======

* Copiar la carpeta **postgresql** en la siguiente ubicación
        
        $JBOSS_HOME/modules/system/layers/base/org
* Editar los archivos de configuración ( standalone / standalone-full).xml que están en 

        $JBOSS_HOME/standalone/configuration
        
        <drivers>
            <driver name="h2" module="com.h2database.h2">
                <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
            </driver>
            <!-- POSTGRES INICIO-->
            <driver name="postgresql" module="org.postgresql">
                <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class> 
            </driver>
            <!-- POSTGRES FIN-->
        </drivers>

* Añadir las lineas correspondientes a posgresql en el apartado drivers
