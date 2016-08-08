/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #2
 *
 * For this part of the assignment, a red black tree is implemented using
 * the interface provided in the abstract Tree class. The red black tree must maintain
 * all five invariants to be considered a red black tree. The red black tree has the basic
 * features of a binary tree that include adding an element given a key and element, removing
 * an element given a key, to look up an element given a key, and to print out the structure
 * of the tree to give a visual representation of what is going on. The red black tree
 * being implemented here must be a generic tree for two data types K and V where K is a 
 * comparable type.
 *
 * Thomas Nguyen
 */

package edu.csupomona.cs.cs241.prog_assgmnt_2;

public class RBTree<K extends Comparable<K>, V> implements Tree<K,V> {
	
	private Node<K,V> root;
	
	/**
	 * Constructor for the Red Black Tree that takes a key and a value and
	 * then makes the root set as that value
	 * @param value The actual value of the element that the node will nold
	 */
	
	public RBTree(K key, V value){
		root = new Node<K,V>(key, value, 'b');
	}
	
	/**
	 * The default constructor for the Red Black Tree in the instance that no key or
	 * value is given upon instantiation of the tree, thus the root will be null but will
	 * create a root the moment the add method is called.
	 */

	public RBTree() {
		root = null;
	}
	
	/**
	 * This is the add method that takes a key and a value from something that is passed in
	 * as the parameters. These values passed through are generic. A new generic node is 
	 * generated with the key and value; the color of this node being added will always be red.
	 * The base case is checked in the instance that there is no node, else a binary esq search 
	 * is implemented starting from the root. The search will traverse through the tree using the
	 * compareTo method provided by java comparing keys. In the instance that 2 keys match, then the
	 * data of the node with the key will be overwritten with the new value that is passed from the
	 * parameters. If there was no matching keys, then the search will check for if the next node is
	 * null or not, if it is not null; then it will keep going to the next node until there is an open 
	 * spot. After the new node is added, then the node that was added has it's parent set by the node 
	 * that is reached in the while loop. Afterwards, the addCheckCases are called in order to maintain the
	 * invariants by handling various cases that may occur when adding in a new node. These cases
	 * were gone over in class, so all that was needed to be done was to implement the cases and
	 * to be able to solve or set up the cases for the next case. Afterwards the tree is checked to
	 * see if any of the invariants are violated after adding. 
	 * @param key The key of the node that is going to be added
	 * @param value The data of the node that is going to be added to the tree
	 */

	@Override
	public void add(K key, V value) {
		Node<K,V> add = new Node<K,V>(key, value, 'r');
		if(root == null) // base case
			root = add;
		else{
			Node<K,V> node = root;
			while(true){
				int comparison = key.compareTo((K)node.getKey());
				if(comparison == 0){
					node.setData(value);
					return;
				}
				else if(comparison < 0){
					if(leftOf(node) == null){
						node.setLeft(add);
						break;
					}
					else
						node = leftOf(node);
				}
				else{
					assert comparison > 0;
					if(rightOf(node) == null){
						node.setRight(add);
						break;
					}
					else{
						node = rightOf(node);
					}
				}
			}
			add.setParent(node);
		}
		addCheckCase1(add);
	}
	
	/**
	 * This method checks case one of 5 cases when adding a node
	 * that was discussed in class. In case 1, the new node is
	 * now the root of the tree. The root is colored black in order
	 * to verify the invariant 2.
	 * @param node The node that is being checked by the addCheckCase
	 */
	
	private void addCheckCase1(Node<K,V> node){
		if(parentOf(node) == null)
			setColor(node, 'b');
		else
			addCheckCase2(node);
	}
	
	/**
	 * This method will test for the second case of the 5 cases where
	 * if the parent is black, then everything is fine because the node 
	 * being added is red and does not add to the black node count.
	 * @param node 
	 */
	
	private void addCheckCase2(Node<K,V> node){
		if(colorOf(parentOf(node)) == 'b')
			return; // do nothing
		else
			addCheckCase3(node);
	}
	
	/**
	 * This method will test for the third case of the 5 cases where the 
	 * uncle is red and given the previous case the parent is black. Coloring
	 * the grandparent red may violate one of the invariants, so this method will
	 * recursivly call on case 1 to fix the tree by recoloring.
	 * @param node
	 */
	
