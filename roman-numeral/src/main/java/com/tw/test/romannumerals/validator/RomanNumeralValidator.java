/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.romannumerals.validator;

import com.google.common.base.CharMatcher;
import com.tw.test.romannumerals.dictionary.RomanNumerals;

/**
 * @author Lex Li
 * @date 20/10/2016
 */
public class RomanNumeralValidator
{
    public static final String YOU_INPUT_AN_INVALID_ROMAN_NUMERAL_PIECE = "you input an invalid roman numeral piece: ";

    public static boolean checkInvalidRomanNumeralChar( String romanNumeral )
    {
        return CharMatcher.anyOf( "IVXLCDM" ).matchesAllOf( romanNumeral );
    }

    public static boolean checkInvalidRomanNumeralCombination( String romanNumeral )
    {
        for( String comb : RomanNumerals.unAcceptableCombinations )
        {
            if( romanNumeral.contains( comb ) )
            {
                System.out.println( YOU_INPUT_AN_INVALID_ROMAN_NUMERAL_PIECE + comb );
                return false;
            }
        }
        return true;
    }

    public static boolean checkOnlyOneSubtractedNumeral( String romanNumeral )
    {
        if( romanNumeral.length() < 3 )
        {
            return true;
        }

        char[] arr = romanNumeral.toCharArray();
        for( int i = 2; i < romanNumeral.length(); i++ )
        {
            int current = RomanNumerals.getValue( arr[i] );
            int prev = RomanNumerals.getValue( arr[i - 1] );
            int prevPrev = RomanNumerals.getValue( arr[i - 2] );
            if( prev <= current && prevPrev < current )
            {
                System.out.println( YOU_INPUT_AN_INVALID_ROMAN_NUMERAL_PIECE + arr[i-2] + arr[i-1] + arr[i] );
                return false;
            }
        }

        return true;
    }
}
