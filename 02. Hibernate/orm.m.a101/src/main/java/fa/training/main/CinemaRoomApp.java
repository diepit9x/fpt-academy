package fa.training.main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;

import fa.training.dao.CinemaRoomDAO;
import fa.training.dao.CinemaRoomDAOImpl;
import fa.training.entities.CinemaRoom;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class CinemaRoomApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CinemaRoomDAO cinemaRoomDAO = new CinemaRoomDAOImpl();
		
		try {
			
			System.out.println("--------GET ALL----------");
			List<CinemaRoom> conCinemaRooms = cinemaRoomDAO.getAllCinemaRooms();
			for (CinemaRoom cinemaRoom : conCinemaRooms) {
				System.out.println(cinemaRoom.toString());
			}
			scanner.nextLine();
			
			System.out.println("--------GET BY ID----------");
			CinemaRoom findCinemaRoom = cinemaRoomDAO.getCinemaRoomByID(1);
			System.out.println(findCinemaRoom.toString());
			scanner.nextLine();
			
			System.out.println("--------UPDATE----------");
			CinemaRoom updateCinemaRoom = cinemaRoomDAO.getCinemaRoomByID(3);
			updateCinemaRoom.setCinemaRoomName("room3 update");
			cinemaRoomDAO.updateCinemaRoomByID(updateCinemaRoom.getCinemaRoomId(), updateCinemaRoom);
			scanner.nextLine();
			
			System.out.println("--------Insert----------");
			CinemaRoom newCinemaRoom = new CinemaRoom();
			newCinemaRoom.setCinemaRoomName("new rom");
			newCinemaRoom.setSeatQuantity(12);
			cinemaRoomDAO.insertCinemaRoom(newCinemaRoom);
			
			scanner.nextLine();
			
			System.out.println("--------DELETE----------");
			cinemaRoomDAO.deleteCinemaRoomByID(5);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		} catch (DataAlreadyExistException e) {
			e.printStackTrace();
		}
		
		
	}
}
