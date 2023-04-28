package com.example.proyectodaw2023;

import com.example.proyectodaw2023.clases.Usuario;
import com.example.proyectodaw2023.dao.UsuariosDao.UsuariosDAO;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.*;

@ApplicationScoped
public class UsuariosIdentityStore implements IdentityStore {
    @Inject @DAOJpa
    private UsuariosDAO usuariosDAO;
    private Map<String,String> credenciales; //ejemplo de almacén de credenciales
    public UsuariosIdentityStore() {}
    public CredentialValidationResult validate (UsernamePasswordCredential usernamePasswordCredential ) {

        //Recuperar credenciales proporcionadas por el servidor
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();

        //Ejemplo simple de verificación de credenciales
        Usuario usuario = usuariosDAO.buscaemail(username);

        if (usuario != null && usuario.getContrasena().equals(password)) {
        //Autenticación completada, obtener los roles del usuario...
            Set<String> roles = new HashSet<>(Arrays.asList("USUARIOS"));
        //Pasar datos del usuario al servidor
            return new CredentialValidationResult(username, roles);
        }
        return CredentialValidationResult.INVALID_RESULT; //Autenticación inválida
    }


}
