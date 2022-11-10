package controller;

import java.util.List;

import model.Flight;
import repositary.DataBase;
import view.CreateFlightView;

public class CreateFlightController {
	private CreateFlightView createFlightView;

	public CreateFlightController(CreateFlightView createFlightView) {
		this.createFlightView = createFlightView;
	}
	public void createNewFlight(Flight flight) {
		//flight creating logics
		DataBase.getInstance().setFlightList(flight);
		createFlightView.flightCreated("Flight Added Successfully");
	}
	public List<String> addRoutes(String routes, List<String> routesList) {
		routesList.add(routes);
		return routesList;
	}
	public int getNewFlightId()
	{
		return DataBase.getInstance().getFlightList().size()+1;
	}
}
