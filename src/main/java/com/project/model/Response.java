package com.project.model;

public class Response {
	private String data;

	public Response(){

	}

	public Response(String status, String data){
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
