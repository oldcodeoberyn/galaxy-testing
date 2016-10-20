/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.parse.engine;

import com.tw.test.model.ParseResult;

/**
 * @author Lex Li
 * @date 20/10/2016
 */
public interface SemanticParseEngine
{
    ParseResult parse( String input );
}
