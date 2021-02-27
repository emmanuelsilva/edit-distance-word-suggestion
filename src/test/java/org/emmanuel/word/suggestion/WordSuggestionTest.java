package org.emmanuel.word.suggestion;

import org.emmanuel.word.Vocabulary;
import org.emmanuel.word.edit.distance.levenshtein.MatrixSolutionLevenshteinDistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordSuggestionTest {

    private WordSuggestion wordSuggestion;

    @BeforeEach
    void setUp() {
        this.wordSuggestion = new WordSuggestion(
            new MatrixSolutionLevenshteinDistance(),
            this.createSmallEnglishVocabulary()
        );
    }

    @Test
    void shouldSuggestAppleForApleWord() {
        String suggestedWord = this.wordSuggestion.nextSimilarWord("aple");
        assertEquals("Apple", suggestedWord);
    }

    @Test
    public void shouldSuggestCatForAtWord() {
        String suggestedWord = this.wordSuggestion.nextSimilarWord("at");
        assertEquals("Cat", suggestedWord);
    }

    private Vocabulary createSmallEnglishVocabulary() {
        return () -> Arrays.asList(
                "Apple",
                "Amazing",
                "Air",
                "Banana",
                "Cat",
                "Dog"
        );
    }
}