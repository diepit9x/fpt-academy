package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "CINEMA_ROOM_DETAIL")
public class CinemaRoomDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CINEMA_ROOM_DETAIL_ID")
	private int cinemaRoomDetailId;
	
	@OneToOne
	@JoinColumn(name = "CINEMA_ROOM_ID")
	private CinemaRoom cinemaRoom;
	
	@Column(name = "ROOM_RATE")
	private int roomRate;
	
	@Column(name = "ACTIVE_DATE")
	private LocalDate activeDate;
	
	@Column(name = "ROOM_DESCRIPTION")
	private String roomDescription;

	public CinemaRoomDetail() {
		super();
	}

	public CinemaRoomDetail(int cinemaRoomDetailId, CinemaRoom cinemaRoom, int roomRate, LocalDate activeDate,
			String roomDescription) {
		super();
		this.cinemaRoomDetailId = cinemaRoomDetailId;
		this.cinemaRoom = cinemaRoom;
		this.roomRate = roomRate;
		this.activeDate = activeDate;
		this.roomDescription = roomDescription;
	}

	public int getCinemaRoomDetailId() {
		return cinemaRoomDetailId;
	}

	public void setCinemaRoomDetailId(int cinemaRoomDetailId) {
		this.cinemaRoomDetailId = cinemaRoomDetailId;
	}

	public CinemaRoom getCinemaRoom() {
		return cinemaRoom;
	}

	public void setCinemaRoom(CinemaRoom cinemaRoom) {
		this.cinemaRoom = cinemaRoom;
	}

	public int getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(int roomRate) {
		this.roomRate = roomRate;
	}

	public LocalDate getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(LocalDate activeDate) {
		this.activeDate = activeDate;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	@Override
	public String toString() {
		return "CinemaRoomDetail [cinemaRoomDetailId=" + cinemaRoomDetailId + ", cinemaRoom=" + cinemaRoom
				+ ", roomRate=" + roomRate + ", activeDate=" + activeDate + ", roomDescription=" + roomDescription
				+ "]";
	}
	
	
}
