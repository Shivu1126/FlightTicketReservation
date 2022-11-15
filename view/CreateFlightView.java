package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.CreateFlightController;
import model.Flight;

public class CreateFlightView 
{
	private Scanner scanner = new Scanner(System.in);
	private Scanner scanStr = new Scanner(System.in);
	private CreateFlightController createFlightController;
	public CreateFlightView() {
		createFlightController = new CreateFlightController(this);
	}

	public static void main(String[] args) {
		CreateFlightView startPro = new CreateFlightView();
		startPro.init();
	}
	
	protected void init() {
		
		System.out.println("##Flight creation##");
		
		while(true)
		{
			List<String> routesList = new ArrayList<>();
			int flightId = createFlightController.getNewFlightId();
			System.out.println("Enter flight name");
			String flightName = scanner.next();
			System.out.println("Enter source");
			String source = scanner.next();
			createFlightController.addRoutes(source, routesList);
			System.out.println("Enter how many routes");
			int routesCount = scanner.nextInt();
			System.out.println("Enter routes");
			for(int i=0;i<routesCount;i++)
			{
				String routes = scanStr.nextLine();
				createFlightController.addRoutes(routes,routesList);
			}	
			System.out.println("Enter destination");
			String destination = scanner.next();
			createFlightController.addRoutes(destination, routesList);
			System.out.println("Enter ticketFare");
			int ticketFare = scanner.nextInt();
			System.out.println("Enter date and time");
			String dateTime = scanStr.nextLine();
			System.out.println("Enter available seats");
			int seats = scanner.nextInt();
			
			System.out.println(routesList);
			
			Flight flight = new Flight();
			flight.setFlightId(flightId);
			flight.setFlightName(flightName);
			flight.setSource(source);
			flight.setDestination(destination);
			flight.setTicketFare(ticketFare);
			flight.setDate(dateTime);
			flight.setRoutes(routesList);
			flight.setSeats(seats);
			createFlightController.createNewFlight(flight);
			System.out.println("If you want add more flight..press 1..");
			String stop = scanner.next();
			if(!stop.equals("1"))
				break;
		}
		System.out.println("--Welcome to Flight Ticket Booking--");
		
		PassengerView passengerView = new PassengerView();
		passengerView.mainMenu();
		
	}
	public void flightCreated(String alert) {
		System.out.println(alert);
	}
}
