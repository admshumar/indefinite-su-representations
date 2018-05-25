package PSR;

/*
 * In the representation theory of real reductive groups,
 * one tries to describe as many features of a representation
 * of a real reductive group G in terms of a maximal compact
 * subgroup K. There are many reasons for this: representations
 * of K are completely classified, are relatively easy to write
 * down, and provide a method for distinguishing representations
 * of G. One takes a representation of G and restricts it to K,
 * generally producing an infinite set of irreducible
 * representations of K in the process. Each such representation
 * of K is called a "K-type". This class defines a K-type for
 * SU(n,1).
 */

public interface KType {
	
	double[] getWeylVector(int j);
	double[] getKTypeCharacter(int j);
	double[] getWeylRepresentative(int j);
	
	double[][] makeWeylVector();
	double[][] makeKTypeCharacter();

}
