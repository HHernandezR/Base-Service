package app.keepcash.base.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import app.keepcash.base.model.secrets.SecretsAuth;
import app.keepcash.utils.Utils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private SecretsInit secretsInit;

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		Authentication authenticationResponse = null; 
		try {
			SecretsAuth secrets = this.secretsInit.getAuthSecrets();
			String certB64 = secrets.getCertificate();
			String userEncrypted = secrets.getUser();
			String passwordEncrypted = secrets.getPassword();
			
			String userInput = authentication.getName();
			String passwordInput = authentication.getCredentials().toString();
			
			boolean valid = Utils.validateCredentials(certB64, userInput, passwordInput, userEncrypted, passwordEncrypted);
	 
			if (valid) {
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				UserDetails principal = new User(userInput, passwordInput, grantedAuths);
				authenticationResponse = new UsernamePasswordAuthenticationToken(principal, passwordInput, grantedAuths);
			}
		} catch (AuthenticationException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return authenticationResponse;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
