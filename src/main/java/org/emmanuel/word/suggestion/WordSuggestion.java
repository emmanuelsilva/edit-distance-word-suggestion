package org.emmanuel.word.suggestion;

import lombok.Data;
import org.emmanuel.word.Vocabulary;
import org.emmanuel.word.edit.distance.levenshtein.LevenshteinDistance;

import java.util.Comparator;

public class WordSuggestion {

    private final LevenshteinDistance distance;
    private final Vocabulary vocabulary;

    public WordSuggestion(LevenshteinDistance distance, Vocabulary vocabulary) {
        this.distance = distance;
        this.vocabulary = vocabulary;
    }

    /**
     * Suggest the next closest word by similarity for a given one.
     **/
    public String nextSimilarWord(String word) {
        return vocabulary
                .getWords()
                .stream()
                .map(vocabularyWord -> new DistanceWord(distance.distance(word, vocabularyWord), vocabularyWord))
                .min(Comparator.comparing(DistanceWord::getDistance))
                .map(DistanceWord::getWord)
                .orElse("");
    }

    @Data
    private static class DistanceWord {
        private final int distance;
        private final String word;
    }
}
