package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.entity.UserEntity;

@Repository
public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {
	// 정의 안해도 되는 것들  : findById, save, delete등은 선언 없이도 구현 가능
	
	// 아래 메소드등은 선언해야 JPA 규칙에 의해 구현됨
	// find = select문 , by = where, OrderBy = order by, ASC와 DESC를 함께 사용
	List<UserEntity> findByName(String name);
	// id내림차순정렬 : List<UserEntity> findByNameOrderByIdDESC(String name);
	List<UserEntity> findByCompany(String company);
	
	UserEntity findByUserId(String userId);  // ByUserId = where order by Id
}
