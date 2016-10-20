/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.parse.engine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

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
public class SemanticParseEngineImpl
    implements SemanticParseEngine
{
    public static final String IS_SEPERATOR = " is ";
    public static final String INVALID_ROMAN_SYMBOL = "Z";
    public static final String WHITESPACE_STRING = " ";
    private final NumeralTranslator numeralTranslator;
    private final HashMap<String, Character> intergalacticToRomanNumeralMap;
    private final HashMap<String, Float> materialValueMap;

    public SemanticParseEngineImpl( NumeralTranslator numeralTranslator )
    {
        intergalacticToRomanNumeralMap = Maps.newHashMap();
        materialValueMap = Maps.newHashMap();
        this.numeralTranslator = numeralTranslator;
    }

    public ImmutableMap<String, Character> getIntergalacticToRomanNumeralMap()
    {
        return ImmutableMap.copyOf( intergalacticToRomanNumeralMap );
    }

    public ImmutableMap<String, Float> getMaterialValueMap()
    {
        return ImmutableMap.copyOf( materialValueMap );
    }

    @Override
    public ParseResult parse( String input )
    {
        String strToBeParsed = adjustFormat( input );

        if( !isGrammaOkForParse(strToBeParsed) )
        {
            return ParseResult.failure();
        }

        String lowerCaseInput = strToBeParsed.toLowerCase();

        if( lowerCaseInput.contains( "how much" ) )
        {
            return parseValueCalculationFormula( strToBeParsed );
        }
        else if( lowerCaseInput.contains( "how many credits" ) )
        {
            return parseCreditsCalculationFormula( strToBeParsed );
        }
        else if( lowerCaseInput.contains( "credits" ) )
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

        String formulaInfo = requestPartials[1];
        String[] formulaInfoArr = formulaInfo.split( WHITESPACE_STRING );
        String materialName = formulaInfoArr[formulaInfoArr.length - 1];
        Float materialCredit = materialValueMap.get( materialName );
        if( materialCredit == null )
        {
            return ParseResult.failure();
        }
        formulaInfoArr[formulaInfoArr.length - 1] = "";
        String formula = Joiner.on( " " ).join( formulaInfoArr ).trim();
        String romanNumeral = intergalacticFormulaToRomanNumerals( formula );
        Optional<Integer> numberOfMaterial = numeralTranslator.translate( romanNumeral );
        if( !numberOfMaterial.isPresent() )
        {
            return ParseResult.failure();
        }

        float creditsValue = numberOfMaterial.get() * materialCredit;

        return new ParseResult( true, formulaInfo + IS_SEPERATOR + creditsValue + " Credits" );
    }

    private ParseResult parseMaterialCredits( String strToBeParsed )
    {
        String[] requestPartials = strToBeParsed.split( IS_SEPERATOR );

        String materialInfo = requestPartials[0];
        String creditsInfo = requestPartials[1];

        String[] materialInfoArr = materialInfo.split( WHITESPACE_STRING );
        String materialName = materialInfoArr[materialInfoArr.length - 1];
        materialInfoArr[materialInfoArr.length - 1] = "";
        String formula = Joiner.on( " " ).join( materialInfoArr ).trim();
        String romanNumeral = intergalacticFormulaToRomanNumerals( formula );
        Optional<Integer> numberOfMaterial = numeralTranslator.translate( romanNumeral );
        if( !numberOfMaterial.isPresent() )
        {
            return ParseResult.failure();
        }

        String[] creditsInfoArr = creditsInfo.split( WHITESPACE_STRING );
        if( creditsInfoArr.length != 2 || !CharMatcher.DIGIT.matchesAllOf( creditsInfoArr[0] ) ||
            !creditsInfoArr[1].toLowerCase().equals( "credits" ) )
        {
            return ParseResult.failure();
        }

        float materialValue = Float.valueOf( creditsInfoArr[0] ) / numberOfMaterial.get();
        materialValueMap.put( materialName, materialValue );

        return ParseResult.success();
    }

    private ParseResult parseValueCalculationFormula( String calculationRequest )
    {
        String[] requestPartials = calculationRequest.split( IS_SEPERATOR );

        String formula = requestPartials[1];

        String romanNumeral = intergalacticFormulaToRomanNumerals( formula );

        Optional value = numeralTranslator.translate( romanNumeral );

        if( !value.isPresent() )
        {
            return ParseResult.failure();
        }

        return new ParseResult(
            true, formula + IS_SEPERATOR + value.get().toString() );
    }

    private ParseResult parseIntergalacticToRomanMap( String map )
    {
        String[] remapInput = map.split( IS_SEPERATOR );

        String mappedRomanSymbol = remapInput[1].trim();
        if( mappedRomanSymbol.length() != 1 ||
            !RomanNumeralValidator.checkInvalidRomanNumeralChar( mappedRomanSymbol ) )
        {
            return ParseResult.failure();
        }

        String intergalacticSymbol = remapInput[0].trim();

        intergalacticToRomanNumeralMap.put( intergalacticSymbol, mappedRomanSymbol.charAt( 0 ) );

        return ParseResult.success();
    }

    private String adjustFormat( String input )
    {
        String withoutOtherSymbol = CharMatcher.JAVA_LETTER_OR_DIGIT.or( CharMatcher.WHITESPACE ).retainFrom( input );
        return CharMatcher.WHITESPACE.trimAndCollapseFrom( withoutOtherSymbol, ' ' );
    }

    private String intergalacticFormulaToRomanNumerals( String formula )
    {
        final Iterable<String> symbols = Splitter.on( WHITESPACE_STRING ).trimResults().split( formula );
        Collection<String> symbolList = Lists.newArrayList( symbols );
        Collection<String> romanSymbolList = Collections2.transform( symbolList, new Function<String, String>()
        {
            public String apply( String input )
            {
                Character result = intergalacticToRomanNumeralMap.get( input );
                // When get the unrecognized intergalactic symbol, output an invalid roman symbol
                return result == null ? INVALID_ROMAN_SYMBOL : result.toString();
            }
        } );
        return Joiner.on( "" ).join( romanSymbolList );
    }

    private boolean isGrammaOkForParse( String toBeCheck )
    {
        return toBeCheck.toLowerCase().contains( IS_SEPERATOR ) && toBeCheck.split( IS_SEPERATOR ).length == 2;
    }

}
