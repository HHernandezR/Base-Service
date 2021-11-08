package app.keepcash.base.model.secrets;

import java.io.Serializable;

public class SecretsAuth implements Serializable {

	private static final long serialVersionUID = -1425842013085199193L;

	private String certificate;
	private String user;
	private String password;

	public SecretsAuth() {
		super();
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SecretsAuth(String certificate, String user, String password) {
		super();
		this.certificate = certificate;
		this.user = user;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Secrets [certificate=" + certificate + ", user=" + user + ", password=" + password + "]";
	}

}
