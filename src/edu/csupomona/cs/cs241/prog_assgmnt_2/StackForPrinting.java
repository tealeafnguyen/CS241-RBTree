/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment 2
 *
 * For this part of the assignment a stack is implemented in order to print
 * out the tree in a pyramid like fashion, since the stack will not be 
 * taking user input, there is nothing to prevent the stack itself from running
 * into an exception. There is also no exceptions handling.
 *
 * Thomas Nguyen
 */
package edu.csupomona.cs.cs241.prog_assgmnt_2;

public class StackForPrinting<T> {

	int top;

	T[] stackItself;

	/**
	 * The default constructor of the stack, this stack will only take nodes because
	 * the sole purpose for this stack to even be here is to print out
	 * the structure of the tree. The stack will always have a size of 70.
	 */
	
	public StackForPrinting() {
		stackItself = (T[]) new Object[70];
		top = 0;
	}
	
	/**
	 * The pop method of the stack that returns the top of the stack which
	 * will always be a node
	 * @return The node on top of the stack
	 */
	
	public T pop(){
		T toReturn = stackItself[--top];
		return toReturn;
	}
	
	/**
	 * The push method of the stack that will push in a node to the top
	 * of the stack and increase the integer top by one.
	 * @param node
	 */
	
	public void push(T data){
		stackItself[top] = data;
		top++;
	}
	
	/**
	 * The boolean method that returns whether or not the stack is empty
	 * @return The boolean if the stack is empty or not
	 */
	
	public boolean isEmpty(){
		return top == 0;
	}

}
