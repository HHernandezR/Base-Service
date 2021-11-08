package app.keepcash.base.model.secrets;

import java.io.Serializable;
import java.util.Map;

public class SecretsResponse implements Serializable {

	private static final long serialVersionUID = 6546637462047041577L;
	
	private Boolean success;
	private Map<String, String> data;
	private String message;

	public SecretsResponse() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SecretsResponse(Boolean success, Map<String, String> data, String message) {
		super();
		this.success = success;
		this.data = data;
		this.message = message;
	}

	@Override
	public String toString() {
		return "SecretsAuthResponse [success=" + success + ", data=" + data + ", message=" + message + "]";
	}

}
