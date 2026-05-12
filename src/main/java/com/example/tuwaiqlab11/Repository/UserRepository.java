package com.example.tuwaiqlab11.Repository;

import com.example.tuwaiqlab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    @Query("select u from User u where u.id = (select p.userId from Post p group by p.userId order by count(p) desc limit 1)")
    User findUserWithMostPosts();

    @Query("select u from User u where u.id = (select c.userId from Comment c group by c.userId order by count(c) desc limit 1)")
    User findUserWithMostComments();
}
