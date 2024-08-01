package fa.training.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import fa.training.dao.CinemaRoomDAO;
import fa.training.dao.CinemaRoomDAOImpl;
import fa.training.dao.CinemaRoomDetailDAO;
import fa.training.dao.CinemaRoomDetailDAOImpl;
import fa.training.entities.CinemaRoomDetail;

public class CinemaRoomDetailApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CinemaRoomDetailDAO cinemaRoomDetailDAO = new CinemaRoomDetailDAOImpl();
		
		try {
			System.out.println("-------get all-----");
			List<CinemaRoomDetail> cinemaRoomDetails = cinemaRoomDetailDAO.getAllCinemaRoomDetails();
			for (CinemaRoomDetail cinemaRoomDetail : cinemaRoomDetails) {
				System.out.println(cinemaRoomDetail.toString());
			}
			scanner.nextLine();
			
			System.out.println("-------get by id-----");
			CinemaRoomDetail finCinemaRoomDetail = cinemaRoomDetailDAO.getCinemaRoomDetailByID(1);
			System.out.println(finCinemaRoomDetail.toString());
			scanner.nextLine();
			
			System.out.println("-------update-----");
			CinemaRoomDetail updateCinemaRoomDetail = cinemaRoomDetailDAO.getCinemaRoomDetailByID(2);
			updateCinemaRoomDetail.setActiveDate(LocalDate.now());
			cinemaRoomDetailDAO.updateCinemaRoomDetailByID(updateCinemaRoomDetail.getCinemaRoomDetailId(), updateCinemaRoomDetail);
			scanner.nextLine();
			
			System.out.println("-------insert-----");
			CinemaRoomDetail newCinemaRoomDetail = new CinemaRoomDetail();
			newCinemaRoomDetail.setActiveDate(LocalDate.of(2003, 10, 14));
			newCinemaRoomDetail.setRoomDescription("mota");
			newCinemaRoomDetail.setRoomRate(5);
			cinemaRoomDetailDAO.insertCinemaRoomDetail(newCinemaRoomDetail);
			scanner.nextLine();
			
			System.out.println("-------delete-----");
			cinemaRoomDetailDAO.deleteCinemaRoomDetailByID(10);
			
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
