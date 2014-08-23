package com.dsmviewer.genetic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.dtangler.core.dsm.DsmCell;
import org.dtangler.core.dsm.DsmRow;

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
			DependencyMatrix newMatrix = getCopiedMatrixInstance(shuffler.shuffle(originMatrix));
//			newMatrix.setRows(getCopiedMatrix(shuffler.shuffle(originMatrix).getRows());
			listOfMatrix.add(newMatrix);
		}
		return listOfMatrix;
	}

	private static DependencyMatrix getCopiedMatrixInstance(DependencyMatrix originMatrix) {
		List<DsmRow> originRows = originMatrix.getRows();
		List<DsmRow> newRows = new ArrayList<DsmRow>();
		for (DsmRow originRow : originRows) {
			List<DsmCell> originCells = originRow.getCells();

			List<DsmCell> newCells = new ArrayList<DsmCell>();
			for (DsmCell originCell : originCells) {
				DsmCell cell = new DsmCell(
				        originCell.getDependency().getDependant(), originCell.getDependency().getDependee(),
				        originCell.getDependencyWeight());
				newCells.add(cell);
			}
			DsmRow newRow = new DsmRow(originRow.getDependee(), newCells);
			newRows.add(newRow);

		}
		DependencyMatrix newMatrix = new DependencyMatrix();
		newMatrix.setRows(newRows);
		return newMatrix;

	}

}
