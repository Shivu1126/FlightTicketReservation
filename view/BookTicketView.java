package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.BookTicketController;
import model.Flight;
import model.Passenger;

public class BookTicketView 
{
	private Scanner scanner = new Scanner(System.in);
	private BookTicketController bookTicketContoller;
	public BookTicketView() {
		bookTicketContoller = new BookTicketController(this);
	}

	protected void bookTicket()
	{
		System.out.println("##Plan my journey##");
		System.out.println("Enter from location..");
		String from = scanner.next();
		System.out.println("Enter to location..");
		String to = scanner.next();
		HashMap<Integer,Integer> ticketFareCheck = new HashMap<>();
		Map<Flight, Integer> availableFlight= bookTicketContoller.checkAvailableFlight(from,to);
		if(availableFlight.isEmpty())
		{
			System.out.println("No flights for your destination..");
			return;
		}
		System.out.println("Available flights...");
		System.out.println("--------------------");
		for(Map.Entry<Flight, Integer> map: availableFlight.entrySet() )
		{
			ticketFareCheck.put(map.getKey().getFlightId(),map.getValue());
			System.out.println("Flight no :" +map.getKey().getFlightId()+"|| name : "+map.getKey().getFlightName()+" || Source :"+map.getKey().getSource()+" || Destination:1"+map.getKey().getDestination()+"\r\n"
					+ "|| Fare : "+ map.getValue() +" || seats :"+map.getKey().getSeats());
			System.out.println("Routes : || ");
			List<String> routes = map.getKey().getRoutes();
			for(int i=0;i<routes.size();i++)
			{
				System.out.print(routes.get(i));
				if(i!=routes.size()-1)
					System.out.print("->");
			}
		}
		int flightId;
		while(true)
		{
			System.out.println("Enter Flight id");
			String pickFlight = scanner.next();
			flightId = bookTicketContoller.checkFlightId(pickFlight,availableFlight);
			if(flightId!=0)
				break;
			else
				System.out.println("Enter prober flight id");
		}
		int fare = ticketFareCheck.get(flightId);
		System.out.println("Enter number of passenger");
		int noOfPassenger = scanner.nextInt();
		System.out.println("Enter passenger name");
		String name = scanner.next();
		System.out.println("Enter gender");
		String gender = scanner.next();
		System.out.println("Enter age");
		int age = scanner.nextInt();
		int pnr = bookTicketContoller.getNewPassengerPnr();
		String status;
		if(bookTicketContoller.checkAvailableSeat(noOfPassenger,flightId))
		{
			status = "CNF";
		}
		else
		{
			status = "WL";
		}
		Passenger passenger = new Passenger();
		passenger.setName(name);
		passenger.setPNR(pnr);
		passenger.setGender(gender);
		passenger.setAge(age);
		passenger.setTicketCount(noOfPassenger);
		passenger.setStatus(status);
		passenger.setFlightId(flightId);
		passenger.setTicketPrice(fare);
		bookTicketContoller.ticketBooker(passenger);
	}

	public void alertMsg(String alert) {
		System.out.println(alert);
	}
}
