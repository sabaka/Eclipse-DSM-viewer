package com.dsmviewer.genetic.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import com.dsmviewer.Activator;
import com.dsmviewer.dsm.DependencyMatrix;
import com.dsmviewer.dsm.DependencyMatrixOrdering;
import com.dsmviewer.genetic.Core;
import com.dsmviewer.genetic.Crossover;
import com.dsmviewer.genetic.DependencyMatrixPool;
import com.dsmviewer.genetic.DsmPoolFactory;
import com.dsmviewer.genetic.ratings.InstabilityRating;
import com.dsmviewer.genetic.ratings.Rating;
import com.dsmviewer.ui.DsmView;
import com.dsmviewer.ui.dsmtable.DsmTableController;

public class GeneticInstabilityAction extends Action
{

    private final DsmTableController dsmTableController;

    public GeneticInstabilityAction(DsmTableController dsmTableController)
    {
        this.dsmTableController = dsmTableController;
    }

    @Override
    public void run() {

    	DsmPoolFactory factory = new DsmPoolFactory(dsmTableController.getDependencyMatrix());
    	DependencyMatrixPool pool = factory.createPool(30);

    	Rating instabilityRating = new InstabilityRating();
    	Crossover strategy = new Crossover();

    	Core geneticCore = new Core(strategy, instabilityRating);
    	geneticCore.iterate(pool, 200);

    	DependencyMatrix newMatrix =  pool.getBestSolution();

        dsmTableController.setDependencyMatrix(newMatrix, true);
        DsmView.getCurrent().updateSortActionsState(DependencyMatrixOrdering.UNKNOWN_ODERING);
    }

    @Override
    public String getToolTipText() {
        return "Sort dsm by dependees instability with genetic algorithm";
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return Activator.getImageDescriptorFromRegistry("dsm-clust-i.png");
    }

}
