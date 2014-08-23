package com.dsmviewer.genetic.ratings;

import com.dsmviewer.dsm.DependencyMatrix;

public interface Rating {

	public int calcRating(DependencyMatrix matrix);

}
