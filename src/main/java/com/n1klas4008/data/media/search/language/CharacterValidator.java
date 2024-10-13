package com.n1klas4008.data.media.search.language;

import com.n1klas4008.data.media.search.language.impl.JapaneseValidator;
import com.n1klas4008.data.media.search.language.impl.RussianValidator;

public interface CharacterValidator {
    CharacterValidator JAPANESE = new JapaneseValidator();
    CharacterValidator RUSSIAN = new RussianValidator();

    boolean matching(String input);

    boolean contains(String input);
}
