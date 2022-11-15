package controller;

import java.util.List;

import model.*;
import repositary.DataBase;
import view.PassengerView;

public class PassengerController
{
	private PassengerView userView;
	public PassengerController(PassengerView userView) {
		this.userView = userView;
	}
	
	public Passenger getPassengerDetail(int pnr) 
	{
		Passenger passenger=null;
		try
		{
			passenger =  DataBase.getInstance().getPassengerList().get(pnr-1);
		}
		catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			userView.alertMsg("Please check PNR number");
		}
		return passenger;
	}

	public Flight getFlightDetail(int flightId) {

		Flight flight = null;
		try
		{
			flight = DataBase.getInstance().getFlightList().get(flightId-1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			userView.alertMsg("Please check FLight Id ");
		}
		return flight;
	}

	public List<Passenger> getBookedTickets() {
		return DataBase.getInstance().getPassengerList();
	}

	public List<Flight> getFlightDetails() {
		return DataBase.getInstance().getFlightList();
	}

	public boolean adminCheck(String name, String pass) {
		if(name.equals("Shiva") && pass.equals("Shiv123"))
			return true;
		return false;
	}

	public boolean changeSeatAvailable(int flightId, int ticketCount, boolean b) {

		List<Flight> flight = DataBase.getInstance().getFlightList();
		for(int i=0;i<flight.size();i++)
		{
			if(flightId==flight.get(i).getFlightId())
			{
				if(b)
					flight.get(i).setSeats(flight.get(i).getSeats()+ticketCount);
				else
					flight.get(i).setSeats(flight.get(i).getSeats()-ticketCount);

				return true;
			}
		}
		return false;
	}
}
