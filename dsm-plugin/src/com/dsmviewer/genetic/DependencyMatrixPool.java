package com.dsmviewer.genetic;

import java.util.List;

import com.dsmviewer.dsm.DependencyMatrix;

public class DependencyMatrixPool
{
	private final List<DependencyMatrix> pool;

	private int bestRating = Integer.MAX_VALUE;

	private DependencyMatrix bestSolution;

	protected DependencyMatrixPool(List<DependencyMatrix> pool) {
		this.pool = pool;
	}

	public DependencyMatrix getBestSolution()
	{
		return bestSolution;
	}

	public void setBestSolution(DependencyMatrix bestSolution)
	{
		this.bestSolution = bestSolution;
	}

	public int getPoolSize()
	{
		return pool.size();
	}

	public List<DependencyMatrix> getPool()
	{
		return pool;
	}

	public void addElement(DependencyMatrix element) {
		pool.add(element);
	}

	public void addAllElements(List<DependencyMatrix> newElements) {
		pool.addAll(newElements);
	}

	public int getBestRating() {
	    return bestRating;
    }

	public void setBestRating(int bestRating) {
	    this.bestRating = bestRating;
    }


}
