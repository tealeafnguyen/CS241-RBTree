/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #2
 *
 * For this part of the assignment, this is the interface that is
 * implemented by the RBTree.java class. This interface has the basic 
 * functions of a typical tree: to add a new element, to remove a node given
 * a key, and to look up an element given a key. The last method toPrettyString()
 * is a method that will return a string which will print out the current tree in
 * a pyramid like fashion to help visualize the tree.
 *
 * Thomas Nguyen
 */
package edu.csupomona.cs.cs241.prog_assgmnt_2;

public interface Tree<K extends Comparable<K>, V> {

	public void add(K key, V value);

	public V remove(K key);

	public V lookup(K key);

	public String toPrettyString();

}