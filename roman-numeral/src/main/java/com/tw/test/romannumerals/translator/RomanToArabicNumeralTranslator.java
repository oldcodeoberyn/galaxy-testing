/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.romannumerals.translator;

import com.tw.test.romannumerals.dictionary.RomanNumerals;
import com.tw.test.romannumerals.validator.RomanNumeralValidator;

/**
 * @author Lex Li
 * @date 18/10/2016
 */
public class RomanToArabicNumeralTranslator
    implements NumeralTranslator
{

    public boolean validateRomanNumeral( String romanNumeral )
    {
        return RomanNumeralValidator.checkInvalidRomanNumeralChar( romanNumeral ) &&
            RomanNumeralValidator.checkInvalidRomanNumeralCombination( romanNumeral ) &&
            RomanNumeralValidator.checkOnlyOneSubtractedNumeral( romanNumeral );
    }

    @Override
    public Integer translate( String input )
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
