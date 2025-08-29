package vn.hieunguyen.dao;

import java.util.List;

import vn.hieunguyen.models.Category;

public interface CategoryDao {
	void insert(Category c);
	  void edit(Category c);
	  void delete(int id, int userId);

	  Category get(int id, int userId);
	  Category get(String name, int userId);
	  List<Category> getAll(int userId);
	  List<Category> search(String keyword, int userId);

}
