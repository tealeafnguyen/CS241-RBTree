/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #2
 *
 * For this class, this class houses the main method that only has one purpose and that is to
 * test the operations of add, remove, and toPrettyString. If the remove method works, then the
 * lookUp method should work because the lookUp method uses a search method that the remove method
 * uses. Both of those methods return a value as well, so if the remove method works normally then
 * the lookUp method should work as intended.
 *
 * Thomas Nguyen
 */

package edu.csupomona.cs.cs241.prog_assgmnt_2;

public class RedBlackTreeTest {
	
	/**
	 * This is the main method of the program, there is nothing here for exceptions handling there
	 * is no UI that accepts user input, thus there are no precautions needed to be taken when dealing
	 * with all of the tree operations. This method simply tests the add and remove methods and if the cases
	 * were handled properly.
	 * @param args 
	 */

	public static void main(String[] args) {
		RBTree test = new RBTree(42,42);
		System.out.println("success in making tree");
		test.add(36, 36);
		System.out.println("success in adding "+ test.lookup(36));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(4, 4);
		System.out.println("success in adding "+ test.lookup(4));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(77, 77);
		System.out.println("success in adding "+ test.lookup(77));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(44, 44);
		System.out.println("success in adding "+ test.lookup(44));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(53, 53);
		System.out.println("success in adding "+ test.lookup(53));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(14, 14);
		System.out.println("success in adding "+ test.lookup(14));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(99,99);
		System.out.println("success in adding "+ test.lookup(99));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(25,25);
		System.out.println("success in adding "+ test.lookup(25));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(1,1);
		System.out.println("success in adding "+ test.lookup(99));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(21,21);
		System.out.println("success in adding "+ test.lookup(99));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(75,75);
		System.out.println("success in adding "+ test.lookup(99));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.add(82,82);
		System.out.println("success in adding "+ test.lookup(99));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");		
		test.add(54,54);
		System.out.println("success in adding "+ test.lookup(99));
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.toPrettyString();
		System.out.println("all values successfully added");
		System.out.println("----------------------------------------------------");
		test.remove(36);
		System.out.println("success in removing 36");
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.remove(77);
		System.out.println("success in removing 77");
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.remove(54);
		System.out.println("success in removing 54");
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.remove(44);
		System.out.println("success in removing 44");
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.remove(4);
		System.out.println("success in removing 4");
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.remove(82);
		System.out.println("success in removing 82");
		test.toPrettyString();
		System.out.println("----------------------------------------------------");
		test.remove(99);
		System.out.println("success in removin 99");
		test.toPrettyString();
	}

}
