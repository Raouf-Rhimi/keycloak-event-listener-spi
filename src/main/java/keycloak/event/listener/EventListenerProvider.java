package keycloak.event.listener;

import org.keycloak.events.Event;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;


public class EventListenerProvider implements org.keycloak.events.EventListenerProvider {
    KeycloakSession session;
    public EventListenerProvider(KeycloakSession session){
        this.session=session;
    }

    @Override
    public void onEvent(Event event) {
        /**
         * define the user event you want to listen for and the action.
         */
        if (event.getType() == EventType.VERIFY_EMAIL){
            RealmModel realm=session.realms().getRealm(event.getRealmId());
            UserModel user = session.users().getUserById(realm,event.getUserId());
            user.addRequiredAction(UserModel.RequiredAction.UPDATE_PASSWORD);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        /**
         * define the admin event you want to listen for and the action.
         */
    }

    @Override
    public void close() {

    }
}