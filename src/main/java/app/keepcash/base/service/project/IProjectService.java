package app.keepcash.base.service.project;

import org.springframework.http.ResponseEntity;

import app.keepcash.base.model.Response;
import app.keepcash.base.model.project.Request;

public interface IProjectService {
	
	public ResponseEntity<Response> test(Request request);

}
