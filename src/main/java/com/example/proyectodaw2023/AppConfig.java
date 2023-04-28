package com.example.proyectodaw2023;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

@EmbeddedIdentityStoreDefinition({
        @Credentials(callerName = "admin", password = "admin", groups = {"ADMINISTRADORES"}),
        @Credentials(callerName = "user", password = "user", groups = {"USUARIOS"})
})
/*@BasicAuthenticationMechanismDefinition( realmName = "Catálogo de biblioteca" )*/
@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/inicio_de_sesion.xhtml",
                errorPage = "/inicio_de_sesion.xhtml?error",
                useForwardToLogin = false
        )
)
@ApplicationScoped
@FacesConfig
public class AppConfig {

}
