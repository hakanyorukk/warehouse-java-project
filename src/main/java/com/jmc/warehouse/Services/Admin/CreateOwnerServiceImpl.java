package com.jmc.warehouse.Services.Admin;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Model;

import java.time.LocalDate;

public class CreateOwnerServiceImpl implements CreateOwnerService{

    private final Model model;

    public CreateOwnerServiceImpl(Model model) {this.model = model;}

    @Override
    public CreateOwnerResult createOwner(String fullName, String username, String email, String password, String phone, String taxId) {
        AdminEntity currentAdmin = model.getCurrentAdmin();
        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return CreateOwnerResult.MISSING_FIELDS;
        } else {
            OwnerEntity owner = new OwnerEntity(fullName, username, email, password, phone, taxId, currentAdmin, LocalDate.now());
            boolean success = model.getDatabaseDriver().createOwner(owner);

            if(success) {
                return CreateOwnerResult.SUCCESS;
            } else {
                return CreateOwnerResult.ERROR;
            }
        }
    }
}
