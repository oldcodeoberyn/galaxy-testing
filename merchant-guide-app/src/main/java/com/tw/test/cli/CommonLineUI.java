/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.cli;

import com.tw.test.model.ParseResult;
import com.tw.test.parse.engine.SemanticParseEngine;
import com.tw.test.parse.engine.SemanticParseEngineImpl;
import com.tw.test.romannumerals.translator.NumeralTranslator;
import com.tw.test.romannumerals.translator.RomanToArabicNumeralTranslator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lex Li
 * @date 19/10/2016
 */
public class CommonLineUI
{
    /*
 * Special reader that can NOT be closed.
 */
    private static final BufferedReader KEYBOARD_READER =
                    new BufferedReader(new InputStreamReader(System.in));

    public static void run()
    {
        NumeralTranslator numeralTranslator = new RomanToArabicNumeralTranslator();
        SemanticParseEngine semanticParseEngine = new SemanticParseEngineImpl( numeralTranslator  );
        do{
            try
            {
                String line = KEYBOARD_READER.readLine();
                if(line.isEmpty())
                {
                    continue;
                }
                ParseResult result = semanticParseEngine.parse( line );
                if( !result.getResult().isEmpty() )
                {
                    System.out.println( result.getResult() );
                }

            }
            catch( IOException e )
            {
                throw new AssertionError("OMG, Impossible! Command shell reading failed", e);
            }
        }while( true );

    }
}