	private void addCheckCase3(Node<K,V> node){
		if(colorOf(node.getUncle()) == 'r'){
			setColor(parentOf(node), 'b');
			setColor(node.getUncle(), 'b');
			setColor(grandParentOf(node), 'r');
			addCheckCase1(grandParentOf(node));
		}
		else
			addCheckCase4(node);
	}
	
	/**
	 * This method deals with the case if the new node added is the right child
	 * of its parent and the parent is the left child of the grandparent, in other
	 * words, check if the node added is an internal child and then rotate appropriately
	 * to the situation of the child.
	 * @param node
	 */

	private void addCheckCase4(Node<K,V> node){
		if(node == rightOf(parentOf(node)) && parentOf(node) == leftOf(grandParentOf(node))){
			rotateLeft(parentOf(node));
			node = leftOf(node);
		}
		else if(node == leftOf(parentOf(node)) && parentOf(node) == rightOf(grandParentOf(node))){
			rotateRight(parentOf(node));
			node = rightOf(node);
		}
		addCheckCase5(node);
	}
	
	/**
	 * This method deals with the case where the new node added is external child
	 * of the grand parent, finding this allows us to rotate the tree to the right, else
	 * if its an external child of the other way, then the tree is rotated left about
	 * the grandparent.
	 * @param node
	 */
	
	private void addCheckCase5(Node<K,V> node){
		setColor(parentOf(node), 'b');
		setColor(grandParentOf(node), 'r');
		if(node == leftOf(parentOf(node)) && parentOf(node) == leftOf(grandParentOf(node))){
			rotateRight(grandParentOf(node));
		}
		else{
			assert node == rightOf(parentOf(node)) && parentOf(node) == rightOf(grandParentOf(node));
			rotateLeft(grandParentOf(node));
		}
	}
	
	/**
	 * This method is called from either of the addCheckCases or the removeCheckCases when
	 * trying to maintain the integrity of the red black tree. This method will swap the location
	 * of the main node being called on and its left child. Afterwards the main node's left child
	 * will be the right child of the main's left child. If the main's left has a right child, then
	 * the left's right child's parent is the main node being called on. Finally the left's right child
	 * is set to the main node and the main node's parent is its own left child. This just changes the 
	 * pointers between the two nodes.
	 * @param node Node being called on to rotate the subtree around the node being called as the
	 * parameter
	 */
	
	private void rotateRight(Node<K,V> node){
		Node<K,V> left = leftOf(node);
		replace(node, left);
		node.setLeft(rightOf(left));
		if(rightOf(left) != null)
			rightOf(left).setParent(node);
		left.setRight(node);
		node.setParent(left);
	}
	
	/**
	 * This method swaps the location between the two nodes being passed in as the parameters and
	 * then the pointers that are pointing to each other are swapped, but their respective pointers 
	 * remain the same and are swapped accordingly to the what method is calling this method.
	 * @param curr The main node being called
	 * @param newN Possibly a child of the main node being called
	 */
	
	private void replace(Node<K,V> curr, Node<K,V> newN){
		if(parentOf(curr) == null){
			root = newN;
		}
		else{
			if(curr == leftOf(parentOf(curr)))
				parentOf(curr).setLeft(newN);
			else
				parentOf(curr).setRight(newN);
		}
		if(newN != null)
			newN.setParent(parentOf(curr));
	}
	
	/**
	 * This method is essentially the mirror of rotateRight(Node<K,V>) where everything that
	 * was written in rotateRight(Node<K,V>) is now pointing to the opposite direction. See
	 * rotateRight(Node<K,V>) for details
	 * @param node node Node being called on to rotate the subtree around the node being 
	 * called as the parameter
	 */
	
	private void rotateLeft(Node<K,V> node){
		Node<K,V> right = rightOf(node);
		replace(node, right);
		node.setRight(leftOf(right));
		if(leftOf(right) != null){
			leftOf(right).setParent(node);
		}
		right.setLeft(node);
		node.setParent(right);
	}
	
