package fa.training.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "CINEMA_ROOM")
public class CinemaRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CINEMA_ROOM_ID")
	private int cinemaRoomId;
	
	@Column(name = "CINEMA_ROOM_NAME")
	private String cinemaRoomName;
	
	@Column(name = "SEAT_QUANTITY")
	private int seatQuantity;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaRoom", fetch = FetchType.EAGER)
	private Set<Seat> seats;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "cinemaRoom", fetch = FetchType.EAGER)
	private CinemaRoomDetail cinemaRoomDetail;
	
	public CinemaRoom() {
		super();
	}

	public CinemaRoom(int cinemaRoomId, String cinemaRoomName, int seatQuantity, Set<Seat> seats,
			CinemaRoomDetail cinemaRoomDetail) {
		super();
		this.cinemaRoomId = cinemaRoomId;
		this.cinemaRoomName = cinemaRoomName;
		this.seatQuantity = seatQuantity;
		this.seats = seats;
		this.cinemaRoomDetail = cinemaRoomDetail;
	}

	public int getCinemaRoomId() {
		return cinemaRoomId;
	}
	public void setCinemaRoomId(int cinemaRoomId) {
		this.cinemaRoomId = cinemaRoomId;
	}
	public String getCinemaRoomName() {
		return cinemaRoomName;
	}
	public void setCinemaRoomName(String cinemaRoomName) {
		this.cinemaRoomName = cinemaRoomName;
	}
	public int getSeatQuantity() {
		return seatQuantity;
	}
	public void setSeatQuantity(int seatQuantity) {
		this.seatQuantity = seatQuantity;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		seats.forEach(seat -> seat.setCinemaRoom(this));
		this.seats = seats;
	}

	public CinemaRoomDetail getCinemaRoomDetail() {
		return cinemaRoomDetail;
	}
	public void setCinemaRoomDetail(CinemaRoomDetail cinemaRoomDetail) {
		cinemaRoomDetail.setCinemaRoom(this);
		this.cinemaRoomDetail = cinemaRoomDetail;
	}
	@Override
	public String toString() {
		return "CinemaRoom [cinemaRoomId=" + cinemaRoomId + ", cinemaRoomName=" + cinemaRoomName + ", seatQuantity="
				+ seatQuantity + "]";
	}
}
