/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.romannumerals.dictionary;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanNumerals
{
    public static ImmutableMap<Character, Integer> romanNumeralValueMap = ImmutableMap.<Character, Integer> builder()
        .put( 'I', 1 )
        .put( 'V', 5 )
        .put( 'X', 10 )
        .put( 'L', 50 )
        .put( 'C', 100 )
        .put( 'D', 500 )
        .put( 'M', 1000 )
        .build();

    private static ImmutableList.Builder<String> unAcceptableCombinationsBuilder = ImmutableList.builder();

    public static ImmutableList<String> unAcceptableCombinations = createUnAcceptableCombinations();

    private static ImmutableList<String> createUnAcceptableCombinations()
    {
        symbolRepeatableRule();
        subtractedRule();
        return unAcceptableCombinationsBuilder.build();
    }

    /*
     * The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. (They may appear four
     * times if the third and fourth are separated by a smaller value, such as XXXIX.) "D", "L", and "V" can never be
     * repeated.
     */
    private static void symbolRepeatableRule()
    {
        unAcceptableCombinationsBuilder
            .add( "IIII" )// can be repeated three times
            .add( "VV" ) // can never be repeated.
            .add( "XXXX" )// can be repeated three times
            .add( "LL" )// can never be repeated.
            .add( "CCCC" )// can be repeated three times
            .add( "DD" )// can never be repeated.
            .add( "MMMM" );// can be repeated three times
    }

    /**
     * "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted
     * from "D" and "M" only. "V", "L", and "D" can never be subtracted. Only one small-value symbol may be subtracted
     * from any large-value symbol.
     */
    private static void subtractedRule()
    {
        unAcceptableCombinationsBuilder
            .add( "IL" )// "I" can be subtracted from "V" and "X" only.
            .add( "IC" )
            .add( "ID" )
            .add( "IM" )
            .add( "XD" )// "X" can be subtracted from "L" and "C" only.
            .add( "XM" )
            .add( "VX" )// "V", "L", and "D" can never be subtracted.
            .add( "VL" )
            .add( "VC" )
            .add( "VD" )
            .add( "VM" )
            .add( "LC" )// "V", "L", and "D" can never be subtracted.
            .add( "LD" )
            .add( "LM" )
            .add( "DM" );
    }

    public static Integer getValue( char key )
    {
        return RomanNumerals.romanNumeralValueMap.get( key );
    }

}
