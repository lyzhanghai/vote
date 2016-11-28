package yingjianhua.vote.actions.resource;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;

import yingjianhua.vote.actions.AbstractWptAction;
import yingjianhua.vote.service.AbstractService;
import yingjianhua.vote.tools.GenericsUtils;
import yingjianhua.vote.tools.Page;
/**
 * 用于对资源做增删改查
 * @author Yingjianhua
 *
 */
public abstract class AbstractCRUDAction<T> extends AbstractWptAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//action的返回值
	public static final String BEAN = "bean";
	public static final String BEANS = "beans";
	public static final String PAGES = "pages";
	public static final String OBJECT = "object";
	
	protected Class<T> entityClass;
	
	//用于存储action返回值
	protected T bean;
	protected List<T> beans;
	protected Page<T> pages;
	protected Object object;
	
	//分页参数
	protected Integer start;
	protected Integer limit;
	protected Integer page;
	
	public AbstractService service;
	
	//初始化action并根据泛型赋值bean的class
	public AbstractCRUDAction() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	@PostConstruct
	public void init() {
		String clazzName = entityClass.getSimpleName();
		String clazzService = clazzName.substring(0, 1).toLowerCase() 
				+ clazzName.substring(1) + "Service";
		try {
			Field clazzField = AbstractWptAction.class.getDeclaredField(clazzService);
			Field baseField = AbstractCRUDAction.class.getDeclaredField("service");
			baseField.set(this, clazzField.get(this)); //service就有值了
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String ins() {
		service.save(bean);
		return BEAN;
	}
	public String upd() {
		service.update(bean);
		return BEAN;
	}
	public String del() {
		service.delete(bean);
		return BEAN;
	}
	
	public T getBean() {
		return bean;
	}
	public void setBean(T bean) {
		this.bean = bean;
	}
	public List<T> getBeans() {
		return beans;
	}
	public void setBeans(List<T> beans) {
		this.beans = beans;
	}
	public Page<T> getPages() {
		return pages;
	}
	public void setPages(Page<T> pages) {
		this.pages = pages;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
