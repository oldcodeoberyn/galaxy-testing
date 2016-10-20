/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.model;

/**
 * @author Lex Li
 * @date 19/10/2016
 */
public class ParseResult
{
    public static final String FAILURE_TO_PARSE = "I have no idea what you are talking about";
    private boolean state;
    private String result;

    public ParseResult( boolean state, String result )
    {
        this.state = state;
        this.result = result;
    }

    public boolean isSuccess()
    {
        return state;
    }

    public String getResult()
    {
        return result;
    }

    public static ParseResult failure()
    {
        return new ParseResult( false, FAILURE_TO_PARSE );
    }

    public static ParseResult success()
    {
        return new ParseResult( true, "" );
    }
}
