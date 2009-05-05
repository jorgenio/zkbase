package org.zkbase.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TreeNode {
	@Id
	Long id;
	String name;
	int length;
	//List<Branch> children;
	Long parent_id;

	public TreeNode() {
		
	}
	
	public TreeNode(String name, int length) {
		super();
		this.name = name;
		this.length = length;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	//	@OneToMany
//	@JoinColumn(name="parent_id")
//	public List<Branch> getChildren() {
//		return children;
//	}
//	public void setChildren(List<Branch> children) {
//		this.children = children;
//	}
	@Override
	public String toString() {
		return this.getName().toString();
	}	
}
