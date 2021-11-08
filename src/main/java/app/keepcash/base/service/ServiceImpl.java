package app.keepcash.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import app.keepcash.base.model.ErrorsResponse;
import app.keepcash.base.model.Response;
import app.keepcash.base.model.ServiceInfo;

@Service
public class ServiceImpl implements IService {
	
private final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);
	
	@Value("${spring.name}")
	private String serviceName;
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private Environment environment;

	@Override
	public ResponseEntity<ServiceInfo> ping() {
		ResponseEntity<ServiceInfo> responseEntity = null;
		try {
			String message = this.serviceName.concat(" Available");
			String versionResp = this.version;
			Integer port = Integer.parseInt(environment.getProperty("local.server.port"));
			ServiceInfo serviceInfo = new ServiceInfo(message, versionResp, new Date(), port);
			responseEntity = new ResponseEntity<ServiceInfo>(serviceInfo, HttpStatus.OK);	
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			responseEntity = new ResponseEntity<ServiceInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@Override
	public ErrorsResponse getRequestErrors(BindingResult bindingResult) {
		ErrorsResponse errorsResponse = new ErrorsResponse();
		List<String> errors = new ArrayList<String>();
		try {
			bindingResult.getFieldErrors().forEach(error -> {
				errors.add(error.getDefaultMessage());
			});
			errorsResponse.setErrors(errors);	
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return errorsResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> ResponseEntity<T> validateErrors(ErrorsResponse errorsResponse) {
		ResponseEntity<T> responseEntity = null;
		try {
			if (!errorsResponse.getErrors().isEmpty()) {
				Response response = new Response();
				response.setErrors(errorsResponse.getErrors());
				responseEntity = new ResponseEntity<T>((T) response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			responseEntity = new ResponseEntity<T>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
