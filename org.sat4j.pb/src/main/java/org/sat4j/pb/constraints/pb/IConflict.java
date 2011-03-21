/*******************************************************************************
 * SAT4J: a SATisfiability library for Java Copyright (C) 2004-2008 Daniel Le Berre
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU Lesser General Public License Version 2.1 or later (the
 * "LGPL"), in which case the provisions of the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of the LGPL, and not to allow others to use your version of
 * this file under the terms of the EPL, indicate your decision by deleting
 * the provisions above and replace them with the notice and other provisions
 * required by the LGPL. If you do not delete the provisions above, a recipient
 * may use your version of this file under the terms of the EPL or the LGPL.
 * 
 * Based on the pseudo boolean algorithms described in:
 * A fast pseudo-Boolean constraint solver Chai, D.; Kuehlmann, A.
 * Computer-Aided Design of Integrated Circuits and Systems, IEEE Transactions on
 * Volume 24, Issue 3, March 2005 Page(s): 305 - 317
 * 
 * and 
 * Heidi E. Dixon, 2004. Automating Pseudo-Boolean Inference within a DPLL 
 * Framework. Ph.D. Dissertation, University of Oregon.
 *******************************************************************************/
package org.sat4j.pb.constraints.pb;

import java.math.BigInteger;

import org.sat4j.minisat.core.VarActivityListener;

public interface IConflict extends IDataStructurePB {

	/**
	 * Effectue une resolution avec une contrainte PB. Met a jour le Conflict.
	 * 
	 * @param cpb
	 *            contrainte avec laquelle on va faire la resolution
	 * @param litImplied
	 *            litteral devant etre resolu
	 * @param val
	 *            TODO
	 * @return la mise a jour du degre
	 */
	BigInteger resolve(PBConstr cpb, int litImplied, VarActivityListener val);

	BigInteger slackConflict();

	boolean isAssertive(int dl);

	/**
	 * Reduction d'une contrainte On supprime un litteral non assigne
	 * prioritairement, vrai sinon. En aucun cas on ne supprime litImplied.
	 * 
	 * @return mise a jour du degre
	 */
	public BigInteger reduceInConstraint(IWatchPb wpb,
			final BigInteger[] coefsBis, final int indLitImplied,
			final BigInteger degreeBis);

	/**
	 * retourne le niveau de backtrack : c'est-?-dire le niveau le plus haut
	 * pour lequel la contrainte est assertive
	 * 
	 * @param maxLevel
	 *            le plus bas niveau pour lequel la contrainte est assertive
	 * @return the highest level (smaller int) for which the constraint is
	 *         assertive.
	 */
	public int getBacktrackLevel(int maxLevel);

	public void updateSlack(int level);

	public boolean slackIsCorrect(int decisionLevel);

}
