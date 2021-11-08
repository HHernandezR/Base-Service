package app.keepcash.base.model;

import java.io.Serializable;
import java.util.Date;

public class ServiceInfo implements Serializable {

	private static final long serialVersionUID = -5289342636823215360L;
	
	private String service;
	private String version;
	private Date date;
	private Integer port;

	public ServiceInfo() {
		super();
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	public ServiceInfo(String service, String version, Date date, Integer port) {
		super();
		this.service = service;
		this.version = version;
		this.date = date;
		this.port = port;
	}

}
