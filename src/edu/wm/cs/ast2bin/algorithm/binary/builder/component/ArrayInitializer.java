package edu.wm.cs.ast2bin.algorithm.binary.builder.component;

import java.util.List;

import edu.wm.cs.ast2bin.tree.BinaryTree;
import edu.wm.cs.ast2bin.tree.vertex.BinaryVertex;

public class ArrayInitializer extends BinaryBuilderComponent{

	public static void convertToBinary(BinaryVertex vertex, BinaryTree tree) {
		//Siblings
		List<BinaryVertex> siblings = tree.getChildren(vertex);

		disconnectChildren(vertex, siblings, tree);

		//Create new artificial NonTerminal node
		BinaryVertex nextParent = new BinaryVertex();
		nextParent.setType(Type.ARRAY_INITIALIZER_LIST);
		tree.addVertex(nextParent);

		//Add edge to parent
		tree.addEdge(nextParent, vertex);
		nextParent.setParent(vertex);

		//FirstChild
		BinaryVertex child = siblings.remove(0);

		//Recursion
		buildList(nextParent, child, siblings, tree, Type.ARRAY_INITIALIZER_LIST);
	}
}
