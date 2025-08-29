package vn.hieunguyen.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.hieunguyen.configs.DBConnectSQL;
import vn.hieunguyen.dao.CategoryDao;
import vn.hieunguyen.models.Category;

public class CategoryDaoImpl implements CategoryDao {
	  @Override
	  public void insert(Category c) {
	    String sql = "INSERT INTO dbo.Category(cate_name,icons,user_id) VALUES(?,?,?)";
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setString(1, c.getName());
	      ps.setString(2, c.getIcon());
	      ps.setInt(3, c.getUserId());
	      ps.executeUpdate();
	    } catch (Exception e) { e.printStackTrace(); }
	  }

	  @Override
	  public void edit(Category c) {
	    String sql = "UPDATE dbo.Category SET cate_name=?, icons=? WHERE cate_id=? AND user_id=?";
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setString(1, c.getName());
	      ps.setString(2, c.getIcon());
	      ps.setInt(3, c.getId());
	      ps.setInt(4, c.getUserId());
	      ps.executeUpdate();
	    } catch (Exception e) { e.printStackTrace(); }
	  }

	  @Override
	  public void delete(int id, int userId) {
	    String sql = "DELETE FROM dbo.Category WHERE cate_id=? AND user_id=?";
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setInt(1, id); ps.setInt(2, userId);
	      ps.executeUpdate();
	    } catch (Exception e) { e.printStackTrace(); }
	  }

	  private Category map(ResultSet rs) throws SQLException {
	    Category c=new Category();
	    c.setId(rs.getInt("cate_id"));
	    c.setName(rs.getString("cate_name"));
	    c.setIcon(rs.getString("icons"));
	    c.setUserId(rs.getInt("user_id"));
	    return c;
	  }

	  @Override
	  public Category get(int id, int userId) {
	    String sql = "SELECT * FROM dbo.Category WHERE cate_id=? AND user_id=?";
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setInt(1,id); ps.setInt(2,userId);
	      try(ResultSet rs=ps.executeQuery()){ if(rs.next()) return map(rs); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return null;
	  }

	  @Override
	  public Category get(String name, int userId) {
	    String sql = "SELECT * FROM dbo.Category WHERE cate_name=? AND user_id=?";
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setString(1,name); ps.setInt(2,userId);
	      try(ResultSet rs=ps.executeQuery()){ if(rs.next()) return map(rs); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return null;
	  }

	  @Override
	  public List<Category> getAll(int userId) {
	    String sql="SELECT * FROM dbo.Category WHERE user_id=? ORDER BY cate_id DESC";
	    List<Category> list=new ArrayList<>();
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setInt(1,userId);
	      try(ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(map(rs)); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return list;
	  }

	  @Override
	  public List<Category> search(String keyword, int userId) {
	    String sql="SELECT * FROM dbo.Category WHERE user_id=? AND cate_name LIKE ?";
	    List<Category> list=new ArrayList<>();
	    try (Connection cn=new DBConnectSQL().getConnection();
	         PreparedStatement ps=cn.prepareStatement(sql)) {
	      ps.setInt(1,userId);
	      ps.setString(2, "%"+keyword+"%");
	      try(ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(map(rs)); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return list;
	  }
	}
