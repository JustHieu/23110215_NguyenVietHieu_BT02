package vn.hieunguyen.serviceimplement;

import java.util.List;
import vn.hieunguyen.dao.CategoryDao;
import vn.hieunguyen.daoimplement.CategoryDaoImpl;
import vn.hieunguyen.models.Category;
import vn.hieunguyen.services.CategoryService;   

public class CategoryServiceImpl implements CategoryService {   

    private final CategoryDao dao = new CategoryDaoImpl();

    @Override
    public void insert(Category c) { dao.insert(c); }

    @Override
    public void edit(Category c) { dao.edit(c); }

    @Override
    public void delete(int id, int userId) { dao.delete(id, userId); }

    @Override
    public Category get(int id, int userId) { return dao.get(id, userId); }

    @Override
    public Category get(String name, int userId) { return dao.get(name, userId); }

    @Override
    public List<Category> getAll(int userId) { return dao.getAll(userId); }

    @Override
    public List<Category> search(String k, int userId) { return dao.search(k, userId); }
}
