package com.jmc.warehouse.Services.Admin;

public interface CreateOwnerService {
    CreateOwnerResult createOwner(String fullName, String username, String email, String password, String phone, String taxId);
}
