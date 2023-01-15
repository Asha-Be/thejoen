package javatest1;

public class Solution {
	
	public static void main (String [] args) {
		
		ChildEx ce = new ChildEx ();
	}
	
}

class ParentEx {
	ParentEx() {
		this (1);
		System.out.println("(1)");
	}
	ParentEx(int x) {
		this (x);
		System.out.println("(2)");

}
}

class ChildEx extends ParentEx {
	ChildEx() {
		this (1);
		System.out.println("(3)");
	}
	ChildEx(int x) {
		this (int x);
		System.out.println("(4)");

}
}