	/**
	 * For the remove method, this method returns the value of the node we are looking
	 * for provided that we pass in a key as the parameter. The remove method essential
	 * uses the binarysearch() method to find the node we are looking for. If the node 
	 * does not exist, then null is returned and nothing happens. Else if the node is 
	 * found then we find save the data of that node and then find the predecessor of the
	 * node and swap their places. Afterwards we verify the existence of the child nodes of
	 * the node to be removed using a ternary statement and swap the colors
	 * of the child with node of interest and proceed to set up the tree for removal so
	 * we don't violate any of the 5 invariants. There are 6 cases that were covered in 
	 * class. After all the cases are gone through; then the node of interest is replaced
	 * with its child, then returns the value saved at the beginning.
	 * @param key The key that allows us to find the node with the data
	 * @return The data that correlates to the key we passed in as the parameter
	 */

	@Override
	public V remove(K key) {
		Node<K,V> node = binarySearch(key);
		if(node == null)
			return null; //key not found
		V toReturn = node.getData();
		if(leftOf(node) != null && rightOf(node) != null){
			Node<K,V> predecessor = findPred(leftOf(node));
			node.setKey(predecessor.getKey());
			node.setData(predecessor.getData());
			node = predecessor;
		}
		assert leftOf(node) == null || rightOf(node) == null;
		Node<K,V> child = (rightOf(node) == null) ? leftOf(node) : rightOf(node);
		if(colorOf(node) == 'b'){
			setColor(node, colorOf(child));
			removeCase1(node);
		}
		replace(node, child);
		return (V)toReturn; 
	}
	
	/**
	 * This method simply finds the in order predecessor of a given node
	 * as long as the node is not null
	 * @param node The left child of the node where we want to find the
	 * in order predecessor
	 * @return The in order predecessor
	 */
	
	private Node<K,V> findPred(Node<K,V> node){
		assert node != null;
		while(rightOf(node) != null)
			node = rightOf(node);
		return node;
	}
	
	/**
	 * This method is the start of the remove cases when a node is removed
	 * from the tree. For this case, the node removed was the root. The
	 * amount of black nodes from one path is still the same with all the 
	 * other paths.
	 * @param node The node that is being checked if it is the root
	 */
	
	private void removeCase1(Node<K,V> node){
		if(parentOf(node) == null)
			return; // do nothing
		else
			removeCase2(node);
	}
	
	/**
	 * For this method, the node removed has a red sibling, so the colors between
	 * the parent and the sibling are exchanged then rotated about the parent
	 * so the sibling becomes the parent of its old parent setting up for case 3.
	 * @param node
	 */
	
	private void removeCase2(Node<K,V> node){
		if(colorOf(siblingOf(node)) == 'r'){
			setColor(parentOf(node), 'r');
			setColor(siblingOf(node), 'b');
			if(node == leftOf(parentOf(node)))
				rotateLeft(parentOf(node));
			else
				rotateRight(parentOf(node));
		}
		removeCase3(node);
	}
	
	/**
	 * For this case, the parent, sibling and sibling's children are black, in this
	 * instance, the sibling is painted red so all the node passing through the node
	 * that is being passed as the parameter has one less black node prior to the
	 * deletion and then this case will call on case 1 recursively.
	 * @param node
	 */
	
	private void removeCase3(Node<K,V> node){
		if(colorOf(parentOf(node)) == 'b' && colorOf(siblingOf(node)) == 'b'
				&& colorOf(leftOf(siblingOf(node))) == 'b' && 
				colorOf(rightOf(siblingOf(node))) == 'b'){
			setColor(siblingOf(node), 'r');
			removeCase1(parentOf(node));
		}
		else
			removeCase4(node);
	}
	
	/**
	 * For this method, the case is that the node being called on's sibling and the sibling's
	 * children are black, but the parent is red. To resolve this issue, the colors of
	 * the sibling and the parent are exchanged to make the tree follow invariant 5
	 * @param node
	 */
	
	private void removeCase4(Node<K,V> node){
		if(colorOf(parentOf(node)) == 'r' && colorOf(siblingOf(node)) == 'b'
				&& colorOf(leftOf(siblingOf(node))) == 'b' &&
				colorOf(rightOf(siblingOf(node))) == 'b'){
			setColor(siblingOf(node), 'r');
			setColor(parentOf(node), 'b');
		}
		else
			removeCase5(node);
	}
	
	/**
	 * For this method, this handles the instance where the node being called on's
	 * sibling's internal child is red and external child is black; this or vice versa.
	 * What is done here is that the colors of the sibling and its internal child is swapped
	 * and then rotated to the right about the sibling or left depending on the sibling's side
	 * on the tree.
	 * @param node
	 */
	
