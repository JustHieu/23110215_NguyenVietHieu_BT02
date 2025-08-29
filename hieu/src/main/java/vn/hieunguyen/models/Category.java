package vn.hieunguyen.models;

public class Category {
	private Integer id;       // cate_id
	  /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	  private String  name;     // cate_name
	  private String  icon;     // icons (đường dẫn hoặc tên file)
	  private Integer userId;   // FK -> User.id
}
