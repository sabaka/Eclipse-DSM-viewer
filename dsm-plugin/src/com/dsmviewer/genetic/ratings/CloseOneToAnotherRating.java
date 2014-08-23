package com.dsmviewer.genetic.ratings;

import java.util.List;

import org.dtangler.core.dsm.DsmCell;
import org.dtangler.core.dsm.DsmRow;

import com.dsmviewer.dsm.DependencyMatrix;

public class CloseOneToAnotherRating implements Rating {

	@Override
	public int calcRating(DependencyMatrix matrix) {
		double result = 0;

		List<DsmRow> rows = matrix.getRows();
		for (int i = 0; i < rows.size(); i++) {
			DsmRow row = rows.get(i);
			List<DsmCell> cells = row.getCells();
			for (int j = 0; j < cells.size(); j++) {
				if (i != j) {
					DsmCell cellFrom = row.getCells().get(j);
					int cellFromDependencyWeight = cellFrom.getDependencyWeight();
					if (cellFromDependencyWeight > 0) {
						result += countDistanceToAnotherCells(rows, i, j, cellFromDependencyWeight);
					}
				}
			}
		}

		return -(int) result;
	}

	private static double countDistanceToAnotherCells(List<DsmRow> rows,
			int rowIndex, int columnIndex, int cellFromDependencyWeight) {

		double result = 0;

		for (int i = 0; i < rows.size(); i++) {

			if (i != rowIndex) {
				DsmRow row = rows.get(i);
				List<DsmCell> cells = row.getCells();
				for (int j = 0; j < cells.size(); j++) {
					if (j != columnIndex) {
						DsmCell cellTo = cells.get(j);

						int cellToDependencyWeight = cellTo.getDependencyWeight();
						if (cellToDependencyWeight > 0) {
							double rowDistance = Math.abs(i - rowIndex) * 2 + 0.1;
							double columnDistance = Math.abs(j - columnIndex) * 2 + 0.1;
							result += Math.max(rowDistance, columnDistance) * cellFromDependencyWeight
									* cellToDependencyWeight;
						}
					}
				}
			}
		}

		return result;
	}

}
