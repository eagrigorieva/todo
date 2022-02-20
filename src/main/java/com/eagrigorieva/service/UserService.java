package com.eagrigorieva.service;

public interface UserService {
    void create(String password, String username, String roleCode);

    void delete(Long id);
}
