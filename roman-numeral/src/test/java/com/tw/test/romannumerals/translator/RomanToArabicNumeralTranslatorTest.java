package com.tw.test.romannumerals.translator;

import junit.framework.TestCase;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanToArabicNumeralTranslatorTest
    extends TestCase
{
    private RomanToArabicNumeralTranslator testObject;

    public void setUp() throws Exception
    {
        super.setUp();
        testObject = new RomanToArabicNumeralTranslator();

    }

    public void testConvert() throws Exception
    {

        assertEquals( new Integer( 2006 ), testObject.translate( "MMVI" ).get() );
        assertEquals( new Integer( 1 ), testObject.translate( "I" ).get() );
        assertEquals( new Integer( 2 ), testObject.translate( "II" ).get() );
        assertEquals( new Integer( 3 ), testObject.translate( "III" ).get() );
        assertEquals( new Integer( 4 ), testObject.translate( "IV" ).get() );
        assertEquals( new Integer( 5 ), testObject.translate( "V" ).get() );
        assertEquals( new Integer( 6 ), testObject.translate( "VI" ).get() );
        assertEquals( new Integer( 7 ), testObject.translate( "VII" ).get() );
        assertEquals( new Integer( 8 ), testObject.translate( "VIII" ).get() );
        assertEquals( new Integer( 9 ), testObject.translate( "IX" ).get() );
        assertEquals( new Integer( 10 ), testObject.translate( "X" ).get() );
        assertEquals( new Integer( 11 ), testObject.translate( "XI" ).get() );
        assertEquals( new Integer( 12 ), testObject.translate( "XII" ).get() );
        assertEquals( new Integer( 13 ), testObject.translate( "XIII" ).get() );
        assertEquals( new Integer( 14 ), testObject.translate( "XIV" ).get() );
        assertEquals( new Integer( 15 ), testObject.translate( "XV" ).get() );
        assertEquals( new Integer( 16 ), testObject.translate( "XVI" ).get() );
        assertEquals( new Integer( 17 ), testObject.translate( "XVII" ).get() );
        assertEquals( new Integer( 18 ), testObject.translate( "XVIII" ).get() );
        assertEquals( new Integer( 19 ), testObject.translate( "XIX" ).get() );
        assertEquals( new Integer( 20 ), testObject.translate( "XX" ).get() );
        assertEquals( new Integer( 30 ), testObject.translate( "XXX" ).get() );
        assertEquals( new Integer( 40 ), testObject.translate( "XL" ).get() );
        assertEquals( new Integer( 50 ), testObject.translate( "L" ).get() );
        assertEquals( new Integer( 60 ), testObject.translate( "LX" ).get() );
        assertEquals( new Integer( 70 ), testObject.translate( "LXX" ).get() );
        assertEquals( new Integer( 80 ), testObject.translate( "LXXX" ).get() );
        assertEquals( new Integer( 90 ), testObject.translate( "XC" ).get() );
        assertEquals( new Integer( 99 ), testObject.translate( "XCIX" ).get() );
        assertEquals( new Integer( 100 ), testObject.translate( "C" ).get() );
        assertEquals( new Integer( 101 ), testObject.translate( "CI" ).get() );
        assertEquals( new Integer( 102 ), testObject.translate( "CII" ).get() );
        assertEquals( new Integer( 199 ), testObject.translate( "CXCIX" ).get() );
        assertEquals( new Integer( 200 ), testObject.translate( "CC" ).get() );
        assertEquals( new Integer( 300 ), testObject.translate( "CCC" ).get() );
        assertEquals( new Integer( 400 ), testObject.translate( "CD" ).get() );
        assertEquals( new Integer( 500 ), testObject.translate( "D" ).get() );
        assertEquals( new Integer( 600 ), testObject.translate( "DC" ).get() );
        assertEquals( new Integer( 800 ), testObject.translate( "DCCC" ).get() );
        assertEquals( new Integer( 900 ), testObject.translate( "CM" ).get() );
        assertEquals( new Integer( 1000 ), testObject.translate( "M" ).get() );
        assertEquals( new Integer( 1400 ), testObject.translate( "MCD" ).get() );
        assertEquals( new Integer( 1437 ), testObject.translate( "MCDXXXVII" ).get() );
        assertEquals( new Integer( 1500 ), testObject.translate( "MD" ).get() );
        assertEquals( new Integer( 1800 ), testObject.translate( "MDCCC" ).get() );
        assertEquals( new Integer( 1880 ), testObject.translate( "MDCCCLXXX" ).get() );
        assertEquals( new Integer( 1900 ), testObject.translate( "MCM" ).get() );
        assertEquals( new Integer( 2000 ), testObject.translate( "MM" ).get() );
        assertEquals( new Integer( 3000 ), testObject.translate( "MMM" ).get() );
        assertEquals( new Integer( 3333 ), testObject.translate( "  MMMCCCXXXIII  " ).get() );
        assertEquals( false, testObject.translate( "mmm" ).isPresent() );
    }

    public void testValidateRomanNumeral() throws Exception
    {
        assertEquals( true, testObject.validateRomanNumeral( "MMVI" ) );
        assertEquals( false, testObject.validateRomanNumeral( "A" ) );
        assertEquals( false, testObject.validateRomanNumeral( "B" ) );
        assertEquals( false, testObject.validateRomanNumeral( "@#" ) );
        assertEquals( false, testObject.validateRomanNumeral( "AMMVI" ) );
        assertEquals( true, testObject.validateRomanNumeral( "III" ) );
        assertEquals( false, testObject.validateRomanNumeral( "IIII" ) );
        assertEquals( false, testObject.validateRomanNumeral( "VVVV" ) );
        assertEquals( false, testObject.validateRomanNumeral( "MMMM" ) );
        assertEquals( false, testObject.validateRomanNumeral( "IIV" ) );
        assertEquals( false, testObject.validateRomanNumeral( "IXX" ) );
        assertEquals( false, testObject.validateRomanNumeral( "VIX" ) );
        assertEquals( false, testObject.validateRomanNumeral( "CDM" ) );
        assertEquals( false, testObject.validateRomanNumeral( "mmm" ) );
    }
}
