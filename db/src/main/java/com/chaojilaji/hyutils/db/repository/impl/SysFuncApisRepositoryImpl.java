package com.chaojilaji.hyutils.db.repository.impl;

import com.chaojilaji.hyutils.db.repository.SysFuncApisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SysFuncApisRepositoryImpl implements SysFuncApisRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}