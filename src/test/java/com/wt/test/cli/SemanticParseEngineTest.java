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

    public void testParse_given_value_formula_expect_calculation_result() throws Exception
    {
        testObj.parse( "glob is I" );
        testObj.parse( "prok is V" );
        testObj.parse( "pish is X" );
        testObj.parse( "tegj is L" );
        assertEquals( true, testObj.parse( "how much is pish tegj glob glob ?" ).isSuccess() );
        assertEquals( false, testObj.parse( "how much is pish lex tegj glob glob ?" ).isSuccess() );
        assertEquals(
            true,
            testObj.parse( "how much is pish tegj glob glob ?" ).getResult().equals( "pish tegj glob glob is 42" ) );
    }

    public void testParseMaterialCredits_given_credits_information_expect_store_to_materialValueMap() throws Exception
    {
        testObj.parse( "glob is I" );
        testObj.parse( "prok is V" );
        testObj.parse( "pish is X" );
        testObj.parse( "tegj is L" );
        assertEquals( true, testObj.parse( "glob glob Silver is 34 Credits" ).isSuccess() );
        assertEquals( true, testObj.getMaterialValueMap().get( "Silver" ).equals( 17.0f ) );
        assertEquals( true, testObj.parse( "glob prok Gold is 57800 Credits" ).isSuccess() );
        assertEquals( true, testObj.getMaterialValueMap().get( "Gold" ).equals( 14450.0f ) );
        assertEquals( true, testObj.parse( "pish pish Iron is 03910 Credits" ).isSuccess() );
        assertEquals( true, testObj.getMaterialValueMap().get( "Iron" ).equals( 195.5f ) );
        assertEquals( false, testObj.parse( "glob glob Silver Gold is 34 Credits" ).isSuccess() );
        assertEquals( false, testObj.parse( "glob glob Silver is 34 31 Credits" ).isSuccess() );
        assertEquals( false, testObj.parse( "glob glob Silver is 34 Credits Credits" ).isSuccess() );
    }

    public void testParseCreditsCalculationFormula_given_credits_formula_expect_calculation_result() throws Exception
    {
        testObj.parse( "glob is I" );
        testObj.parse( "prok is V" );
        testObj.parse( "pish is X" );
        testObj.parse( "tegj is L" );
        testObj.parse( "glob glob Silver is 34 Credits" );
        assertEquals(
            true,
            testObj.parse( "how many Credits is glob prok Silver ?" ).getResult()
                .equals( "glob prok Silver is 68.0 Credits" ) );
        testObj.parse( "glob prok Gold is 57800 Credits" );
        assertEquals(
            true,
            testObj.parse( "how many Credits is glob prok Gold ?" ).getResult()
                .equals( "glob prok Gold is 57800.0 Credits" ) );
        testObj.parse( "pish pish Iron is 03910 Credits" );
        assertEquals(
            true,
            testObj.parse( "how many Credits is glob prok Iron ?" ).getResult()
                .equals( "glob prok Iron is 782.0 Credits" ) );

        assertEquals( false, testObj.parse( "how many Credits is glob bingo prok Iron ?" ).isSuccess() );
        assertEquals( false, testObj.parse( "how many Credits is glob prok Iron Silver ?" ).isSuccess() );
    }
}
