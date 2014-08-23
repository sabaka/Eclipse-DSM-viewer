package com.dsmviewer.genetic;

import java.util.LinkedList;
import java.util.List;

import org.dtangler.core.dsm.DsmRow;

import com.dsmviewer.dsm.DependencyMatrix;

public class Crossover {

	public List<DependencyMatrix> gitChildren(DependencyMatrix firstParent, DependencyMatrix secondParent) {

		sortSecondParentForExcludeCollisiums(firstParent, secondParent);

		DependencyMatrix firstChild = getFirstChild(firstParent, secondParent);

		DependencyMatrix secondChild = getSecondChild(firstParent, secondParent);

		List<DependencyMatrix> children = new LinkedList<DependencyMatrix>();

		children.add(firstChild);
		children.add(secondChild);
		return children;
	}

	private static DependencyMatrix getFirstChild(DependencyMatrix firstParent, DependencyMatrix secondParent) {
		int totalListSize = firstParent.getSize();
		int firstPartSize = totalListSize / 2 + 1;

		List<DsmRow> firstPartofFirstParentRows = firstParent.getRows().subList(0, firstPartSize);
		List<DsmRow> secondPartOfSecondParentRows = secondParent.getRows().subList(firstPartSize, totalListSize);

		List<DsmRow> newRowsList = new LinkedList<DsmRow>();
		newRowsList.addAll(firstPartofFirstParentRows);
		newRowsList.addAll(secondPartOfSecondParentRows);

		DependencyMatrix firstChild = new DependencyMatrix();
		firstChild.setRows(newRowsList);
		return firstChild;
	}

	private static DependencyMatrix getSecondChild(DependencyMatrix firstParent, DependencyMatrix secondParent) {
		int totalListSize = firstParent.getSize();
		int firstPartSize = totalListSize / 2 + 1;

		List<DsmRow> firstPartofSecondParentRows = secondParent.getRows().subList(0, firstPartSize);
		List<DsmRow> secondPartOfFirstParentRows = firstParent.getRows().subList(firstPartSize, totalListSize);

		List<DsmRow> newRowsList = new LinkedList<DsmRow>();
		newRowsList.addAll(firstPartofSecondParentRows);
		newRowsList.addAll(secondPartOfFirstParentRows);

		DependencyMatrix secondChild = new DependencyMatrix();
		secondChild.setRows(newRowsList);
		return secondChild;
	}

	private static void sortSecondParentForExcludeCollisiums(DependencyMatrix firstParent,
	        DependencyMatrix secondParent) {
		List<String> firstParentFullNames = firstParent.getDisplayNames();
		List<String> secondParentFullNames = secondParent.getDisplayNames();

		int totalListSize = firstParentFullNames.size();
		int firstPartSize = totalListSize / 2 + 1;

		List<String> firstPartofFirstParentNames = firstParentFullNames.subList(0, firstPartSize);
		List<String> secondPartOfSecondParentNames = secondParentFullNames.subList(firstPartSize, totalListSize);

		for (String name : firstPartofFirstParentNames) {

			if (secondPartOfSecondParentNames.contains(name)) {

				int indexFrom = secondPartOfSecondParentNames.indexOf(name);
				int indexTo = firstPartofFirstParentNames.indexOf(name);

				secondPartOfSecondParentNames.set(indexFrom, firstParentFullNames.get(indexFrom));

				secondParent.replaceElements(indexFrom, indexTo);
			}
		}

	}
}
