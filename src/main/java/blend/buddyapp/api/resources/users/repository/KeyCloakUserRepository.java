package blend.buddyapp.api.resources.users.repository;

import blend.buddyapp.api.resources.users.model.User;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Optional;


@Repository
public class KeyCloakUserRepository {

    private final RealmResource BuddyRealmResourceSignedInAsAdmin;

    @Autowired
    public KeyCloakUserRepository(Environment properties){
        String buddyRealm = properties.getProperty("keycloak.realm");
        String serverUrl = properties.getProperty("keycloak.auth-server-url");
        String tokenRequestServiceClientId = properties.getProperty("keycloak.resource");
        String tokenRequestServiceClientSecret = properties.getProperty("keycloak.credentials.secret");
        String password = properties.getProperty("custom.keycloak.credentials.password");
        String username = properties.getProperty("custom.keycloak.credentials.username");

        this.BuddyRealmResourceSignedInAsAdmin = KeycloakBuilder
                .builder()
                .realm(buddyRealm)
                .serverUrl(serverUrl)
                .clientId(tokenRequestServiceClientId)
                .clientSecret(tokenRequestServiceClientSecret)
                .password(password)
                .username(username)
                .build()
                .realm(buddyRealm);
    }

    private CredentialRepresentation credential(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }

    private Optional<UserRepresentation> getUser(String username){
        return BuddyRealmResourceSignedInAsAdmin
                .users()
                .search(username, true)
                .stream()
                .findFirst();
    }

    private Optional<RoleRepresentation> getRole(String rolename){
        return  BuddyRealmResourceSignedInAsAdmin
                .roles()
                .list()
                .stream()
                .filter(_role -> _role.getName().equals(rolename))
                .findFirst();
    }

    public HttpStatus subscribeUserToRole(String rolename, String username){
        Optional<RoleRepresentation> fetchedRole = getRole(rolename);
        Optional<UserRepresentation> fetchedUser = getUser(username);
        if (!fetchedRole.isPresent() || !fetchedUser.isPresent())
            return HttpStatus.NOT_FOUND;

        BuddyRealmResourceSignedInAsAdmin
                .users()
                .get(fetchedUser.get().getId())
                .roles()
                .realmLevel()
                .add(Collections.singletonList(fetchedRole.get()));
        return HttpStatus.CREATED;
    }

    public HttpStatus addUser(User account)  {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(account.getStudentNumber().toString());
        user.setEnabled(true);
        user.setCredentials(Collections.singletonList(this.credential(account.getPassword())));
        user.setRealmRoles(Collections.singletonList(account.getUserType()));
        Response kc =
                BuddyRealmResourceSignedInAsAdmin
                .users()
                .create(user);
        return HttpStatus.valueOf(kc.getStatus());
    }

    public HttpStatus deleteUser(String username) {
        UsersResource users = BuddyRealmResourceSignedInAsAdmin.users();
        if (!getUser(username).isPresent()) return HttpStatus.NOT_FOUND;
        return HttpStatus.valueOf(users.delete(getUser(username).get().getId()).getStatus());
    }
}







