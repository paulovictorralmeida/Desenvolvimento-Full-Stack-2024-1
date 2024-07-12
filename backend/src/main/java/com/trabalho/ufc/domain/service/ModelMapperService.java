package com.trabalho.ufc.domain.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelMapperService {

    private final ModelMapper modelMapper;

    public <T> List<T> toList(Class<T> clazz, List<?> items) {

        return items.stream()
                .map(item -> modelMapper.map(item, clazz))
                .toList();
    }

    public <T> T toObject(Class<T> clazz, Object item) {
        if (item == null) return null;
        return modelMapper.map(item, clazz);
    }

    public <T> Map<String, Object> toPage(Class<T> clazz, Page<?> page) {
        if (page == null) return new HashMap<>();

        return Map.of(
                "totalResults", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "result", toList(clazz, page.getContent())
        );
    }

}
