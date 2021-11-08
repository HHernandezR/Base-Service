package app.keepcash.base.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.keepcash.base.model.ErrorsResponse;
import app.keepcash.base.model.Response;
import app.keepcash.base.model.ServiceInfo;
import app.keepcash.base.model.project.Request;
import app.keepcash.base.service.IService;
import app.keepcash.base.service.project.IProjectService;

@RestController
@RequestMapping(path = "/base")
public class ApplicationController {
	
	@Autowired
	private IService service;
	
	@Autowired
	private IProjectService projectService;
	
	@GetMapping(path = "/")
	public ResponseEntity<ServiceInfo> ping() {
		return this.service.ping();
	}
	
	@PostMapping(path = "/test")
	public ResponseEntity<Response> createKeys(@Valid @RequestBody Request request, BindingResult bindingResult) {
		ResponseEntity<Response> responseEntity = null;
		if (bindingResult.hasErrors()) {
			ErrorsResponse errorsResponse = this.service.getRequestErrors(bindingResult);
			responseEntity = this.service.validateErrors(errorsResponse);
		} else {
			responseEntity = this.projectService.test(request);
		}
		return responseEntity;
	}

}
