package app.keepcash.base.service.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.keepcash.base.model.Response;
import app.keepcash.base.model.project.Request;

@Service
public class ProjectServiceImpl implements IProjectService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Override
	public ResponseEntity<Response> test(Request request) {
		LOGGER.info("----- STARTING TEST -----");
		LOGGER.info("idUser : " + request.getIdUser());
		ResponseEntity<Response> responseEntity = null;
		try {
			Map<String, String> info = new HashMap<String, String>();
			info.put("idCard", String.valueOf(request.getIdUser()));
			Response response = new Response();
			response.setSuccess(Boolean.TRUE);
			response.setData(info);
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			String error = "Error on test";
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			Response response = new Response();
			response.setSuccess(Boolean.FALSE);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
