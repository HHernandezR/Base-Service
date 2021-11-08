package app.keepcash.base.config;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import app.keepcash.base.client.secrets.SecretsClient;
import app.keepcash.base.model.secrets.SecretsAuth;
import app.keepcash.base.model.secrets.SecretsResponse;

@Component
public class SecretsInit {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SecretsInit.class);

	@Autowired
	private SecretsClient secretsClient;

	private SecretsAuth secretsAuth;
	
	public SecretsInit() {
		super();
	}

	@PostConstruct
	public void postConstruct() {
		try {
			LOGGER.info("----- GETTING SECRETS -----");
			ResponseEntity<SecretsResponse> responseAuth = this.secretsClient.getAuthSecrets();
			this.setAuthSecrets(responseAuth);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private SecretsAuth setAuthSecrets(ResponseEntity<SecretsResponse> responseAuth) {
		this.secretsAuth = new SecretsAuth();
		try {
			if (HttpStatus.OK.equals(responseAuth.getStatusCode())) {
				LOGGER.info("----- SECRETS AUTH DONE -----");
				LOGGER.info(String.valueOf(responseAuth.getStatusCode()));
				
				JSONObject secretsObj = new JSONObject(responseAuth.getBody());
				
				if (null != secretsObj && secretsObj.has("data")) {
					JSONObject data = secretsObj.getJSONObject("data");
					
					if(null != data	&& data.has("certificate") && data.has("username") && data.has("password")) {
						String certificate = data.getString("certificate");
						String user = data.getString("username");
						String password = data.getString("password");
							
						if (null != certificate && !"".equals(certificate) && null != user && !"".equals(user)
							&& null != password && !"".equals(password)) {
							secretsAuth.setCertificate(certificate);
							secretsAuth.setUser(user);
							secretsAuth.setPassword(password);	
						} else {
							throw new Exception("Error on Secrets Auth Data");
						}
					} else {
						throw new Exception("Error on Secrets Auth Data");
					}
				} else {
					throw new Exception("Error on Secrets Auth Data");
				}
			} else {
				throw new Exception("Error Calling Secrets Auth Service");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return secretsAuth;
	}

	public SecretsAuth getAuthSecrets() {
		return this.secretsAuth;
	}

}
