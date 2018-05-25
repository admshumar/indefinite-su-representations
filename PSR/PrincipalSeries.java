package PSR;

interface PrincipalSeries {
	/* A principal series representation of a real reductive group G is
	 * (almost always) an infinite-dimensional vector space V with a 
	 * linear action of G on V. These representations carry lots of 
	 * information, and it is useful to reduce as much of it as possible.
	 * 
	 * Every principal series representation of a real reductive group
	 * has an invariant called the "infinitesimal character". It essentially
	 * is a finite list of real numbers, and we use it in this package 
	 * to distinguish various principal series representations from 
	 * each other. The construction of the infinitesimal character 
	 * depends on the real reductive group. The infinitesimal character
	 * will always be an array of doubles.
	 */
	double[] makeInfinitesimalCharacter();
	
	/* The infinitesimal character of a principal series representation 
	 * of G is determined up to permutation by G's Weyl group. The following 
	 * interface method uses the Weyl group to permute the coefficients
	 * of the infinitesimal character into a more pleasing form. (For example,
	 * in the case G=SU(n,1), the permutation places the coefficients in
	 * weakly increasing order. The point is to make the infinitesimal 
	 * character as readable as possible.)
	 */
	double[] getWeylRepresentative();
}
