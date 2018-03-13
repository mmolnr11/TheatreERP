package com.project.model;

public class Response {
	private String status;
	private String data;
	
	public Response(){
		
	}
	
	public Response(String status, String data){
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
