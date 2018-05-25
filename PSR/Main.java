package PSR;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int rank;
	static Scanner in = new Scanner(System.in);
	
	//Strings for interaction with the end user.
	static String queryRank = "Enter rank: ";
	static String queryKType = "Enter K-Type: ";
	static String queryMType = "Enter M-Type: ";
	static String queryAType = "Enter A-Type: ";
	
	//Define the highest weight of an irrep of a compact group,
	//given the end user's input.
	public static double[] getHighestWeight(int R, int N) {
		double[] highestWeight = new double[R];
		
		for(int i = 0; i<N; i++) {
			highestWeight[i] = in.nextDouble();
		}
		for(int i = N; i<highestWeight.length-N; i++) {
			highestWeight[i] = 0;
		}
		for(int i = highestWeight.length-N; i<highestWeight.length; i++) {
			highestWeight[i] = in.nextDouble();
		}
		
		return highestWeight;
	}
	
	//Define a representation of SU(n,1) given end user's input.
	private static SpecialUnitaryRepresentation getSpecialUnitaryRepresentation() {
		System.out.print(queryRank);
		rank = in.nextInt();
		
		System.out.print(queryMType);
		double[] mType = getHighestWeight(rank-1,2);

		System.out.print(queryAType);
		double aType = in.nextDouble();
		
		return new SpecialUnitaryRepresentation(rank, mType, aType);
	}
	
	//Define an irrep of K=S(U(n)xU(1)) given end user's input.
	private static SpecialUnitaryKType getKType() {
		System.out.print(queryKType);
		double[] kType = getHighestWeight(rank,2);
		double[] kTypeExtension = Arrays.copyOf(kType, rank+1);
		return new SpecialUnitaryKType(rank, kTypeExtension);
	}
	
	public static void printKTypeCharacter(SpecialUnitaryKType K, int i) {
		String myKType = Arrays.toString(K.getWeylRepresentative(i));
		System.out.println("W. Chamber ("+i+"), K-Type Representative: "+myKType);
	}
	
	public static boolean areSameCharacters(SpecialUnitaryRepresentation V, SpecialUnitaryKType K, int j) {
		double[] infChar = V.getWeylRepresentative();
		double[] KTypeChar = K.getWeylRepresentative(j);
		int L = 0;
		boolean isFundamental = true;
		
		if(infChar.length==KTypeChar.length) L=infChar.length;
		else return false;
		
		for(int i = 0; i<L; i++) {
			if(infChar[i]!=KTypeChar[i]) isFundamental = false;
		}
		
		return isFundamental;
	}
	
	public static void printFundamentalKType(SpecialUnitaryRepresentation V, SpecialUnitaryKType K) {
		int count = 0;
		for(int i = 0; i<rank+1; i++) {
			if(areSameCharacters(V,K,i)) {
				count++;
				printKTypeCharacter(K,i);
			}
		}
		if(count > 0) System.out.println("K-Type is fundamental.");
		else System.out.println("K-Type is not fundamental.");
	}
	
	public static void main(String[] args) {
		SpecialUnitaryRepresentation V = getSpecialUnitaryRepresentation();
		V.printInfinitesimalCharacter();
		V.printWeylRepresentative();
		V.printReducibility();
		
		SpecialUnitaryKType K = getKType();
		printFundamentalKType(V,K);
		
		in.close();
	}

}