	private void removeCase5(Node<K,V> node){
		if(node == leftOf(parentOf(node)) && colorOf(siblingOf(node)) == 'b'
				&& colorOf(leftOf(siblingOf(node))) == 'r' && 
				colorOf(rightOf(siblingOf(node))) == 'b'){
			setColor(siblingOf(node), 'r');
			setColor(siblingOf(node), 'b');
			rotateRight(siblingOf(node));
		}
		else if(node == rightOf(parentOf(node)) && colorOf(siblingOf(node)) == 'b'
				&& colorOf(rightOf(siblingOf(node))) == 'r' && 
				colorOf(leftOf(siblingOf(node))) == 'b'){
			setColor(siblingOf(node), 'r');
			setColor(rightOf(siblingOf(node)), 'b');
		}
		removeCase6(node);
	}
	
	/**
	 * For this method, the node being passed as the parameter's sibling is
	 * black and the sibling's external child is red. The node that is being passed
	 * has its color exchanged with the node being passed's parent and the sibling,
	 * then make the external child black and then rotating about the parent of the
	 * node being passed. This applies vice versa in the instance the external child of the
	 * sibling is on the other side of the tree.
	 * @param node
	 */
	
	private void removeCase6(Node<K,V> node){
		setColor(siblingOf(node), colorOf(parentOf(node)));
		setColor(parentOf(node), 'b');
		if(node == leftOf(parentOf(node))){
			assert colorOf(rightOf(siblingOf(node))) == 'r';
			setColor(rightOf(siblingOf(node)), 'b');
			rotateLeft(parentOf(node));
		}
		else{
			assert colorOf(leftOf(siblingOf(node))) == 'r';
			setColor(leftOf(siblingOf(node)), 'b');
			rotateRight(parentOf(node));
		}
	}
	
	/**
	 * This method employs the use of a binary search that is used on 
	 * a binary tree. This ends up being faster than searching blindly
	 * through some method of traversal because the nodes are organized 
	 * by the order of their keys.
	 * @param key The key of the node that we are looking for
	 * @return The node that we are looking for
	 */
	
	private Node<K,V> binarySearch(K key){
		Node<K,V> node = root;
		while(node != null){
			int comparison = key.compareTo((K)node.getKey());
			if(comparison == 0)
				return (Node<K,V>)node;
			else if(comparison < 0)
				node = leftOf(node);
			else{
				assert comparison >0;
				node = rightOf(node);
			}
		}
		return null;
	}
	
	/**
	 * The lookup() method is a method that was forced to be implemented
	 * due to it being in the Tree interface. This method simply looks for the
	 * node with the key that we pass through as the parameter by using the
	 * binary search method that has been implemented before I even got to this
	 * part of the program.
	 * @param key The key of the node that we are looking for
	 * @return The data of the node we are looking for, else if it doesn't exist then
	 * return null
	 */

	@Override
	public V lookup(K key) {
		Node<K,V> node = binarySearch(key);
		return  node != null ? (V)node.getData() : null;
	}
	
	/**
	 * This method was supposed to return a string, but seriously I don't know how
	 * to print out the tree. At first I wanted to try out using breadth first traversal,
	 * but eventually it became something that employs the use of a stack. This code is
	 * really messy and does not display the nil leaves. This method incorporates the use of 2
	 * stacks and will print out the tree in a pyramid like fashion.
	 * @return A string that has nothing because the method itself just prints out the whole tree
	 */

	@Override
	public String toPrettyString() {
		StackForPrinting<Node<K,V>> nStack = new StackForPrinting();
		nStack.push(root);
		int emptyLeaf = 32;
		boolean isRowEmpty = false;
		while(isRowEmpty == false){
			StackForPrinting<Node<K,V>> otherStack = new StackForPrinting();
			isRowEmpty = true;
			for(int i = 0; i < emptyLeaf; i++)
				System.out.print(' ');
			while(nStack.isEmpty() == false){
				Node<K,V> temp = nStack.pop();
				if(temp != null){
					System.out.print(temp.getData());
					System.out.print(temp.getColor());
					otherStack.push(temp.getLeft());
					otherStack.push(temp.getRight());
					if(temp.getLeft() != null || temp.getRight() != null)
						isRowEmpty = false;
				}
				else{
					System.out.print("--");
					otherStack.push(null);
					otherStack.push(null);
				}
				for(int j = 0; j < emptyLeaf*2-2; j++)
					System.out.print(' ');
			}
			System.out.println();
			emptyLeaf /= 2;
			while(otherStack.isEmpty() == false)
				nStack.push(otherStack.pop());
		}
		return null;
	}
	
