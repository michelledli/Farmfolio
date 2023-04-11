package com.iloveyou.domain.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iloveyou.domain.User;

// TODO: Convert to database JDBI calls
public class UserDAO {
    List<User> list;

    Database database;

    public UserDAO(Database database) {
        this.list = new ArrayList<User>();
        this.database = database;
    }

    public void create(User t) {
        this.list.add(t);
    }

    public Optional<User> read(UUID id) {
        User user = null;

        for(User u : this.list) {
            if(u.getId().compareTo(id) == 0) {
                user = u;
                break;
            }
        }

        return Optional.ofNullable(user);
    }

    public List<User> readAll() {
        return list;
    }

    public void update(User user) {
        for(int i = 0; i < this.list.size(); i++) {
            if(this.list.get(i).getId() == user.getId()) {
                this.list.set(i, user);
                break;
            }
        }
    }

    public void delete(UUID id) {
        for(int i = 0; i < this.list.size(); i++) {
            if(this.list.get(i).getId().equals(id)) {
                this.list.remove(i);
                break;
            }
        }
    }

    public Optional<User> findByEmail(String email) {
        User user = null;

        for(User u : this.list) {
            if(u.getEmail().compareTo(email) == 0) {
                user = u;
                break;
            }
        }

        return Optional.ofNullable(user);   
    }
}
