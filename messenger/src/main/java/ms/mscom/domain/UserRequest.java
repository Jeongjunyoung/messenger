package ms.mscom.domain;

import java.sql.Timestamp;

public class UserRequest {
	private String request_id;
	private String response_id;
	private Timestamp date;
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getResponse_id() {
		return response_id;
	}
	public void setResponse_id(String response_id) {
		this.response_id = response_id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
