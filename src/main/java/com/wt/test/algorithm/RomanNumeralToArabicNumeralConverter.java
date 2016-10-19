/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.wt.test.algorithm;

import com.wt.test.data.RomanNumerals;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanNumeralToArabicNumeralConverter
{

    public static boolean validateRomanNumeral( String romanNumeral )
    {
        return RomanNumerals.checkInvalidRomanNumeralChar( romanNumeral ) &&
            RomanNumerals.checkInvalidRomanNumeralCombination( romanNumeral ) &&
            RomanNumerals.checkOnlyOneSubtractedNumeral( romanNumeral );
    }

    public static Integer convert( String input )
    {
        String romanNumeral = input.trim();
        int length = romanNumeral.length();
        int sum = 0;

        if( !validateRomanNumeral( romanNumeral ) || romanNumeral.isEmpty() )
        {
            return null;
        }

        char[] arr = romanNumeral.toCharArray();

        if( length == 1 )
        {
            sum = sum + RomanNumerals.getValue( arr[0] );
            return sum;
        }

        for( int i = 0; i < length - 1; i++ )
        {
            if( RomanNumerals.getValue( arr[i] ) < RomanNumerals.getValue( arr[i + 1] ) )
            {
                sum = sum + RomanNumerals.getValue( arr[i + 1] ) - RomanNumerals.getValue( arr[i] );
                i++;
                if( i == length - 1 )
                {
                    return sum;
                }
            }
            else
            {
                sum = sum + RomanNumerals.getValue( arr[i] );
            }
        }

        sum = sum + RomanNumerals.getValue( arr[length - 1] );

        return sum;
    }

}
