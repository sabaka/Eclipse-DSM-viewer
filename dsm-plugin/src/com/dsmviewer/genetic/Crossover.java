package com.dsmviewer.genetic;

import java.util.LinkedList;
import java.util.List;

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

		List<String> firstPartofFirstParentNames = firstParent.getDisplayNames().subList(0, firstPartSize);
		List<String> secondPartOfSecondParentNames =
		        secondParent.getDisplayNames().subList(firstPartSize, totalListSize);

		List<String> newNamesOrder = new LinkedList<String>();
		newNamesOrder.addAll(firstPartofFirstParentNames);
		newNamesOrder.addAll(secondPartOfSecondParentNames);

		DependencyMatrix firstChild = GeneticAlgorithmUtils.getCopiedMatrixInstance(firstParent);
		List<String> oldNameOrder = firstChild.getDisplayNames();

		for (String name : newNamesOrder) {
			int indexTo = newNamesOrder.indexOf(name);
			int indexFrom = oldNameOrder.indexOf(name);
			firstChild.replaceElements(indexFrom, indexTo);
			oldNameOrder = firstChild.getDisplayNames();
		}

		return firstChild;
	}

	private static DependencyMatrix getSecondChild(DependencyMatrix firstParent, DependencyMatrix secondParent) {
		int totalListSize = firstParent.getSize();
		int firstPartSize = totalListSize / 2 + 1;

		List<String> firstPartofSecondParentNames = secondParent.getDisplayNames().subList(0, firstPartSize);
		List<String> secondPartOfFirstParentNames = firstParent.getDisplayNames().subList(firstPartSize, totalListSize);

		List<String> newNamesOrder = new LinkedList<String>();
		newNamesOrder.addAll(firstPartofSecondParentNames);
		newNamesOrder.addAll(secondPartOfFirstParentNames);

		DependencyMatrix secondChild = GeneticAlgorithmUtils.getCopiedMatrixInstance(secondParent);
		List<String> oldNameOrder = secondChild.getDisplayNames();

		for (String name : newNamesOrder) {
			int indexTo = newNamesOrder.indexOf(name);
			int indexFrom = oldNameOrder.indexOf(name);
			secondChild.replaceElements(indexFrom, indexTo);
			oldNameOrder = secondChild.getDisplayNames();
		}

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
