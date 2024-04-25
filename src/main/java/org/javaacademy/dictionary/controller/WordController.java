package org.javaacademy.dictionary.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionary.dto.WordDto;
import org.javaacademy.dictionary.dto.WordsDto;
import org.javaacademy.dictionary.service.WordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class WordController {
    private final WordService dictionaryService;

    @PostMapping
    public void addWord(@RequestBody WordDto word) {
        dictionaryService.addWord(word);
    }

    @GetMapping()
    public WordsDto<List<WordDto>> getPartWord(@RequestParam Integer startElement,
                                               @RequestParam Integer pageSize) {
        return dictionaryService.getPartWord(startElement, pageSize);
    }

    @GetMapping("/all")
    public List<WordDto> getAllWord() {
        return dictionaryService.getAllWord();
    }
}
