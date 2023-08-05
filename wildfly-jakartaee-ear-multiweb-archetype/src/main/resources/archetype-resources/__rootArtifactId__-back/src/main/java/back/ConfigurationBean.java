#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back;

import jakarta.faces.annotation.FacesConfig;

import static jakarta.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * Necessari per activar la integració CDI amb JSF 2.3.
 * Permet injectar la majoria d'objectes de JSF.
 * Veure punts 5.6.3 i 5.9 de l'especificació JSF.
 *
 * @author areus
 */
@FacesConfig(version = JSF_2_3)
public class ConfigurationBean {

}