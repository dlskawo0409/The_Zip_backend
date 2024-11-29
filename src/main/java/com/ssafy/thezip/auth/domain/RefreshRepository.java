package com.ssafy.thezip.auth.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface RefreshRepository {
    Boolean existsByRefresh(String refresh);
    @Transactional
    void deleteByRefresh(String refresh);

    boolean save(RefreshEntity refreshEntity);
}
