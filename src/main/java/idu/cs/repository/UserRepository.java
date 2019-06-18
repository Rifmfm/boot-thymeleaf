package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.entity.UserEntity;

@Repository  // 데이터베이스에 접근 하는 클래스
public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {  // 정의 안해도 되는 것들  : findById, save, delete등은 선언 없이도 구현 가능
	// 아래 메소드등은 선언해야 JPA 규칙에 의해 구현됨
	// find = select문 , by = where, OrderBy = order by, ASC와 DESC를 함께 사용
	
	UserEntity findByUserId(String userId);

	List<UserEntity> findByName(String name);
	List<UserEntity> findByNameOrderByIdDesc(String name);  // id 내림차순 정렬
	List<UserEntity> findByCompany(String company);
	
	// Page<UserEntity> findAll(Pageable pageable); 
}
