package org.javaacademy.dictionary.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.javaacademy.dictionary.dto.WordDto;
import org.javaacademy.dictionary.dto.WordsDto;
import org.javaacademy.dictionary.entity.Word;
import org.javaacademy.dictionary.repository.WordRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WordService {
    private final WordRepository wordRepository;

    @CacheEvict(value = "getPart", allEntries = true)
    public void addWord(WordDto wordRq) {
        wordRepository.addWord(new Word(wordRq.getWord(), wordRq.getDesc()));
    }

    public List<WordDto> getAllWord() {
        return wordRepository.getAllWord().stream()
                .map(word -> new WordDto(word.getWord(), word.getDesc()))
                .toList();
    }

    @Cacheable(cacheNames = "getPart")
    public WordsDto<List<WordDto>> getPartWord(Integer startElement, Integer pageSize) {
        List<Word> words = wordRepository.getAllWord();
        List<WordDto> wordDto = words.stream()
                .skip(startElement)
                .limit(pageSize)
                .map(word -> new WordDto(word.getWord(), word.getDesc()))
                .toList();
        return new WordsDto<>(wordDto.size(),
                words.size(),
                startElement,
                startElement + pageSize - 1,
                wordDto);
    }
}
