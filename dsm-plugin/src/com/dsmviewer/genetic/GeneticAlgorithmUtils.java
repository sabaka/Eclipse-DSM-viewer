package com.dsmviewer.genetic;

import java.util.ArrayList;
import java.util.List;

import org.dtangler.core.dsm.DsmCell;
import org.dtangler.core.dsm.DsmRow;

import com.dsmviewer.dsm.DependencyMatrix;

public final class GeneticAlgorithmUtils
{
    private GeneticAlgorithmUtils() {

    }

    public static DependencyMatrix getCopiedMatrixInstance(DependencyMatrix originMatrix) {
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
