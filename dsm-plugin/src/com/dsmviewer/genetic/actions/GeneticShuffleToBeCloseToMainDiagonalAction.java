package com.dsmviewer.genetic.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import com.dsmviewer.Activator;
import com.dsmviewer.ui.dsmtable.DsmTableController;

public class GeneticShuffleToBeCloseToMainDiagonalAction extends Action
{
    private final DsmTableController dsmTableController;

    public GeneticShuffleToBeCloseToMainDiagonalAction(DsmTableController dsmTableController)
    {
        this.dsmTableController = dsmTableController;
    }

    @Override
    public void run() {
    	
    }

    @Override
    public String getToolTipText() {
        return "Clustering matrix with genetic algorithm";
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return Activator.getImageDescriptorFromRegistry("dsm-clust-1.png");
    }
}
