/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.wt.test.cli;

import java.util.Collection;
import java.util.HashMap;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wt.test.algorithm.RomanNumeralToArabicNumeralConverter;
import com.wt.test.data.RomanNumerals;

/**
 * @author Lex Li
 * @date 19/10/2016
 */
public class SemanticParseEngine
{
    public static final String IS_SEPERATOR = " is ";
    public HashMap<String, Character> intergalacticToRomanNumeralMap;
    public HashMap<String, Integer> materialValueMap;

    public SemanticParseEngine()
    {
        intergalacticToRomanNumeralMap = Maps.newHashMap();
        materialValueMap = Maps.newHashMap();
    }

    public ParseResult parse( String strToBeParsed )
    {
        if( !strToBeParsed.toLowerCase().contains( IS_SEPERATOR ) )
        {
            return ParseResult.failure();
        }

        if( strToBeParsed.toLowerCase().contains( "how much" ) )
        {
            return parseCalculationFormula( strToBeParsed );
        }
        else if( strToBeParsed.toLowerCase().contains( "how many credits" ) )
        {
            return ParseResult.failure();
        }
        else if( strToBeParsed.toLowerCase().contains( "credits" ) )
        {
            return parseMaterialCredits( strToBeParsed );
        }
        else
        {
            return parseIntergalacticToRomanMap( strToBeParsed );
        }
    }

    private ParseResult parseMaterialCredits( String strToBeParsed )
    {
        String[] requestPartials = strToBeParsed.split( IS_SEPERATOR );
        if( requestPartials.length != 2 )
        {
            return ParseResult.failure();
        }

        String materialInfo = adjustFormat( requestPartials[0] );
        String creditsInfo = adjustFormat( requestPartials[1] );

        return ParseResult.success();
    }

    public ImmutableMap<String, Character> getIntergalacticToRomanNumeralMap()
    {
        return ImmutableMap.copyOf( intergalacticToRomanNumeralMap );
    }

    public ImmutableMap<String, Integer> getMaterialValueMap()
    {
        return ImmutableMap.copyOf( materialValueMap );
    }

    private ParseResult parseCalculationFormula( String calculationRequest )
    {
        String[] requestPartials = calculationRequest.split( IS_SEPERATOR );

        if( requestPartials.length != 2 )
        {
            return ParseResult.failure();
        }

        String formula = adjustFormat( requestPartials[1] );

        String romanNumeral = formulaToRomanNumerals( formula );

        Integer value = RomanNumeralToArabicNumeralConverter.convert( romanNumeral );

        if( value == null )
        {
            return ParseResult.failure();
        }

        StringBuilder builder = new StringBuilder();
        return new ParseResult(
            true, builder.append( formula ).append( IS_SEPERATOR ).append( value.toString() ).toString() );
    }

    private String adjustFormat( String input )
    {
        return CharMatcher.JAVA_LETTER.or( CharMatcher.WHITESPACE ).retainFrom( input ).trim();
    }

    private String formulaToRomanNumerals( String formula )
    {
        final Iterable<String> symbols = Splitter.on( " " ).trimResults().split( formula );
        Collection<String> symbolList = Lists.newArrayList( symbols );
        Collection<String> romanSymbolList = Collections2.transform( symbolList, new Function<String, String>()
        {
            public String apply( String input )
            {
                Character result = intergalacticToRomanNumeralMap.get( input );
                return result == null ? "" : result.toString();
            }
        } );
        return Joiner.on( "" ).join( romanSymbolList );
    }

    private ParseResult parseIntergalacticToRomanMap( String map )
    {
        String[] remapInput = map.split( IS_SEPERATOR );

        if( remapInput.length != 2 )
        {
            return ParseResult.failure();
        }

        String mappedRomanNumeral = remapInput[1].trim();
        if( mappedRomanNumeral.length() != 1 || !RomanNumerals.checkInvalidRomanNumeralChar( mappedRomanNumeral ) )
        {
            return ParseResult.failure();
        }

        String intergalacticSymbol = remapInput[0].trim();

        intergalacticToRomanNumeralMap.put( intergalacticSymbol, mappedRomanNumeral.charAt( 0 ) );

        return ParseResult.success();
    }

}
