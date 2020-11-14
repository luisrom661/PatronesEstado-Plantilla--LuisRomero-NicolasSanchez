package co.unicauca.domicilios.infra;

/**
 * @author Luis Romero, Jose Nicolas Santander
 */
public class JsonError {
	
	/**
	 * Error code
	 */
	private String code;
	private String error;
	private String message;
	
	public JsonError() {
	}
	
	public JsonError(String code, String error, String message) {
		this.code = code;
		this.error = error;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
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
	
}
