/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 */

package org.biojava.bio.symbol;

import junit.framework.TestCase;

/**
 * Tests the CodonPrefTools class and
 * the CodonPref functionality.
 * @author David Huen
 * @since 1.3
 */

public class CodonPrefToolsTest extends TestCase
{

    public CodonPrefToolsTest(String name)
    {
	super(name);
    }

    protected void setUp() throws Exception
    {

    }

    public void testGetCodonPreference()
    {
        CodonPref testPref = CodonPrefTools.getCodonPreference(CodonPrefTools.DROSOPHILA_MELANOGASTER_NUCLEAR);
        assertNotNull(testPref);
    }

}