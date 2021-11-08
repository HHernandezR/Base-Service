package app.keepcash.base.model;

import java.io.Serializable;
import java.util.List;

public class ErrorsResponse implements Serializable {

	private static final long serialVersionUID = -7652820171467594472L;
	
	private List<String> errors;

	public List<String> getErrors() {
		return errors;
	}

	public ErrorsResponse() {
		super();
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorsResponse(List<String> errors) {
		super();
		this.errors = errors;
	}

}
