/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.wt.test.cli;

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
        SemanticParseEngine semanticParseEngine = new SemanticParseEngine();
        do{
            try
            {
                String line = KEYBOARD_READER.readLine();
                if(line == null)
                {
                    continue;
                }
                ParseResult result = semanticParseEngine.parse( line );
                System.out.println( result.getResult() );

            }
            catch( IOException e )
            {
                throw new AssertionError("OMG, Impossible! Command shell reading failed", e);
            }
        }while( true );

    }
}
