/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.parse.engine;

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
import com.tw.test.model.ParseResult;
import com.tw.test.romannumerals.translator.NumeralTranslator;
import com.tw.test.romannumerals.validator.RomanNumeralValidator;

/**
 * @author Lex Li
 * @date 19/10/2016
 */
public class SemanticParseEngineImpl implements SemanticParseEngine
{
    public static final String IS_SEPERATOR = " is ";
    public static final String INVALID_ROMAN_SYMBOL = "Z";
    private final NumeralTranslator numeralTranslator;
    private final HashMap<String, Character> intergalacticToRomanNumeralMap;
    private final HashMap<String, Float> materialValueMap;

    public SemanticParseEngineImpl( NumeralTranslator numeralTranslator  )
    {
        intergalacticToRomanNumeralMap = Maps.newHashMap();
        materialValueMap = Maps.newHashMap();
        this.numeralTranslator = numeralTranslator;
    }

    @Override
    public ParseResult parse( String input )
    {
        String strToBeParsed = adjustFormat( input );
        if( !strToBeParsed.toLowerCase().contains( IS_SEPERATOR ) )
        {
            return ParseResult.failure();
        }

        if( strToBeParsed.toLowerCase().contains( "how much" ) )
        {
            return parseValueCalculationFormula( strToBeParsed );
        }
        else if( strToBeParsed.toLowerCase().contains( "how many credits" ) )
        {
            return parseCreditsCalculationFormula( strToBeParsed );
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

    private ParseResult parseCreditsCalculationFormula( String calculationRequest )
    {
        String[] requestPartials = calculationRequest.split( IS_SEPERATOR );

        if( requestPartials.length != 2 )
        {
            return ParseResult.failure();
        }

        String formulaInfo = requestPartials[1];
        String[] formulaInfoArr = formulaInfo.split( " " );
        String materialName = formulaInfoArr[formulaInfoArr.length - 1];
        Float materialCredit = materialValueMap.get( materialName );
        if( materialCredit == null )
        {
            return ParseResult.failure();
        }
        formulaInfoArr[formulaInfoArr.length - 1] = "";
        String formula = Joiner.on( " " ).join( formulaInfoArr ).trim();
        String romanNumeral = formulaToRomanNumerals( formula );
        Integer numberOfMaterial = numeralTranslator.translate( romanNumeral );
        if( numberOfMaterial == null )
        {
            return ParseResult.failure();
        }

        float creditsValue = numberOfMaterial * materialCredit;

        return new ParseResult( true, formulaInfo + IS_SEPERATOR + creditsValue + " Credits" );
    }

    private ParseResult parseMaterialCredits( String strToBeParsed )
    {
        String[] requestPartials = strToBeParsed.split( IS_SEPERATOR );
        if( requestPartials.length != 2 )
        {
            return ParseResult.failure();
        }

        String materialInfo = requestPartials[0];
        String creditsInfo = requestPartials[1];

        String[] materialInfoArr = materialInfo.split( " " );
        String materialName = materialInfoArr[materialInfoArr.length - 1];
        materialInfoArr[materialInfoArr.length - 1] = "";
        String formula = Joiner.on( " " ).join( materialInfoArr ).trim();
        String romanNumeral = formulaToRomanNumerals( formula );
        Integer numberOfMaterial = numeralTranslator.translate( romanNumeral );
        if( numberOfMaterial == null )
        {
            return ParseResult.failure();
        }

        String[] creditsInfoArr = creditsInfo.split( " " );
        if( creditsInfoArr.length != 2 || !CharMatcher.DIGIT.matchesAllOf( creditsInfoArr[0] ) ||
            !creditsInfoArr[1].toLowerCase().equals( "credits" ) )
        {
            return ParseResult.failure();
        }

        float materialValue = Float.valueOf( creditsInfoArr[0] ) / numberOfMaterial;
        materialValueMap.put( materialName, materialValue );

        return ParseResult.success();
    }

    public ImmutableMap<String, Character> getIntergalacticToRomanNumeralMap()
    {
        return ImmutableMap.copyOf( intergalacticToRomanNumeralMap );
    }

    public ImmutableMap<String, Float> getMaterialValueMap()
    {
        return ImmutableMap.copyOf( materialValueMap );
    }

    private ParseResult parseValueCalculationFormula( String calculationRequest )
    {
        String[] requestPartials = calculationRequest.split( IS_SEPERATOR );

        if( requestPartials.length != 2 )
        {
            return ParseResult.failure();
        }

        String formula = requestPartials[1];

        String romanNumeral = formulaToRomanNumerals( formula );

        Integer value = numeralTranslator.translate( romanNumeral );

        if( value == null )
        {
            return ParseResult.failure();
        }

        return new ParseResult(
            true, formula + IS_SEPERATOR + value.toString() );
    }

    private String adjustFormat( String input )
    {
        return CharMatcher.JAVA_LETTER_OR_DIGIT.or( CharMatcher.WHITESPACE ).retainFrom( input ).trim();
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
                // When get the unrecognize intergalactic symbol, ouput an invalid roman symbol
                return result == null ? INVALID_ROMAN_SYMBOL : result.toString();
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
        if( mappedRomanNumeral.length() != 1 ||
            !RomanNumeralValidator.checkInvalidRomanNumeralChar( mappedRomanNumeral ) )
        {
            return ParseResult.failure();
        }

        String intergalacticSymbol = remapInput[0].trim();

        intergalacticToRomanNumeralMap.put( intergalacticSymbol, mappedRomanNumeral.charAt( 0 ) );

        return ParseResult.success();
    }

}
