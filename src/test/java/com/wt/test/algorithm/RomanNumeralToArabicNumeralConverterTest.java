package com.wt.test.algorithm;

import junit.framework.TestCase;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanNumeralToArabicNumeralConverterTest
    extends TestCase
{
    private RomanNumeralToArabicNumeralConverter testObj;

    public void testConvert() throws Exception
    {

        assertEquals( (int)testObj.convert( "MMVI" ), 2006 );
        assertEquals( (int)testObj.convert( "I" ), 1 );
        assertEquals( (int)testObj.convert( "II" ), 2 );
        assertEquals( (int)testObj.convert( "III" ), 3 );
        assertEquals( (int)testObj.convert( "IV" ), 4 );
        assertEquals( (int)testObj.convert( "V" ), 5 );
        assertEquals( (int)testObj.convert( "VI" ), 6 );
        assertEquals( (int)testObj.convert( "VII" ), 7 );
        assertEquals( (int)testObj.convert( "VIII" ), 8 );
        assertEquals( (int)testObj.convert( "IX" ), 9 );
        assertEquals( (int)testObj.convert( "X" ), 10 );
        assertEquals( (int)testObj.convert( "XI" ), 11 );
        assertEquals( (int)testObj.convert( "XII" ), 12 );
        assertEquals( (int)testObj.convert( "XIII" ), 13 );
        assertEquals( (int)testObj.convert( "XIV" ), 14 );
        assertEquals( (int)testObj.convert( "XV" ), 15 );
        assertEquals( (int)testObj.convert( "XVI" ), 16 );
        assertEquals( (int)testObj.convert( "XVII" ), 17 );
        assertEquals( (int)testObj.convert( "XVIII" ), 18 );
        assertEquals( (int)testObj.convert( "XIX" ), 19 );
        assertEquals( (int)testObj.convert( "XX" ), 20 );
        assertEquals( (int)testObj.convert( "XXX" ), 30 );
        assertEquals( (int)testObj.convert( "XL" ), 40 );
        assertEquals( (int)testObj.convert( "L" ), 50 );
        assertEquals( (int)testObj.convert( "LX" ), 60 );
        assertEquals( (int)testObj.convert( "LXX" ), 70 );
        assertEquals( (int)testObj.convert( "LXXX" ), 80 );
        assertEquals( (int)testObj.convert( "XC" ), 90 );
        assertEquals( (int)testObj.convert( "XCIX" ), 99 );
        assertEquals( (int)testObj.convert( "C" ), 100 );
        assertEquals( (int)testObj.convert( "CI" ), 101 );
        assertEquals( (int)testObj.convert( "CII" ), 102 );
        assertEquals( (int)testObj.convert( "CXCIX" ), 199 );
        assertEquals( (int)testObj.convert( "CC" ), 200 );
        assertEquals( (int)testObj.convert( "CCC" ), 300 );
        assertEquals( (int)testObj.convert( "CD" ), 400 );
        assertEquals( (int)testObj.convert( "D" ), 500 );
        assertEquals( (int)testObj.convert( "DC" ), 600 );
        assertEquals( (int)testObj.convert( "DCCC" ), 800 );
        assertEquals( (int)testObj.convert( "CM" ), 900 );
        assertEquals( (int)testObj.convert( "M" ), 1000 );
        assertEquals( (int)testObj.convert( "MCD" ), 1400 );
        assertEquals( (int)testObj.convert( "MCDXXXVII" ), 1437 );
        assertEquals( (int)testObj.convert( "MD" ), 1500 );
        assertEquals( (int)testObj.convert( "MDCCC" ), 1800 );
        assertEquals( (int)testObj.convert( "MDCCCLXXX" ), 1880 );
        assertEquals( (int)testObj.convert( "MCM" ), 1900 );
        assertEquals( (int)testObj.convert( "MM" ), 2000 );
        assertEquals( (int)testObj.convert( "MMM" ), 3000 );
        assertEquals( (int)testObj.convert( "  MMMCCCXXXIII  " ), 3333 );
        assertEquals( testObj.convert( "mmm" ), null );
    }

    public void testValidateRomanNumeral() throws Exception
    {
        assertEquals( testObj.validateRomanNumeral( "MMVI" ), true );
        assertEquals( testObj.validateRomanNumeral( "A" ), false );
        assertEquals( testObj.validateRomanNumeral( "B" ), false );
        assertEquals( testObj.validateRomanNumeral( "@#" ), false );
        assertEquals( testObj.validateRomanNumeral( "AMMVI" ), false );
        assertEquals( testObj.validateRomanNumeral( "III" ), true );
        assertEquals( testObj.validateRomanNumeral( "IIII" ), false );
        assertEquals( testObj.validateRomanNumeral( "VVVV" ), false );
        assertEquals( testObj.validateRomanNumeral( "MMMM" ), false );
        assertEquals( testObj.validateRomanNumeral( "IIV" ), false );
        assertEquals( testObj.validateRomanNumeral( "IXX" ), false );
        assertEquals( testObj.validateRomanNumeral( "VIX" ), false );
        assertEquals( testObj.validateRomanNumeral( "CDM" ), false );
        assertEquals( testObj.validateRomanNumeral( "mmm" ), false );
    }
}
