package idu.cs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idu.cs.domain.User;
import idu.cs.entity.UserEntity;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Repository("userService")
public class UserServiceImpl implements UserService {

	@Autowired UserRepository repository;
	@Override
	public User getUserById(long id) {
		UserEntity entity = null;
		try {
			entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found" + id));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = entity.buildDomain();
		return user;
	}

	@Override
	public User getUserByUserId(String userId) {  // DB로 부터 가져와라 가져와서 바꿔라
		// DB, repository에서 가져와 Entiry에 저장
		// UserReposutory에 findByUserId()가 선업되어야 한다.
		UserEntity entity = repository.findByUserId(userId);
		// Entity를 Service, Controller에서 사용하기 위해 Domain으로 변환
		User user = entity.buildDomain();
		return user;
	}

	@Override
	public List<User> getUsers() { 
		List<User> users = new ArrayList<User>();
		List<UserEntity> entities = repository.findAll();
		for(UserEntity entity : entities) {
			/*
			 * entity -> domain으로 변환하는 곳*/
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByCompany(String company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByPage(int index, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		UserEntity entity = new UserEntity();
		/*
		 * domain-user 객체를 entity-userEntity 생성
		 * DB저장을 위해 repository가 Entity를 필요로 함
		 */
		entity.buildEntity(user);
		repository.save(entity);
	}

	@Override
	public void updateUser(User user) {
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		repository.save(entity);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub

	}

}