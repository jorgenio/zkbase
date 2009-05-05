package org.zkbase.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkbase.dao.BasicDao;
import org.zkbase.dao.EntityNotFoundException;
import org.zkbase.model.User;

@Transactional(readOnly=true,propagation = Propagation.REQUIRED)
public abstract class GenericService<T>  {
	
	private final Class<T> objectClass;
	
	public GenericService(final Class<T> objectClass){
		this.objectClass = objectClass;
	}
	
	@Autowired
	private BasicDao basicDao;
	

	/* (non-Javadoc)
	 * @see org.zkbase.service.BaseService#count()
	 */
	public Long count() {
		return this.basicDao.count(this.objectClass);
	}

	/* (non-Javadoc)
	 * @see org.zkbase.service.BaseService#findByID(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")	
	public T findByID(Serializable id) throws EntityNotFoundException {
		return basicDao.find(objectClass, id);
	}

	/* (non-Javadoc)
	 * @see org.zkbase.service.BaseService#findAll()
	 */
	@SuppressWarnings("unchecked")	
	public List<T> findAll(){
		List<?> objects = this.basicDao.findAll(objectClass);
		return(List<T>) objects;
	}
	
	/* (non-Javadoc)
	 * @see org.zkbase.service.BaseService#findAll(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(int firstResult, int maxResults){
		List<?> objects = this.basicDao.findAll(objectClass, firstResult, maxResults);
		return(List<T>) objects;
	}	
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findByNamedQuery(String namedQuery, int firstResult, int maxResults, Object... params) {
		return this.basicDao.findNamedQuery(namedQuery, firstResult, maxResults, params);		
	}
	
	@SuppressWarnings("unchecked")
	protected Object findByNamedQuerySingle(String namedQuery, Object... params){
		return this.basicDao.findNamedQuerySingle(namedQuery, params);		
	}
	
	/* (non-Javadoc)
	 * @see org.zkbase.service.BaseService#persist(T)
	 */
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED)
	public void persist(T object){
		this.basicDao.persist(object);
	}

	@Transactional(readOnly=false,propagation = Propagation.REQUIRED)
	public void delete(Serializable id) throws EntityNotFoundException {
		this.basicDao.remove(objectClass, id);
	}

	/* (non-Javadoc)
	 * @see org.zkbase.service.BaseService#merge(T)
	 */
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED)
	public void merge(T object) throws EntityNotFoundException {
		this.basicDao.merge(object);
	}
	
	public void test(User u) {
		Long x = (Long)this.basicDao.findSingle("SELECT COUNT(u) from User u WHERE u.username LIKE ?1", u.getUsername());
		System.out.println(x);
	}
	
	abstract public List<T> findByExample(T example, int firstResult, int maxResults);
	
	abstract public Long countByExample(T example);	

}
