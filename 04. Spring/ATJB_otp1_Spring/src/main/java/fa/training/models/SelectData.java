package fa.training.models;

public class SelectData {
	public static String trangThai(String status) {
		if (status == null) {
			return "null";
		}
		switch (status) {
		case "1":
			return "Rảnh";
		case "2":
			return "Bận";
		case "3":
			return "Đang sửa chữa";
		default:
			return "undefined";
		}
	}

	public static String donVi(String status) {
		if (status == null) {
			return "null";
		}
		switch (status) {
		case "1":
			return "Chai";
		case "2":
			return "Đĩa";
		case "3":
			return "Cái";
		case "4":
			return "Lon";
		default:
			return "undefined";
		}
	}
}
