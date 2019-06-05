package idu.cs.service;

import java.util.List;

import idu.cs.domain.User;

// 인터페이스 = 실제 객체를 레퍼런스 할 수 있음
public interface UserService {
	User getUserById(long id); // primary key에 해당하는 id로  조회
	User getUserByUserId(String userId); // unique key에 해당하는 userId로 조회
	List<User> getUsers(); // 모든 사용자 조회
	
	/* 쓸수도 있고 안쓸수도 있어 */
	List<User> getUsersByName(String name); // name으로 조회
	List<User> getUsersByCompany(String company); // company으로 조회
	List<User> getUsersByPage(int index, int size); // 페이지로 조회
	
	void saveUser(User user); // 생성
	void updateUser(User user); // 수정
	void deleteUser(long id); // 삭제
}
