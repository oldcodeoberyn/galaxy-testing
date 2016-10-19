package com.wt.test.algorithm;

import junit.framework.TestCase;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanNumeralToArabicNumeralConverterTest
    extends TestCase
{

    public void testConvert() throws Exception
    {

        assertEquals( new Integer(2006), RomanNumeralToArabicNumeralConverter.convert( "MMVI" ) );
        assertEquals( new Integer(1), RomanNumeralToArabicNumeralConverter.convert( "I" ) );
        assertEquals( new Integer(2), RomanNumeralToArabicNumeralConverter.convert( "II" ) );
        assertEquals( new Integer(3), RomanNumeralToArabicNumeralConverter.convert( "III" ) );
        assertEquals( new Integer(4), RomanNumeralToArabicNumeralConverter.convert( "IV" ) );
        assertEquals( new Integer(5), RomanNumeralToArabicNumeralConverter.convert( "V" ) );
        assertEquals( new Integer(6), RomanNumeralToArabicNumeralConverter.convert( "VI" ) );
        assertEquals( new Integer(7), RomanNumeralToArabicNumeralConverter.convert( "VII" ) );
        assertEquals( new Integer(8), RomanNumeralToArabicNumeralConverter.convert( "VIII" ) );
        assertEquals( new Integer(9), RomanNumeralToArabicNumeralConverter.convert( "IX" ) );
        assertEquals( new Integer(10), RomanNumeralToArabicNumeralConverter.convert( "X" ) );
        assertEquals( new Integer(11), RomanNumeralToArabicNumeralConverter.convert( "XI" ) );
        assertEquals( new Integer(12), RomanNumeralToArabicNumeralConverter.convert( "XII" ) );
        assertEquals( new Integer(13), RomanNumeralToArabicNumeralConverter.convert( "XIII" ) );
        assertEquals( new Integer(14), RomanNumeralToArabicNumeralConverter.convert( "XIV" ) );
        assertEquals( new Integer(15), RomanNumeralToArabicNumeralConverter.convert( "XV" ) );
        assertEquals( new Integer(16), RomanNumeralToArabicNumeralConverter.convert( "XVI" ) );
        assertEquals( new Integer(17), RomanNumeralToArabicNumeralConverter.convert( "XVII" ) );
        assertEquals( new Integer(18), RomanNumeralToArabicNumeralConverter.convert( "XVIII" ) );
        assertEquals( new Integer(19), RomanNumeralToArabicNumeralConverter.convert( "XIX" ) );
        assertEquals( new Integer(20), RomanNumeralToArabicNumeralConverter.convert( "XX" ) );
        assertEquals( new Integer(30), RomanNumeralToArabicNumeralConverter.convert( "XXX" ) );
        assertEquals( new Integer(40), RomanNumeralToArabicNumeralConverter.convert( "XL" ) );
        assertEquals( new Integer(50), RomanNumeralToArabicNumeralConverter.convert( "L" ) );
        assertEquals( new Integer(60), RomanNumeralToArabicNumeralConverter.convert( "LX" ) );
        assertEquals( new Integer(70), RomanNumeralToArabicNumeralConverter.convert( "LXX" ) );
        assertEquals( new Integer(80), RomanNumeralToArabicNumeralConverter.convert( "LXXX" ) );
        assertEquals( new Integer(90), RomanNumeralToArabicNumeralConverter.convert( "XC" ) );
        assertEquals( new Integer(99), RomanNumeralToArabicNumeralConverter.convert( "XCIX" ) );
        assertEquals( new Integer(100), RomanNumeralToArabicNumeralConverter.convert( "C" ) );
        assertEquals( new Integer(101), RomanNumeralToArabicNumeralConverter.convert( "CI" ) );
        assertEquals( new Integer(102), RomanNumeralToArabicNumeralConverter.convert( "CII" ) );
        assertEquals( new Integer(199), RomanNumeralToArabicNumeralConverter.convert( "CXCIX" ) );
        assertEquals( new Integer(200), RomanNumeralToArabicNumeralConverter.convert( "CC" ) );
        assertEquals( new Integer(300), RomanNumeralToArabicNumeralConverter.convert( "CCC" ) );
        assertEquals( new Integer(400), RomanNumeralToArabicNumeralConverter.convert( "CD" ) );
        assertEquals( new Integer(500), RomanNumeralToArabicNumeralConverter.convert( "D" ) );
        assertEquals( new Integer(600), RomanNumeralToArabicNumeralConverter.convert( "DC" ) );
        assertEquals( new Integer(800), RomanNumeralToArabicNumeralConverter.convert( "DCCC" ) );
        assertEquals( new Integer(900), RomanNumeralToArabicNumeralConverter.convert( "CM" ) );
        assertEquals( new Integer(1000), RomanNumeralToArabicNumeralConverter.convert( "M" ) );
        assertEquals( new Integer(1400), RomanNumeralToArabicNumeralConverter.convert( "MCD" ) );
        assertEquals( new Integer(1437), RomanNumeralToArabicNumeralConverter.convert( "MCDXXXVII" ) );
        assertEquals( new Integer(1500), RomanNumeralToArabicNumeralConverter.convert( "MD" ) );
        assertEquals( new Integer(1800), RomanNumeralToArabicNumeralConverter.convert( "MDCCC" ) );
        assertEquals( new Integer(1880), RomanNumeralToArabicNumeralConverter.convert( "MDCCCLXXX" ) );
        assertEquals( new Integer(1900), RomanNumeralToArabicNumeralConverter.convert( "MCM" ) );
        assertEquals( new Integer(2000), RomanNumeralToArabicNumeralConverter.convert( "MM" ) );
        assertEquals( new Integer(3000), RomanNumeralToArabicNumeralConverter.convert( "MMM" ) );
        assertEquals( new Integer(3333), RomanNumeralToArabicNumeralConverter.convert( "  MMMCCCXXXIII  " ) );
        assertEquals( null, RomanNumeralToArabicNumeralConverter.convert( "mmm" ) );
    }

    public void testValidateRomanNumeral() throws Exception
    {
        assertEquals( true, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "MMVI" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "A" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "B" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "@#" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "AMMVI" ) );
        assertEquals( true, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "III" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "IIII" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "VVVV" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "MMMM" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "IIV" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "IXX" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "VIX" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "CDM" ) );
        assertEquals( false, RomanNumeralToArabicNumeralConverter.validateRomanNumeral( "mmm" ) );
    }
}
