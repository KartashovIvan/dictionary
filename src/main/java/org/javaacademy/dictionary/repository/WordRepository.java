package org.javaacademy.dictionary.repository;

import java.util.*;
import lombok.SneakyThrows;
import org.javaacademy.dictionary.entity.Word;
import org.springframework.stereotype.Component;

@Component
public class WordRepository {
    private final SortedSet<Word> words = new TreeSet<>(Comparator.comparing(Word::getWord));

    public void addWord(Word word) {
        words.add(word);
    }

    @SneakyThrows
    public List<Word> getAllWord() {
        Thread.sleep(3000);
        return words.stream().toList();
    }
}
