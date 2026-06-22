package com.example.usuariosApi.Usuario.Cliente.securite;

import com.example.usuariosApi.Usuario.Cliente.entity.Cliente;
import com.example.usuariosApi.Usuario.Cliente.service.ClienteService;
import com.example.usuariosApi.Usuario.Execoes.UsuarioNaoExistente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class CustomRegisteredClienteRepository implements RegisteredClientRepository {

    private final ClienteService clienteService;
    private final TokenSettings tokenSettings;
    private final ClientSettings clientSettings;

    @Override
    public void save(RegisteredClient registeredClient) {
    }

    @Override
    public RegisteredClient findById(String id) {
        var cliente = clienteService.obterporClienteId(id);
        if (cliente == null) {
            return null;
        }
        return buildRegisteredClient(cliente);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var cliente = clienteService.obterporClienteId(clientId);
        if (cliente == null) {
            return null;
        }
        return buildRegisteredClient(cliente);
    }

    // evita repetição de código
    private RegisteredClient buildRegisteredClient(Cliente cliente) {
        return RegisteredClient.withId(cliente.getId().toString())
                .clientId(cliente.getClienteid())
                .clientSecret(cliente.getClienteSecret())
                .redirectUri(cliente.getRedirectURI())
                .scope(OidcScopes.OPENID)
                .scope(cliente.getScope())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenSettings(tokenSettings)
                .clientSettings(clientSettings)
                .build();
    }
}