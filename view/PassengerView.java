package view;

import java.util.Scanner;

import controller.UserController;

public class PassengerView 
{
	private Scanner scanner = new Scanner(System.in);
	private UserController userController;
	public PassengerView()
	{
		userController = new UserController(this);
	}
	
	protected void mainMenu() 
	{
		System.out.println("1-Booking\n2-Get PNR status\n3-Booked tickets\n4-Cancel Tickets,5-Search"
				+"passenger\n6-Change ticket status of a passenger\n7-List flight routes\n8-Add flight");
		System.out.println("Enter your option");
		String opt = scanner.next();
		switch(opt)
		{
			case "1":
				BookTicketView book = new BookTicketView();
				book.bookTicket();
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				break;
			case "7":
				break;
			case "8":
				break;
			default:
				System.out.println("Enter proper input...");
		}
	}

}
