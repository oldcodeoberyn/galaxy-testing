/*
 * Copyright (c) 2016 Nokia Solutions and Networks. All rights reserved.
 */

package com.tw.test.romannumerals.translator;

import java.util.Optional;

/**
 * @author Lex Li
 * @date 20/10/2016
 */
public interface NumeralTranslator
{
    Optional<Integer> translate( String input );
}
