package com.dsmviewer.genetic;

import java.util.List;

import org.dtangler.core.dsm.DsmCell;
import org.dtangler.core.dsm.DsmRow;

import com.dsmviewer.dsm.DependencyMatrix;

public class InstabilityRating implements Rating {

	@Override
	public int calcRating(DependencyMatrix matrix) {
		List<DsmRow> rows = matrix.getRows();

        int matrixWeight = 0;
        for (int rowNumber = 0; rowNumber < rows.size(); rowNumber++) {
            DsmRow row = rows.get(rowNumber);
            List<DsmCell> cells = row.getCells();

            for (int columnNumber = 0; columnNumber < cells.size(); columnNumber++) {
                DsmCell cell = cells.get(columnNumber); 
                if (rowNumber > columnNumber && cell.getDependencyWeight() > 0) {
                    matrixWeight++;
                }
            }
        }
        return matrixWeight;
	}

}
