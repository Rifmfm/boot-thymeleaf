package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.entity.UserEntity;

@Repository
public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {
	List<UserEntity> findByName(String name);
	List<UserEntity> findByCompany(String company);
	UserEntity findByUserId(String userId);
}
