/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment 2
 *
 * For this part of the assignment, this method represents a node class that
 * will accept a generic type T that implements its own comparable method when
 * asked to compare special data types like objects. The node class encompasses 
 * everything that a node should possess, getters and setters are a given.
 * The node will also keep track of its own color given in the form of a char.
 *
 * Thomas Nguyen
 */
package edu.csupomona.cs.cs241.prog_assgmnt_2;

public class Node<K extends Comparable <K>, V> {
	
	private K key;
	
	private V data;
	
	private Node<K,V> left, right, parent;
	
	private char color;
	
	/**
	 * Default constructor of the node, the color will always be black and
	 * the data it possesses will be null until something is added in
	 */
	
	public Node(){
		key = null;
		data = null;
		color = 'b';
		parent = null;
	}
	
	/**
	 * 
	 * The constructor is called when a parameter is given for the node to take.
	 * The color of the node will always be black when generated, but that is subject to
	 * change once it is placed into the tree.
	 *
	 * @param key The key that is passed in from the add method
	 * @param data The actual data that is passed in along with the key from the add method
	 * @param color The color by default will always be red or 'r', but this method was made
	 * before I got to the add method
	 */

	public Node(K key, V data, char color) {
		this.key = key;
		this.data = data;
		this.color = color;
		parent = null;
	}
	
	/**
	 * Another constructor for the instance that I needed to create a nil leaf, but this method wasn't used
	 * because I made it so that the getColor() method in the RBTree class would return 'b' whenever the node
	 * it calls is a null making the nil leaf implicit because it will always be there without being added 
	 * @param parent The nil leaf would only have one pointer and that is to the parent, but unfortunately this method
	 * is not needed, but is left here in the instance that I did need it.
	 */
	
	public Node(Node<K,V> parent){
		key = null;
		data = null;
		color = 'b';
		this.parent = parent;
	}
	
	/**
	 * This is the main constructor of the Node class, but I don't think I ever had to call this method because 
	 * like the same reason above, when a new node is added, the left and right are always null and a method that I have
	 * that returns the color will always return black in the instance the program checks the color of a null part of the
	 * tree
	 * @param key
	 * @param data
	 * @param color
	 * @param left
	 * @param right
	 */
	
	public Node(K key, V data, char color, Node<K,V> left, Node<K,V> right) {
		this.key = key;
		this.data = data;
		this.color = color;
		parent = null;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * The getter method that returns the key of the node, mainly
	 * used for identifying the node without knowing the data it
	 * contains.
	 * @return The key of the node
	 */
	
	public K getKey(){
		return key;
	}
	
	/**
	 * The getter method that returns the data of the node
	 * that calls this method. 
	 * @return The element of a node
	 */
	
	public V getData(){
		return data;
	}
	
	/**
	 * The getter method that returns the left child of itself when
	 * a node calls upon this method, although this method is subject 
	 * to a null pointer exception
	 * @return The left child of the node that calls this method
	 */
	
	public Node<K,V> getLeft(){
		return left;
	}
	
	/**
	 * The getter method equivalent of the getLeft() method except that this
	 * method returns the right child of a given.
	 * @return The right child of a node that calls this method
	 */
	
	public Node<K,V> getRight(){
		return right;
	}
	
	/**
	 * The getter method that returns the parent of itself when a node
	 * calls upon this method
	 * @return The parent of the node that calls this method
	 */
	
	public Node<K,V> getParent(){
		return parent;
	}
	
	/**
	 * The setter method that alters the key of the node with the
	 * new key that is passed in as the argument. Usually called when
	 * replacing 2 nodes.
	 * @param key The key that will replace the current key
	 */
	
	public void setKey(K key){
		this.key = key;
	}
	
	/**
	 * The setter method that changes the current data being held on
	 * by the node
	 * @param data The data that will replace the current data
	 */
	
	public void setData(V data){
		this.data = data;
	}
	
	/**
	 * The setter method that will change the color of a node taking
	 * in a char as the parameter, since the parameter is not based
	 * on user input, there is nothing that will catch an exception
	 * @param newColor The char that is passed from the RBTree class
	 */
	
	public void setColor(char color){
		this.color = color;
	}
	
	/**
	 * The setter method that sets a given node its left
	 * child, the node does not necessarily need a child because
	 * a RB tree is not essentially complete although it does rebalance
	 * itself
	 * @param newLeft The node that will be the left child of a node
	 */
	
	public void setLeft(Node<K,V> left){
		this.left = left;
	}
	
	/**
	 * The setter method that is the same as the setLeft() method 
	 * except this method sets the right child of a given node
	 * @param newRight The node that will be the right child of a node
	 */
	
	public void setRight(Node<K,V> right){
		this.right = right;
	}
	
	/**
	 * The setter method that sets a given node its parent, in
	 * the instance the parameter is null, then the node itself is
	 * the root.
	 * @param newParent The parent of the given node
	 */
	
	public void setParent(Node<K,V> parent){
		this.parent = parent;
	}
	
	/**
	 * The getter method that returns the color of the node in 
	 * the form of a char. The char returned will always be either 'r'
	 * or 'b'
	 * @return The character 'r' or 'b' 
	 */

	public char getColor() {
		return color;
	}
	
	/**
	 * A method that is used to find the sibling of a node that is used to 
	 * call this method. This makes it easier to find the sibling without getting
	 * a null pointer exception with the inclusion of assert. Furthermore this method 
	 * is called multiple times so it saves time than writing out the algorithm every time
	 * I want to find the sibling.
	 * @return The sibling of the node calling this method
	 */
	
	public Node<K,V> getSibling(){
		assert parent != null;
		if(this == parent.left)
			return parent.right;
		else
			return parent.left;		
	}
	
	/**
	 * A method that is used to find the uncle of a node that is used to call this method.
	 * This method makes it easier to find the uncle without running into a null pointer
	 * exception due to the assert statement used in the instance the parent sibling is null.
	 * @return The uncle of the node that calls this method or null if the parent is null 
	 * or the sibling of the parent is null.
	 */
	
	public Node<K,V> getUncle(){
		assert parent != null;
		assert parent.parent != null;
		return parent.getSibling();
	}

}
