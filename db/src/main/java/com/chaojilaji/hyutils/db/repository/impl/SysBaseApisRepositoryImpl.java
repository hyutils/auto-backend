package com.chaojilaji.hyutils.db.repository.impl;

import com.chaojilaji.hyutils.db.repository.SysBaseApisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SysBaseApisRepositoryImpl implements SysBaseApisRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}