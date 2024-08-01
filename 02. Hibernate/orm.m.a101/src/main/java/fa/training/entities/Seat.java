package fa.training.entities;

import java.util.Objects;

import fa.training.exception.IncorrectFormatException;
import fa.training.util.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "SEAT")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEAT_ID")
	private int seatId;
	
	@ManyToOne
	@JoinColumn(name = "CINEMA_ROOM_ID")
	private CinemaRoom cinemaRoom;
	
	@Column(name = "SEAT_COLUMN")
	private String seatColumn;
	
	@Column(name = "SEAT_ROW")
	private int seatRow;
	
	@Column(name = "SEAT_STATUS")
	private String seatStatus;
	
	@Column(name = "SEAT_TYPE")
	private String seatType;

	public Seat() {
		super();
	}

	public Seat(int seatId, CinemaRoom cinemaRoom, String seatColumn, int seatRow, String seatStatus, String seatType) throws IncorrectFormatException {
		super();
		this.seatId = seatId;
		this.cinemaRoom = cinemaRoom;
		this.seatColumn = seatColumn;
		this.seatRow = seatRow;
		setSeatStatus(seatStatus);
		setSeatType(seatType);
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public CinemaRoom getCinemaRoom() {
		return cinemaRoom;
	}

	public void setCinemaRoom(CinemaRoom cinemaRoom) {
		this.cinemaRoom = cinemaRoom;
	}

	public String getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(String seatColumn) {
		this.seatColumn = seatColumn;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) throws IncorrectFormatException {
		if (Validator.isSeatStatus(seatStatus)) {
			this.seatStatus = seatStatus;
			return;
		}
		throw new IncorrectFormatException("Seat status is incorrect format");
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) throws IncorrectFormatException {
		if (Validator.isSeatType(seatType)) {
			this.seatType = seatType;
			return;
		}
		throw new IncorrectFormatException("Seat type is incorrect format");
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", cinemaRoom=" + cinemaRoom + ", seatColumn=" + seatColumn + ", seatRow="
				+ seatRow + ", seatStatus=" + seatStatus + ", seatType=" + seatType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(seatColumn, seatId, seatRow, seatStatus, seatType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(seatColumn, other.seatColumn) && seatId == other.seatId && seatRow == other.seatRow
				&& Objects.equals(seatStatus, other.seatStatus) && Objects.equals(seatType, other.seatType);
	}
	
	
}
