package culture;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;

public class JCultureBuilder implements ContextBuilder <Object >{

	@Override
	public Context build(Context<Object> context) {
		context.setId ("Culture");
		
		//Random.createUniform();
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace ("space", context ,
				new RandomCartesianAdder<Object>(),
				new repast.simphony.space.continuous.StrictBorders(),
				50,50);
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid",context,
				new GridBuilderParameters<Object>(new repast.simphony.space.grid.StrictBorders(),
						new SimpleGridAdder<Object>(),
						true,50,50));
		
		NetworkBuilder builder = new NetworkBuilder("network", context, true);
		Network network = builder.buildNetwork();
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		
		int actorsCount = params.getInteger("actor_count"); 
		Actor.engagementRate = params.getDouble("engagement_rate");
		for (int i = 0; i < actorsCount ; i++) {
			context.add (new Actor (space , grid, network ));
		}
		
		int eventCount = params.getInteger("event_count");;
		for (int i = 0; i < eventCount ; i++) {
			context.add (new Event (space , grid, network, String.valueOf(i) ));
		}
		return context;
	}

}
