package com.examp.demoServiceV2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao<T> {
    protected abstract T mapperResult(ResultSet rs, int i) throws SQLException;
}
