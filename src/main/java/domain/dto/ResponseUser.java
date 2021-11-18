package domain.dto;


import domain.model.Users;

import java.util.List;

public class ResponseUser {
    public List<Users> result;

    public ResponseUser(List<Users> result) {
        this.result = result;
    }
}
