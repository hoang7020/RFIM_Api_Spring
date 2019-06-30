package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.User;

public interface UserRepository {

    public User getByUsername(String username);
}
