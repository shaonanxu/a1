package test.x;

public class TestBinary {

	public static void main(String[] args) {
		long u = 16;
		System.out.println(Long.toBinaryString(u));
		System.out.println(Long.bitCount(u));
		System.out.println(Long.highestOneBit(u));
		
		int v = 0xcc9e2d51;
		System.out.println(Integer.toBinaryString(v));
	    int v2 = 0x1b873593;
	    System.out.println(Integer.toBinaryString(v2));
	}

}
