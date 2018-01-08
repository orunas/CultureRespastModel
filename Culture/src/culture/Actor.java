package culture;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;
import repast.simphony.space.graph.Network;
import repast.simphony.space.graph.RepastEdge;

public class Actor {
	
	private ContinuousSpace<Object> space ;
	private Grid<Object> grid ;
	private Network<Object> network;
	public static Double engagementRate;
	private int[] traits;
	private int age; //category
	private int gainings;  // category
	private int creativityIndex; //
	private int networkingIndex;
	private int trustAndCooperavityIndex;
	private int civicEngagement;
	
	public Actor(ContinuousSpace<Object> space , Grid<Object> grid, Network<Object> network){
		this.space = space ;
		this.grid = grid ;
		this.network = network;
	}
	
	@ScheduledMethod ( start = 1, interval = 1)
	public void Step(){
		GridPoint pt = grid.getLocation(this);
		Context context = ContextUtils.getContext (this);
		repast.simphony.query.InstanceOfQuery<Object> eventQuery = new repast.simphony.query.InstanceOfQuery<Object>(context,Event.class);
		Iterable<Object> iter = eventQuery.query();
		Event minDistEvent = null;
		Double minDist = Double.MAX_VALUE;
		for (Object o :eventQuery.query()){
			Event e = (Event) o;
			Double dist = this.space.getDistance(this.getLocation(), e.getLocation());
			if (dist < minDist) {
				minDist = dist;
				minDistEvent = e;
			}				
			System.out.println(String.format("Event id:%s. Distance %f",e.getId(),dist));
		}
		if ((minDistEvent !=null) && (engagementRate > RandomHelper.nextDouble() )) 
			network.addEdge(new RepastEdge<Object>(this, minDistEvent, true));
	
	}
	
	public NdPoint getLocation(){
		return this.space.getLocation(this);
	}	
	
	private Event SelectPreferedEvent(Iterable<Event> eventIter){
		return null;
	}

}
