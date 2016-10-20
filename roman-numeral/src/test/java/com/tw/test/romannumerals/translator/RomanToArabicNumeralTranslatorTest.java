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

        assertEquals( new Integer(2006), testObject.translate( "MMVI" ) );
        assertEquals( new Integer(1), testObject.translate( "I" ) );
        assertEquals( new Integer(2), testObject.translate( "II" ) );
        assertEquals( new Integer(3), testObject.translate( "III" ) );
        assertEquals( new Integer(4), testObject.translate( "IV" ) );
        assertEquals( new Integer(5), testObject.translate( "V" ) );
        assertEquals( new Integer(6), testObject.translate( "VI" ) );
        assertEquals( new Integer(7), testObject.translate( "VII" ) );
        assertEquals( new Integer(8), testObject.translate( "VIII" ) );
        assertEquals( new Integer(9), testObject.translate( "IX" ) );
        assertEquals( new Integer(10), testObject.translate( "X" ) );
        assertEquals( new Integer(11), testObject.translate( "XI" ) );
        assertEquals( new Integer(12), testObject.translate( "XII" ) );
        assertEquals( new Integer(13), testObject.translate( "XIII" ) );
        assertEquals( new Integer(14), testObject.translate( "XIV" ) );
        assertEquals( new Integer(15), testObject.translate( "XV" ) );
        assertEquals( new Integer(16), testObject.translate( "XVI" ) );
        assertEquals( new Integer(17), testObject.translate( "XVII" ) );
        assertEquals( new Integer(18), testObject.translate( "XVIII" ) );
        assertEquals( new Integer(19), testObject.translate( "XIX" ) );
        assertEquals( new Integer(20), testObject.translate( "XX" ) );
        assertEquals( new Integer(30), testObject.translate( "XXX" ) );
        assertEquals( new Integer(40), testObject.translate( "XL" ) );
        assertEquals( new Integer(50), testObject.translate( "L" ) );
        assertEquals( new Integer(60), testObject.translate( "LX" ) );
        assertEquals( new Integer(70), testObject.translate( "LXX" ) );
        assertEquals( new Integer(80), testObject.translate( "LXXX" ) );
        assertEquals( new Integer(90), testObject.translate( "XC" ) );
        assertEquals( new Integer(99), testObject.translate( "XCIX" ) );
        assertEquals( new Integer(100), testObject.translate( "C" ) );
        assertEquals( new Integer(101), testObject.translate( "CI" ) );
        assertEquals( new Integer(102), testObject.translate( "CII" ) );
        assertEquals( new Integer(199), testObject.translate( "CXCIX" ) );
        assertEquals( new Integer(200), testObject.translate( "CC" ) );
        assertEquals( new Integer(300), testObject.translate( "CCC" ) );
        assertEquals( new Integer(400), testObject.translate( "CD" ) );
        assertEquals( new Integer(500), testObject.translate( "D" ) );
        assertEquals( new Integer(600), testObject.translate( "DC" ) );
        assertEquals( new Integer(800), testObject.translate( "DCCC" ) );
        assertEquals( new Integer(900), testObject.translate( "CM" ) );
        assertEquals( new Integer(1000), testObject.translate( "M" ) );
        assertEquals( new Integer(1400), testObject.translate( "MCD" ) );
        assertEquals( new Integer(1437), testObject.translate( "MCDXXXVII" ) );
        assertEquals( new Integer(1500), testObject.translate( "MD" ) );
        assertEquals( new Integer(1800), testObject.translate( "MDCCC" ) );
        assertEquals( new Integer(1880), testObject.translate( "MDCCCLXXX" ) );
        assertEquals( new Integer(1900), testObject.translate( "MCM" ) );
        assertEquals( new Integer(2000), testObject.translate( "MM" ) );
        assertEquals( new Integer(3000), testObject.translate( "MMM" ) );
        assertEquals( new Integer(3333), testObject.translate( "  MMMCCCXXXIII  " ) );
        assertEquals( null, testObject.translate( "mmm" ) );
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
