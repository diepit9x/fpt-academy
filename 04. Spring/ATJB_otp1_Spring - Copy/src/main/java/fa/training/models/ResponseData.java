package fa.training.models;

public class ResponseData {
	private int status;
	private Object object;
	public ResponseData() {
		super();
	}
	public ResponseData(int status, Object object) {
		super();
		this.status = status;
		this.object = object;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
