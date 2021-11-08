package app.keepcash.base.client;

import java.io.Serializable;

public class ExceptionMessage implements Serializable {

	private static final long serialVersionUID = 582705594477799004L;
	
	private Boolean success;
	private String timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ExceptionMessage() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ExceptionMessage(Boolean success, String timestamp, int status, String error, String message, String path) {
		super();
		this.success = success;
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	@Override
	public String toString() {
		return "ExceptionMessage [success=" + success + ", timestamp=" + timestamp + ", status=" + status + ", error="
				+ error + ", message=" + message + ", path=" + path + "]";
	}

}
