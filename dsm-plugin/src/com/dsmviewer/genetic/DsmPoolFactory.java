package com.dsmviewer.genetic;

import java.util.LinkedList;
import java.util.List;

import com.dsmviewer.cluster.DependencyMatrixShuffler;
import com.dsmviewer.dsm.DependencyMatrix;

public class DsmPoolFactory
{
	private static final int NUMBER_OF_SHUFFLES = 100;

	private final DependencyMatrix originMatrix;

	public DsmPoolFactory(DependencyMatrix originMatrix) {
		this.originMatrix = originMatrix;
	}

	public DependencyMatrixPool createPool(int size) {
		DependencyMatrixPool pool = new DependencyMatrixPool(createMatrix(size));
		return pool;
	}

	private List<DependencyMatrix> createMatrix(int listLength) {
		List<DependencyMatrix> listOfMatrix = new LinkedList<DependencyMatrix>();
		DependencyMatrixShuffler shuffler = new DependencyMatrixShuffler(NUMBER_OF_SHUFFLES);
		for (int i = 0; i < listLength; i++) {
			DependencyMatrix newMatrix = GeneticAlgorithmUtils.getCopiedMatrixInstance(shuffler.shuffle(originMatrix));
			listOfMatrix.add(newMatrix);
		}
		return listOfMatrix;
	}

}