	/**
	 * This method is a getter method that uses the getter method in the node class, but 
	 * this method prevents me from getting a null pointer exception. Whenever I call a node
	 * for it's color the node might be null, in that instance it must be a nil leaf which means
	 * it must always be black. 
	 * @param node The node of interest being called
	 * @return The color of the node represented as a string
	 */
	
	private char colorOf(Node<K, V> node){
		if(node == null)
			return 'b';
		else
			return node.getColor();
	}
	
	/**
	 * This method is a boolean method that confirms the color of the node because I tried using 
	 * a getter method in the Node<K,V> class, but it ended up giving me a null pointer exception. This
	 * method essential prevents me from getting a headache.
	 * @param node The node of interest
	 * @return Whether or not the node is red and is actually a node
	 */
	
	private boolean isRed(Node<K,V> node){
		return node != null && node.getColor() == 'r';
	}
	
	/**
	 * This method is essentially the same as the isRed() boolean method except this method concerns
	 * itself with black nodes.
	 * @param node The node of interest
	 * @return Whether or not the node is black and is actually a node
	 */
	
	private boolean isBlack(Node<K,V> node){
		return node != null && node.getColor() == 'b';
	}
	
	/**
	 * This method is a setter method that changes the color of a node, the issue here was that 
	 * color a nil leaf should never ever happen, so I made it so the nil leaf is pretty much
	 * a null node that will always be black. This method will never color the node that never was.
	 * @param node The node of interest
	 * @param color The color that we want the node to be.
	 */
	
	private void setColor(Node<K,V> node, char color){
		if(node != null)
			node.setColor(color);
	}
	
	/**
	 * This method uses a ternary operator to cut down on the lines used in a standard if statement. This
	 * also prevents me from getting a null pointer exception if I use this over the getter method found
	 * in the Node<K,V> method
	 * @param node The node of interest
	 * @return The parent of the node of interest
	 */
	
	private Node<K,V> parentOf(Node<K,V> node){
		return node == null ? null : (Node<K,V>)node.getParent();
	}
	
	/**
	 * This method is used to find the grand parent of a given node without running into a null pointer
	 * exception, so in the instance I pass in the root, or a child of the root; I don't get slapped in the
	 * face by a null pointer exception because I am so sick of those by now
	 * @param node The node of interest in which we want to find its grandparent
	 * @return The grandparent of the node if it does exist
	 */
	
	private Node<K,V> grandParentOf(Node<K,V> node){
		return (node == null || node.getParent() == null) ? null
				: (Node<K,V>) node.getParent().getParent();
	}
	
	/**
	 * This method finds the sibling of the given node of interest unless the node is null, or the parent of
	 * the node is null, then it would just return null
	 * @param node The node of who's sibling we want to find
	 * @return The sibling of the node or null
	 */
	
	private Node<K,V> siblingOf(Node<K,V> node){
		return (node == null || node.getParent() == null) ?
				null : (node == node.getParent().getLeft()) 
				? (Node<K,V>) node.getParent().getRight() :
					(Node<K,V>) node.getParent().getLeft();
	}
	
	/**
	 * This method returns the left child of a node unless the node itself is null. This prevents 
	 * me from getting slapped in the face with a null pointer exception
	 * @param node The node who's left child we want to find
	 * @return The left child
	 */
	
	private Node<K,V> leftOf(Node<K,V> node){
		return node == null ? null : (Node<K,V>)node.getLeft();
	}
	
	/**
	 * This method returns the right child of a node unless the node itself is null. This prevents 
	 * me from getting a null pointer exception in some cases
	 * @param node The node of who's right child we want to find
	 * @return The right child
	 */
	
	private Node<K,V> rightOf(Node<K,V> node){
		return node == null ? null : (Node<K,V>)node.getRight();
	}

}
