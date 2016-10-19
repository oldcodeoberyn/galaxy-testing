package com.wt.test.cli;

import junit.framework.TestCase;

/**
 * @author Lex Li
 * @date 19/10/2016
 */
public class SemanticParseEngineTest
    extends TestCase
{

    SemanticParseEngine testObj;

    public void setUp() throws Exception
    {
        super.setUp();
        testObj = new SemanticParseEngine();

    }

    public void testParse_given_illegal_input_expect_return_false() throws Exception
    {
        assertEquals( false, testObj.parse( "tex is B" ).isSuccess() );
        assertEquals( false, testObj.parse( "I am making a joke" ).isSuccess() );
    }

    public void testParse_given_correct_map_expect_store_map_correctly() throws Exception
    {
        assertEquals( true, testObj.parse( "glob is I" ).isSuccess() );
        assertEquals( true, testObj.getIntergalacticToRomanNumeralMap().get( "glob" ).equals( 'I' ) );
        assertEquals( true, testObj.parse( "prok is V" ).isSuccess() );
        assertEquals( true, testObj.getIntergalacticToRomanNumeralMap().get( "prok" ).equals( 'V' ) );
        assertEquals( true, testObj.parse( "pish is X" ).isSuccess() );
        assertEquals( true, testObj.getIntergalacticToRomanNumeralMap().get( "pish" ).equals( 'X' ) );
        assertEquals( true, testObj.parse( "tegj is L" ).isSuccess() );
        assertEquals( true, testObj.getIntergalacticToRomanNumeralMap().get( "tegj" ).equals( 'L' ) );
    }

    public void testParse_given_correct_fomula_expect_calculation_result() throws Exception
    {
        testObj.parse( "glob is I" );
        testObj.parse( "prok is V" );
        testObj.parse( "pish is X" );
        testObj.parse( "tegj is L" );
        assertEquals( true, testObj.parse( "how much is pish tegj glob glob ?" ).isSuccess() );
        assertEquals(
            true,
            testObj.parse( "how much is pish tegj glob glob ?" ).getResult().equals( "pish tegj glob glob is 42" ) );
    }

    public void testParseMaterialCredits_given_credits_infomration_expect_store_to_materialValueMap() throws Exception
    {
        testObj.parse( "glob is I" );
        testObj.parse( "prok is V" );
        testObj.parse( "pish is X" );
        testObj.parse( "tegj is L" );
        assertEquals( true, testObj.parse( "glob glob Silver is 34 Credits" ).isSuccess());
        assertEquals( true, testObj.getMaterialValueMap().get( "Silver" ).equals( 17 ) );

    }
}
