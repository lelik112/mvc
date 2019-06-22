package net.tcheltsou.springmvclearning.repository;

import java.util.List;

public interface Repository<T> {
	void create(T entity);
	T read(Long id);
	List<T> readAll();
	void update(T entity);
	void delete(Long id);
}
