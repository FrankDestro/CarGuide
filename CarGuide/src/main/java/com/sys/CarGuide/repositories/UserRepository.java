package com.sys.CarGuide.repositories;

import com.sys.CarGuide.models.entities.User;
import com.sys.CarGuide.models.projection.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Long findUserIdByEmail(String email);

    @Query(nativeQuery = true, value = """
			SELECT tb_user.id AS id, tb_user.email AS username, tb_user.password, role.id As roleId, role.authority
			FROM tb_user
			INNER JOIN user_role ON tb_user.id = user_role.user_id
			INNER JOIN role ON role.id = user_role.role_id
			WHERE tb_user.email = :email
			""")
    List<UserDetailsProjection> searchUserAndRoleByEmail(String email);
}
