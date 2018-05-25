package PSR;

import java.util.Arrays;

/*
 * This class employs Kraljevic's algorithms for determining whether
 * a given principal series representation of SU(n,1) is irreducible,
 * whether it is unitary, and where a representation in the unitary
 * dual occurs if it does not contain a full principal series.
 */

public class SpecialUnitaryRepresentation implements PrincipalSeries{
	/*
	 * rank: rank of SU(n,1), hence is equal to n.
	 * halfRank: (0.5)*rank, a quantity that appears in many formulae.
	 * mType: highest weight of an irreducible representation (irrep) 
	 * of M in SU(n,1).
	 * aType: weight of the action of A on the irrep that is described
	 * by mType.
	 * infinitesimalCharacter: infinitesimal character of the principal 
	 * series induced by (mType, aType).
	 */
	private int rank;
	private double halfRank;
	private double[] mType;
	private double aType;
	private double[] infinitesimalCharacter;
	
	String showInfinitesimalCharacter = "Infinitesimal character: ";
	String showWeylRepresentative = "W. orbit representative: ";
	
	//Get methods.
	public double[] getMType() {
		return this.mType;
	}
	
	public double getAType() {
		return this.aType;
	}
	
	public double[] getInfinitesimalCharacter() {
		return this.infinitesimalCharacter;
	}
	
	//Check for reducibility of a principal series. This amounts
	//to checking whether certain differences of array elements
	//are equal to non-zero integers. See Kraljevic p.438 Proposition 1.
	public boolean isReducible() {
		boolean condition1 = true;
		boolean condition2 = true;
		int L = this.infinitesimalCharacter.length;
		
		for(int j = 1; j<L-1; j++) {
			double m = this.infinitesimalCharacter[0]-this.infinitesimalCharacter[j];
			if(m!=Math.floor(m) || m==0) {
				condition1 = false;
				break;
			}
		}
		
		for(int j = 1; j<L-1; j++) {
			double m = this.infinitesimalCharacter[L-1]-this.infinitesimalCharacter[j];
			if(m!=Math.floor(m) || m==0) {
				condition2 = false;
				break;
			}
		}
		
		return(condition1 || condition2);	
	}

	
	//Implementations of abstract methods from Interface PrincipalSeries.
	
	@Override
	//For a general real reductive group, getWeylRepresentative() would
	//involve a series of sophisticated permutations. In the case of the
	//real reductive group being SU(n,1), we need only sort the infinitesimal
	//character's coefficients in weakly increasing order.
	public double[] getWeylRepresentative() {
		double[] weylRep = Arrays.copyOf(this.infinitesimalCharacter,this.infinitesimalCharacter.length);
		Arrays.sort(weylRep);
		return weylRep;
	}
	
	@Override
	public double[] makeInfinitesimalCharacter() {
		double[] infinitesimalCharacter = new double[rank+1];
		int L = infinitesimalCharacter.length;
		int mTypeSum = 0;
		
		//Sum of array elements of mType, which is incorporated
		//into the infinitesimal character.
		for(int i = 0; i<mType.length; i++) {
			mTypeSum += this.mType[i];
		}
		
		//Fill infinitesimalCharacter using Kraljevic p.437, unlabeled equation
		//at bottom. The formula here looks slightly different,
		//due to the way Java indexes its arrays.
		infinitesimalCharacter[0] = (0.5)*(this.aType - mTypeSum);
		for(int i = 1; i<L-1; i++) {
			infinitesimalCharacter[i] = this.mType[i-1] + halfRank - i;
		}
		infinitesimalCharacter[L-1] = (-0.5)*(this.aType + mTypeSum);
		
		return infinitesimalCharacter;
	}
	
	//Print methods for displaying the infinitesimal character
	//and Weyl orbit representative of a principal series representation.
	
	public void printInfinitesimalCharacter() {
		String myInfinitesimalCharacter = Arrays.toString(getInfinitesimalCharacter());
		System.out.println(showInfinitesimalCharacter+myInfinitesimalCharacter);
	}
	
	public void printWeylRepresentative() {
		String myWeylRepresentative = Arrays.toString(getWeylRepresentative());
		System.out.println(showWeylRepresentative+myWeylRepresentative);
	}
	
	//Print method for irreducibility.
	public void printReducibility() {
		System.out.print("Principal series is ");
		
		if(isReducible()) System.out.println("REDUCIBLE.");
		else System.out.println("irreducible.");
		
		System.out.println("");
	}
	
	//Class constructor.
	SpecialUnitaryRepresentation(int rank, double[] mType, double aType){
		this.rank = rank;
		this.halfRank = rank/((double) 2);
		this.mType = mType;
		this.aType = aType;
		this.infinitesimalCharacter = makeInfinitesimalCharacter();
	}
	
}
