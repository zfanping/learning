package com.example.demo.ch01.mapper;

import java.util.List;

import com.example.demo.ch01.model.Country;

public interface CountryMapper {
    List<Country> selectAll();
}
