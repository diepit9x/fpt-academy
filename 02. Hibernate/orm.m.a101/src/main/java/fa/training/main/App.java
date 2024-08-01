package fa.training.main;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fa.training.dao.CinemaRoomDAO;
import fa.training.dao.CinemaRoomDAOImpl;
import fa.training.dao.CinemaRoomDetailDAO;
import fa.training.dao.CinemaRoomDetailDAOImpl;
import fa.training.dao.SeatDAO;
import fa.training.dao.SeatDAOImpl;
import fa.training.entities.CinemaRoom;
import fa.training.entities.CinemaRoomDetail;
import fa.training.entities.Seat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SeatDAO seatDAO = new SeatDAOImpl();
        CinemaRoomDAO cinemaRoomDAO = new CinemaRoomDAOImpl();
        CinemaRoomDetailDAO cinemaRoomDetailDAO = new CinemaRoomDetailDAOImpl();
        try {
        	//seat
        	Seat seat1 = new Seat();
        	seat1.setSeatColumn("B");
        	seat1.setSeatRow(2);
        	seat1.setSeatStatus("Available");
        	seat1.setSeatType("VIP");
        	
        	Seat seat2 = new Seat();
        	seat2.setSeatColumn("A");
        	seat2.setSeatRow(10);
        	seat2.setSeatStatus("Available");
        	seat2.setSeatType("Normal");
        	
        	Set<Seat> seats = new HashSet<>();
        	seats.add(seat1);
        	seats.add(seat2);
        	
        	//cinema room detail
        	CinemaRoomDetail cinemaRoomDetail = new CinemaRoomDetail();
        	cinemaRoomDetail.setRoomRate(3);
        	cinemaRoomDetail.setActiveDate(LocalDate.now());
        	cinemaRoomDetail.setRoomDescription("descr");
        	
        	//cinema room
        	CinemaRoom cinemaRoom = new CinemaRoom();
        	cinemaRoom.setCinemaRoomName("room2");
        	cinemaRoom.setSeatQuantity(10);
        	cinemaRoom.setCinemaRoomDetail(cinemaRoomDetail);
        	cinemaRoom.setSeats(seats);
        	
        	
        	
//			cinemaRoomDAO.insertCinemaRoom(cinemaRoom);
        	
        	//add a seat to cinema room by id = 2
			
			Seat addSeat = new Seat();
			addSeat.setSeatColumn("C");
			addSeat.setSeatRow(2);
			addSeat.setSeatStatus("Available");
			addSeat.setSeatType("VIP");
			addSeat.setCinemaRoom(cinemaRoomDAO.getCinemaRoomByID(2));
			
			//seatDAO.insertSeat(addSeat);
        	
        	/* update */
			Seat existingSeat = seatDAO.getSeatByID(11);
			existingSeat.setSeatType("Normal");
			existingSeat.setCinemaRoom(cinemaRoomDAO.getCinemaRoomByID(1));
			seatDAO.updateSeatByID(existingSeat.getSeatId(), existingSeat);

        	
//        	List<Seat> seats2 = seatDAO.getAllSeats();
//        	for (Seat seat : seats2) {
//				System.out.println(seat.toString());
//			}
//        	
//        	List<CinemaRoomDetail> cinemaRoomDetails = cinemaRoomDetailDAO.getAllCinemaRoomDetails();
//        	for (CinemaRoomDetail cinemaRoomDetail2 : cinemaRoomDetails) {
//				System.out.println(cinemaRoomDetail2.toString());
//			}
//        	
//        	
//        	List<CinemaRoom> cinemaRooms = cinemaRoomDAO.getAllCinemaRooms();
//        	
//        	for (CinemaRoom cinemaRoom2 : cinemaRooms) {
//				System.out.println(cinemaRoom2.toString());
//			}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
