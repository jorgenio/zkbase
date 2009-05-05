package org.zkbase.webapp.controller;

import java.util.List;
import java.util.Vector;

import org.zkbase.model.TreeNode;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;

public class TreeComposer extends GenericForwardComposer {

	Tree tree;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		SimpleTreeNode rootNode = new SimpleTreeNode("root", this.getTree());
		TreeModel model = new SimpleTreeModel(rootNode);
		tree.setModel(model);
	}

	private List<SimpleTreeNode> getTree() {
		
		List<TreeNode> TreeNode1 = new Vector<TreeNode>();
		TreeNode1.add(new TreeNode("first", 10));
		TreeNode1.add(new TreeNode("second", 20));
		TreeNode1.add(new TreeNode("third", 30));
		
		List<TreeNode> TreeNode2 = new Vector<TreeNode>();
		TreeNode2.add(new TreeNode("first", 10));
		TreeNode2.add(new TreeNode("second", 20));
		TreeNode2.add(new TreeNode("third", 30));
		
		List<TreeNode> TreeNode3 = new Vector<TreeNode>();
		TreeNode3.add(new TreeNode("first", 10));
		TreeNode3.add(new TreeNode("second", 20));
		TreeNode3.add(new TreeNode("third", 30));
		
		List<SimpleTreeNode> tree = new Vector<SimpleTreeNode>();
		tree.add(new SimpleTreeNode("TreeNode1", TreeNode1));
		tree.add(new SimpleTreeNode("TreeNode2", TreeNode2));
		tree.add(new SimpleTreeNode("TreeNode3", TreeNode3));
		return tree;
	}		
}
