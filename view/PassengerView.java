package view;

import java.util.List;
import java.util.Scanner;
import controller.PassengerController;
import model.*;

public class PassengerView 
{
	private Scanner scanner = new Scanner(System.in);
	private PassengerController passengerController;
	public PassengerView()
	{
		passengerController = new PassengerController(this);
	}

	protected void mainMenu() 
	{
		while(true)
		{
			System.out.println("1-Booking\n2-Get PNR status\n3-Booked tickets\n4-Cancel Tickets"
					+"\n5-Change ticket status of a passenger\n6-List flight routes\n7-Add flight\n8-Exit");
			System.out.println("Enter your option");
			String opt = scanner.next();
			switch(opt)
			{
			case "1":
				BookTicketView book = new BookTicketView();
				book.bookTicket();
				break;
			case "2":
				getStatus();
				break;
			case "3":
				showBookedTickets();
				break;
			case "4":
				cancelTicket();
				break;
			case "5":
				changeStatus();
				break;
			case "6":
				listOfRoutse();
				break;
			case "7":
				CreateFlightView flightCreation = new CreateFlightView();
				flightCreation.init();
				break;
			case "8":
				System.out.println("ThankYou..");
				System.exit(0);
			default:
				System.out.println("Enter proper input...");
			}
		}
	}

	private void getStatus() {
		System.out.println("Enter PNR..");
		int pnr = scanner.nextInt();
		Passenger passenger = passengerController.getPassengerDetail(pnr);
		if(passenger==null)
		{
			System.out.println("You didn't booked ticket..");
			return;
		}
		int flightId = passenger.getFlightId();
		Flight flightDetail = passengerController.getFlightDetail(flightId);
		if(flightDetail==null)
		{
			System.out.println("You didn't booked ticket..");
			return;
		}
		System.out.println("Flight detail\n-----------------");
		System.out.println("FlightID : "+flightDetail.getFlightId());
		System.out.println("FlightName:"+flightDetail.getFlightName());
		System.out.println("Date :"+flightDetail.getDate());
		System.out.println("Source:"+flightDetail.getSource());
		System.out.println("Destination:"+flightDetail.getDestination());
		System.out.println("Seats:"+flightDetail.getSeats());
		System.out.println("TicketFare:"+flightDetail.getTicketFare());
		System.out.println("Routes : ");
		for(String routes: flightDetail.getRoutes())
		{
			System.out.println(" ->"+routes);
		}
		System.out.println("--------------------");
		System.out.println("Your Detail\n-------------------");
		System.out.println("Name:"+passenger.getName());
		System.out.println("Age :"+passenger.getAge());
		System.out.println("Gender :"+passenger.getGender());
		System.out.println("PNR :"+passenger.getPNR());
		System.out.println("Status:"+passenger.getStatus());
		System.out.println("Ticker Count:"+passenger.getTicketCount());
		System.out.println("Ticket price:"+passenger.getTicketPrice());
		System.out.println("Total price:"+passenger.getTicketCount()*passenger.getTicketPrice());
		System.out.println("----------------------");

	}

	private void showBookedTickets()
	{
		List<Passenger> bookedTickets = passengerController.getBookedTickets();
		if (bookedTickets.isEmpty()) {
			System.out.println("No one booked ticket..");
			return;
		}
		System.out.println("Booked Tickets Details...");
		for(Passenger passenger : bookedTickets)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Passenger Name :"+passenger.getName());
			System.out.println("Age:"+passenger.getAge());
			System.out.println("Gender :"+passenger.getGender());
			System.out.println("PNR :"+passenger.getPNR());
			System.out.println("Their flight Id:"+passenger.getFlightId());
			System.out.println("TicketCount:"+passenger.getTicketCount());
			System.out.println("Price:"+passenger.getTicketPrice());
			System.out.println("Status:"+passenger.getStatus());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
	private void listOfRoutse() {
		List<Flight> flightsRoutes = passengerController.getFlightDetails();
		for (Flight flight: flightsRoutes)
		{
			System.out.println("-----------------");

			System.out.println("Flight Id : "+flight.getFlightId());
			System.out.println("Routs : ");
			List<String> routes = flight.getRoutes();
			for (int i = 0; i < routes.size(); i++) 
			{
				System.out.print(routes.get(i));
				if(i!=routes.size()-1)
					System.out.print("->");
			}
			System.out.println("-----------------");
		}
	}

	private void cancelTicket() {
		System.out.println("Enter PNR number");
		int pnr = scanner.nextInt();
		Passenger passenger = passengerController.getPassengerDetail(pnr);
		if(passenger==null)
		{
			System.out.println("You didn't booked ticket..");
			return;
		}
		while(true)
		{	
			System.out.println("Do you want to cancel the ticket?\n1.Yes or 2.No");
			String opt = scanner.next();
			if(opt.equals("1"))
			{
				passenger.setStatus("CANCELLED");
				if(passengerController.changeSeatAvailable(passenger.getFlightId(),passenger.getTicketCount(),true))
					System.out.println("Ticket(s) Cancelled Successfully.Your refund "+ passenger.getTicketCount()*passenger.getTicketPrice() +"will be processed soon.\r\n");
				else
					System.out.println("Cancelled not successfully");
				break;
			}
			else if(opt.equals("2"))
			{
				System.out.println("Ok..Thankyou");
				break;
			}
			else
			{
				System.out.println("Enter proper input...");
			}
		}
	}
	private void changeStatus() {
		System.out.println("Enter admin name");
		String name = scanner.next();
		System.out.println("Enter password");
		String pass = scanner.next();

		if(!passengerController.adminCheck(name,pass))
		{
			System.out.println("User name or password incorrect");
			return;
		}

		System.out.println("Enter PNR number");
		int pnr = scanner.nextInt();
		Passenger passenger = passengerController.getPassengerDetail(pnr);
		if(passenger==null)
		{
			System.out.println("No one booked..");
			return;
		}
		while(true)
		{
			System.out.println("Enter status..\n1.CNF\n2.WL\n3.CANCELLED");
			String opt = scanner.next();
			if(opt.equals("1"))
			{
				passenger.setStatus("CNF");
				passengerController.changeSeatAvailable(passenger.getFlightId(),passenger.getTicketCount(),false);
				break;
			}
			else if(opt.equals("2"))
			{
				passenger.setStatus("WL");
				break;
			}
			else if(opt.equals("3"))
			{
				passenger.setStatus("CANCELLED");
				if(passengerController.changeSeatAvailable(passenger.getFlightId(),passenger.getTicketCount(),true))
					System.out.println("Ticket(s) Cancelled Successfully.Their refund "+ passenger.getTicketCount()*passenger.getTicketPrice() +"will be processed soon.\r\n");
				else
					System.out.println("Cancelled not successfully");
				break;
			}
			else
				System.out.println("Enter proper input..");
		}
	}

	public void alertMsg(String msg) {
		System.out.println(msg);
	}
}
