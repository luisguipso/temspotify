package com.example.temspotify.dao;

import java.util.List;

public interface DAO {
    public void create(Object o);
    public List<Object> read(Object o);
    public void update(Object o);
    public void delete(Object o);
}
