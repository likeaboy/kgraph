package org.jrocky.kgraph.filter;

import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder.DegreeRangeFilter;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.jrocky.kgraph.KGraphContext;
import org.jrocky.kgraph.KGraphConstant;
import org.openide.util.Lookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KGraphFilter implements IKGraphFilter{
	
	private static Logger logger = LoggerFactory.getLogger(KGraphFilter.class);
	
	private KGraphFilter(){}
	
	private static volatile KGraphFilter instance = new KGraphFilter();
	
	public static KGraphFilter getInstance(){
		return instance;
	}

	@Override
	public void filter(KGraphContext context) {
		GraphModel graphModel = context.getGraphModel();
		//Filter, remove degree < GephiConstant.FILTER_DEGREE_THRESHOLD
        FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
        DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
        degreeFilter.init(graphModel.getGraph());
        degreeFilter.setRange(new Range(KGraphConstant.FILTER_DEGREE_THRESHOLD, Integer.MAX_VALUE));     //Remove nodes with degree < 10
        Query query = filterController.createQuery(degreeFilter);
        GraphView view = filterController.filter(query);
        graphModel.setVisibleView(view);    //Set the filter result as the visible view
	}

	
}
