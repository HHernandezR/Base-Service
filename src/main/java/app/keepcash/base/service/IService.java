package app.keepcash.base.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import app.keepcash.base.model.ErrorsResponse;
import app.keepcash.base.model.ServiceInfo;

public interface IService {
	
	public ResponseEntity<ServiceInfo> ping();
	public ErrorsResponse getRequestErrors(BindingResult bindingResult);
	public <T> ResponseEntity<T> validateErrors(ErrorsResponse errorsResponse);

}
