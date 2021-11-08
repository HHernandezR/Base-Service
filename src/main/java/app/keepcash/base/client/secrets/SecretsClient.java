package app.keepcash.base.client.secrets;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import app.keepcash.base.model.secrets.SecretsResponse;

@FeignClient(contextId = "secretsContextId", value = "secretsClient", url = "${secrets.url}", configuration = FeignClientSecretsConfig.class)
public interface SecretsClient {
	
	@GetMapping(value = "${secrets.auth}")
	ResponseEntity<SecretsResponse> getAuthSecrets();

}
