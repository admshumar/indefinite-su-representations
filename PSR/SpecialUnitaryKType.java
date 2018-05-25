package PSR;

import java.util.Arrays;

public class SpecialUnitaryKType implements KType{
	/*
	 * rank: rank of K=S(U(n)xU(1)), hence rank=n
	 * hrank: (0.5)*rank, appears in character formulas
	 * highestWeight: the most important invariant of an irreducible
	 * representation of K
	 * WeylVector: half the sum of the positive roots of Lie(K)
	 * KTypeChar: central character constructed from above data. This
	 * is used to determine if a K-type is "fundamental" according to
	 * Kraljevic and is one step toward classifying certain constituents
	 * of the unitary dual of SU(n,1).
	 */
	int rank;
	double halfRank;
	double[] highestWeight; //q in Kraljevic
	double[][] weylVector; //rho_k in Kraljevic
	double[][] kTypeCharacter; //
	
	//rho_k-rho^j_P in Kraljevic:
	public double[] getWeylVector(int j) {
		double[] vector = new double[rank+1];
		for(int i = 0; i<rank+1; i++) {
			vector[i] = this.weylVector[i][j];
		}
		return vector;
	}
	
	public double[] getKTypeCharacter(int j) {
		double[] vector = new double[rank+1];
		for(int i = 0; i<rank+1; i++) {
			vector[i] = this.kTypeCharacter[i][j];
		}
		return vector;
	}
	
	public double[] getWeylRepresentative(int j) {
		double[] weylRep = Arrays.copyOf(getKTypeCharacter(j), getKTypeCharacter(j).length);
		Arrays.sort(weylRep);
		return weylRep;
	}
	
	public double[][] makeWeylVector() {
		double[][] WeylVector = new double[rank+1][rank+1];
		for(int j = 0; j<rank+1; j++) {
			for(int i = 0; i<j; i++) {
				WeylVector[i][j] = this.halfRank-1-i;
			}
			for(int i = j; i<rank; i++) {
				WeylVector[i][j] = this.halfRank-i;
			}
			WeylVector[rank][j] = j-this.halfRank;
		}
		return WeylVector;
	}
	
	//q+rho_k-rho^j_P in Kraljevic. For each integer j from 0 to rank,
	//we have a character defined by a KType with highest weight q. This
	//method stores all of these characters in a doubly indexed array.
	//Any character is obtained using the getKTypeCharacter method.
	public double[][] makeKTypeCharacter() {
		double[][] KTypeChar = new double[rank+1][rank+1];
		for(int j = 0; j<rank+1; j++) {
			for(int i = 0; i<rank+1; i++) {
				KTypeChar[i][j] = this.highestWeight[i]+this.weylVector[i][j];
			}
		}	
		return KTypeChar;
	}
	
	SpecialUnitaryKType(int rank, double[] highestWeight){
		this.rank = rank;
		this.halfRank = this.rank/((double) 2);
		this.highestWeight = highestWeight;
		this.weylVector = makeWeylVector();
		this.kTypeCharacter = makeKTypeCharacter();
	}
}
