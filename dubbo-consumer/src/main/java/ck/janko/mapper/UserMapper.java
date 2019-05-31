package ck.janko.mapper;


import ck.janko.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper{
    User Sel(int id);
}
