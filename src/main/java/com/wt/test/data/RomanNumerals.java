/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.wt.test.data;

import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanNumerals
{
    public static final String YOU_INPUT_AN_INVALID_ROMAN_NUMERAL_PIECE = "you input an invalid roman numeral piece: ";

    public static ImmutableMap<Character, Integer> romanNumeralValueMap = ImmutableMap.<Character, Integer> builder()
        .put( 'I', 1 )
        .put( 'V', 5 )
        .put( 'X', 10 )
        .put( 'L', 50 )
        .put( 'C', 100 )
        .put( 'D', 500 )
        .put( 'M', 1000 )
        .build();

    private static ImmutableList.Builder<String> unAcceptableCombinationsBuilder = ImmutableList.<String>builder();

    public static ImmutableList<String> unAcceptableCombinations = createUnAcceptableCombinations();

    private static ImmutableList<String> createUnAcceptableCombinations()
    {
        symbolRepeatableRule();
        subtractedRule();
        return unAcceptableCombinationsBuilder.build();
    }
    /*
    * The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
    * (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.)
    * "D", "L", and "V" can never be repeated.
    * */
    private static void symbolRepeatableRule()
    {
        unAcceptableCombinationsBuilder
                        .add("IIII")//can be repeated three times
                        .add("VV") //can never be repeated.
                        .add("XXXX")//can be repeated three times
                        .add("LL")//can never be repeated.
                        .add("CCCC")//can be repeated three times
                        .add("DD")//can never be repeated.
                        .add("MMMM");//can be repeated three times
    }

    /**
     * "I" can be subtracted from "V" and "X" only.
     * "X" can be subtracted from "L" and "C" only.
     * "C" can be subtracted from "D" and "M" only.
     * "V", "L", and "D" can never be subtracted.
     * Only one small-value symbol may be subtracted from any large-value symbol.
     */
    private static void subtractedRule()
    {
        unAcceptableCombinationsBuilder
                        .add( "IL" )//"I" can be subtracted from "V" and "X" only.
                        .add( "IC" )
                        .add( "ID" )
                        .add( "IM" )
                        .add( "XD" )//"X" can be subtracted from "L" and "C" only.
                        .add( "XM" )
                        .add( "VX" )//"V", "L", and "D" can never be subtracted.
                        .add( "VL" )
                        .add( "VC" )
                        .add( "VD" )
                        .add( "VM" )
                        .add( "LC" )//"V", "L", and "D" can never be subtracted.
                        .add( "LD" )
                        .add( "LM" )
                        .add( "DM" );
    }

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
            int current = getValue( arr[i] );
            int prev = getValue( arr[i - 1] );
            int prevPrev = getValue( arr[i - 2] );
            if( prev <= current && prevPrev < current )
            {
                System.out.println( YOU_INPUT_AN_INVALID_ROMAN_NUMERAL_PIECE + arr[i-2] + arr[i-1] + arr[i] );
                return false;
            }
        }

        return true;
    }

    public static Integer getValue( char key )
    {
        return RomanNumerals.romanNumeralValueMap.get( key );
    }

}
