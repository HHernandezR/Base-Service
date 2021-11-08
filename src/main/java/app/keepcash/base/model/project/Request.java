package app.keepcash.base.model.project;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Request implements Serializable {

	private static final long serialVersionUID = -2529063852820106848L;

	@NotNull(message = "El campo idUser es requerido")
	@Size(min = 1, message = "El campo idUser es requerido")
	private String idUser;

	public Request() {
		super();
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Request [idUser=" + idUser + "]";
	}

}
