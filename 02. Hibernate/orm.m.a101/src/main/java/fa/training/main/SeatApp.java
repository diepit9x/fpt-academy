package fa.training.main;

import java.util.List;
import java.util.Scanner;

import fa.training.dao.SeatDAO;
import fa.training.dao.SeatDAOImpl;
import fa.training.entities.Seat;

public class SeatApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SeatDAO seatDAO = new SeatDAOImpl();
		
		try {
			System.out.println("--------------GET ALL SEATS----------------");
			List<Seat> seats = seatDAO.getAllSeats();
			for (Seat seat : seats) {
				System.out.println(seat.toString());
			}
			scanner.nextLine();

			System.out.println("--------------GET A SEAT BY ID----------------");
			Seat findSeat = seatDAO.getSeatByID(1);
			System.out.println(findSeat.toString());
			scanner.nextLine();
			
			System.out.println("--------------UPDATE SEAT BY ID ----------------");
			
			Seat updateSeat = seatDAO.getSeatByID(2);
			updateSeat.setSeatType("Normal"); //VIP or Normal
			seatDAO.updateSeatByID(updateSeat.getSeatId(), updateSeat);
			
			scanner.nextLine();
			
			System.out.println("--------------INSERT ----------------");
			
			//insert
			Seat newSeat = new Seat();
			newSeat.setSeatId(10);
			newSeat.setSeatColumn("X");
	    	newSeat.setSeatRow(2);
	    	newSeat.setSeatStatus("Available");
	    	newSeat.setSeatType("VIP");
	    	
	    	seatDAO.insertSeat(newSeat);
	    	
	    	scanner.nextLine();
	    	
	    	System.out.println("--------------DELETE ----------------");
	    	seatDAO.deleteSeatByID(10);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		
	}
}
