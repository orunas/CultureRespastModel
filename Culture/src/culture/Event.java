package culture;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;


public class Event {
	private ContinuousSpace<Object> space ;
	private Grid<Object> grid ;
	private Network<Object> network;
	private String id;
	private int[] traits;
	
	public Event(ContinuousSpace<Object> space , Grid<Object> grid,  Network<Object> network, String id){
		this.space = space ;
		this.grid = grid ;
		this.network = network;
		this.id = id;
	}
	
	@ScheduledMethod ( start = 1, interval = 1)
	public void Step(){
	
	}
	
	public NdPoint getLocation(){
		return this.space.getLocation(this);
	}
	
	
	public String getId() {
		return this.id;
	}

}
