package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Flight;
import repositary.DataBase;
import view.BookTicketView;

public class BookTicketController {
	private BookTicketView bookTicketView;
	public BookTicketController(BookTicketView bookTicketView) {
		this.bookTicketView = bookTicketView;
	}
	
	public void ticketBooker() 
	{
		
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
			int temp=0;
			int precentage = 0;
			for(int j=0;j<sourceAndDestination.size();j++)
			{
				if(from.equals(sourceAndDestination.get(i)))
				{
					for(int k=j;k<sourceAndDestination.size();k++)
					{
						precentage++;
						if(to.equals(sourceAndDestination.get(j)))
						{
							temp=1;
							break;
						}
					}
				}
				if(temp!=0)
				{			
					break;
				}
			}
			if(temp!=0)
			{
				precentage = precentage*10;	
				int price = flight.getTicketFare();
				int fare = price - (price * precentage/100);		
				flightDet.put(flight, fare);
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
}