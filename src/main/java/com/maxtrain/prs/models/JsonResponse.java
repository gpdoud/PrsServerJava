package com.maxtrain.prs.models;

public class JsonResponse {
    private int code;
    private String message;
    private Object data;
    private Object error;
    // constructors with fields
	public JsonResponse(int code, String message, Object data, Object error) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.error = error;
	}
    public JsonResponse(Iterable<User> findAll) {
		this.code = 200;
		this.message = "Ok";
		this.data = findAll;
		this.error = null;
	}
	public JsonResponse(Object object) {
		this.code = 200;
		this.message = "Ok";
		this.data = object;
		this.error = null;
	}
	// getters & setters
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
	
}
