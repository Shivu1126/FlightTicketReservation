package repositary;

import java.util.ArrayList;
import java.util.List;

import model.Flight;
import model.Passenger;

public class DataBase
{
	private static DataBase database;
	private List<Flight> flightList;
	private List<Passenger> passengerList;
	DataBase() {
		flightList = new ArrayList<>();
	}
	public static DataBase getInstance()
	{
		if(database==null)
			database = new DataBase();
		return database;		
	}

	public List<Flight> getFlightList() {
		return flightList;
	}
	public void setFlightList(Flight flightList) {
		this.flightList.add(flightList);
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
}