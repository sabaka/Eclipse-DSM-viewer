package com.dsmviewer.genetic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.dsmviewer.dsm.DependencyMatrix;
import com.dsmviewer.genetic.ratings.Rating;

/**
 * 
 * @author Ilja Dubinin
 *
 */
public class Core {

	private final Crossover strategy;
	private final Rating rating;

	public Core(Crossover strategy, Rating rating) {
		this.strategy = strategy;
		this.rating = rating;
	}

	public void iterate(DependencyMatrixPool pool, int iter) {
		List<DependencyMatrix> listOfMatrix = pool.getPool();
		int populationSize = listOfMatrix.size();
		for (int iterationNumber = 1; iterationNumber <= iter; iterationNumber++) {

			//Remove too old generation
			if (iterationNumber > 3) {
				for (int i = populationSize - 1; i >= 0; i--) {
					listOfMatrix.remove(i);
				}
			}

			//Calc rating for current genome and select best solution
			for (DependencyMatrix matrix : listOfMatrix) {
				int currentRating =  rating.calcRating(matrix);

				if (currentRating < pool.getBestRating()) {
					pool.setBestSolution(matrix);
					pool.setBestRating(currentRating);
				}
			}

			//Extend current generation with new children
			if (pool.getBestRating() > 0) {
				List<DependencyMatrix> newGen = new LinkedList<DependencyMatrix>();
				for (int i = 0; i < populationSize; i += 2) {
					Random random = new Random();
					List<DependencyMatrix> children = strategy
					        .gitChildren(listOfMatrix.get(random.nextInt(listOfMatrix.size())),
					        		listOfMatrix.get(random.nextInt(listOfMatrix.size())));
					newGen.addAll(children);
				}
				pool.addAllElements(newGen);
			}
		}
	}
}