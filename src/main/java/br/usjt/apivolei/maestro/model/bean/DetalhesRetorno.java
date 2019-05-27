package br.usjt.apivolei.maestro.model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DetalhesRetorno {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String timestamp;
	private String message;
	private String details;
	
	public DetalhesRetorno() {
	}

	public DetalhesRetorno build(Date timestamp, String message, String details) {
		setTimestamp(timestamp);
		setMessage(message);
		setDetails(details);
		return this;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = sdf.format(timestamp);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "DetalhesErro [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
}