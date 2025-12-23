package com.jmc.warehouse.Services;

import com.jmc.warehouse.Views.AccountType;

public interface LoginService {
    LoginResult login(String username, String password, AccountType type);
}
