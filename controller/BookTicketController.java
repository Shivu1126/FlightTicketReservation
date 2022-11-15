package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Flight;
import model.Passenger;
import repositary.DataBase;
import view.BookTicketView;

public class BookTicketController {
	private BookTicketView bookTicketView;
	public BookTicketController(BookTicketView bookTicketView) {
		this.bookTicketView = bookTicketView;
	}
	
	public void ticketBooker(Passenger passenger) 
	{
		DataBase.getInstance().setPassengerList(passenger);
		bookTicketView.alertMsg("Booked successfully...You can see your ticket details on ticket tab...");
	}

	public Map<Flight, Integer> checkAvailableFlight(String from, String to) 
	{
		Map<Flight, Integer> flightDet = new HashMap<>();
		List<Flight> flightList = DataBase.getInstance().getFlightList();
		if(flightList.isEmpty())
		{
			return flightDet;
		}
		for(int i=0;i<flightList.size();i++)
		{
			Flight flight = flightList.get(i);
			List<String> sourceAndDestination = flight.getRoutes();
	
			int precentage = 0;
			for(int j=0;j<sourceAndDestination.size();j++)
			{
				if(from.equals(sourceAndDestination.get(j)))
				{
					for(int k=j+1;k<sourceAndDestination.size();k++)
					{
						precentage++;
						if(to.equals(sourceAndDestination.get(k)))
						{
							precentage = precentage*10;	
							int price = flight.getTicketFare();
							int fare = price - (price * precentage/100);		
							flightDet.put(flight, fare);
						}
					}
				}
			}
		}
		return flightDet;
	}
	public int checkFlightId(String pickFlight, Map<Flight, Integer> availableFlight) {

		for(Map.Entry<Flight, Integer> map: availableFlight.entrySet() )
		{
			int id = map.getKey().getFlightId();
			if(Integer.toString(id).equals(pickFlight))
			{
				return map.getKey().getFlightId();
			}
		}

		return 0;
	}

	public boolean checkAvailableSeat(int noOfPassenger, int flightId) {
		// TODO Auto-generated method stub
		List<Flight> flight = DataBase.getInstance().getFlightList();
		for(int i=0;i<flight.size();i++)
		{
			if(flightId==flight.get(i).getFlightId() && flight.get(i).getSeats()>=noOfPassenger)
			{
				flight.get(i).setSeats(flight.get(i).getSeats()-noOfPassenger);
				return true;
			}
		}
		return false;
	}

	public int getNewPassengerPnr() {
		return DataBase.getInstance().getPassengerList().size()+1;
	}
}
