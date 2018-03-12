package com.fh.framewrok.service;

import com.fh.framewrok.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jszeng on 2016/12/21.
 */
@Service
public class BaseService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @PersistenceContext
    EntityManager entityManager;

    public EntityManager getEm() {
        return this.entityManager;
    }

    /**
     * 查询符合条件的记录，生成List返回。 举两个示例，一个是普通查询记录，返回元素是HashMap的List；另一个是执行存储过程
     * @param statementId
     * @param value
     * @return
     * @throws Exception
     */
    public List queryForListBySql(String statementId, Object value) throws Exception {
        List list = this.sqlSessionTemplate.selectList(statementId,value);
        return list;
    }

    /**
     * 查询符合条件的记录，生成Object返回。
     * @param statementId
     * @param value
     * @return
     * @throws Exception
     */
    public Object queryForObjectBySql(String statementId, Object value) throws Exception {
        Object bean = this.sqlSessionTemplate.selectOne(statementId,value);
        return bean;
    }

    /**
     * 查询总记录数
     *
     * @param statementId
     *            调用iBatis的SqlMap文件的声明段名，规则名：SqlMap的namespace+"." +
     * @param value
     *            查询条件值
     * @return int 查找到的记录数
     */
    public int queryForIntBySql(String statementId, Object value) throws Exception {
        return (Integer) this.sqlSessionTemplate.selectOne(statementId, value);
    }

    /**
     * 更新库表中的记录（可批量更新），返回更新成功的记录数。
     * @param statementId
     *            调用iBatis的SqlMap文件的声明段名，规则名：SqlMap的namespace+"." +
     *            该sqlMap文件某片段的id
     * @param value
     *            更新条件值值
     * @return 更新成功的记录数
     */
    public int updateBySql(String statementId, Object value) throws Exception {
        return this.sqlSessionTemplate.update(statementId, value);
    }

    /**
     * 删除库表中的记录（可批量删除），返回删除成功的记录数。
     * @param statementId
     *            调用iBatis的SqlMap文件的声明段名，规则名：SqlMap的namespace+"." +
     *            该sqlMap文件某片段的id
     * @param value
     *            删除条件值
     * @return 删除成功的记录数
     */
    public int deleteBySql(String statementId, Object value) throws Exception {
        return this.sqlSessionTemplate.delete(statementId, value);
    }


    /**
     * 往库表中插入记录（可只插入记录的部分字段，不同于hibernate的save要保存整个实例的所有属性到DB），返回插入后的对象。
     * @param statementId
     *            调用iBatis的SqlMap文件的声明段名，规则名：SqlMap的namespace+"." +
     *            该sqlMap文件某片段的id
     * @param value
     *            要操作的对象
     * @return 插入后的对象
     */
    public Object insertBySql(String statementId, Object value) throws Exception {
        return this.sqlSessionTemplate.insert(statementId, value);
    }

    /**
     * 更新对象
     *
     * @param entity
     * @return
     */
    public Object update(Object entity) throws Exception {
        getEm().merge(entity);
        getEm().flush();
        return entity;
    }

    /**
     * 保存对象
     *
     * @param entity
     */
    public Object save(Object entity) throws Exception {
        getEm().persist(entity);
        getEm().flush();
        return entity;
    }


    /**
     * 根据主键查询对象
     *
     * @param beanClass
     * @param key
     * @return
     */
    public <T> Object find(T beanClass, Object key) throws Exception {
        return getEm().find((Class<T>) beanClass, key);
    }

    /**
     * 删除对象
     *
     * @param entity
     */
    public void remove(Object entity) throws Exception {
        getEm().remove(getEm().merge(entity));
        getEm().flush();
    }


    /**
     * 批量保存对象
     *
     * @param entitysToSave
     * @throws Exception
     */
    public void batchSave(final List entitysToSave) throws Exception {
        if (CollectionUtils.isEmpty(entitysToSave)) {
            return;
        }
        int max = entitysToSave.size();
        for (int i = 0; i < max; i++) {
            getEm().persist(entitysToSave.get(i));
            if (i != 0 && i % 10 == 0 || i == max - 1) {
                getEm().flush();
            }
        }
    }


    /**
     * 批量更新对象
     *
     * @param entitysToMerge
     * @throws Exception
     */
    public void batchUpdate(final List entitysToMerge) throws Exception {
        if (CollectionUtils.isEmpty(entitysToMerge)) {
            return;
        }
        int max = entitysToMerge.size();
        for (int i = 0; i < max; i++) {
            getEm().merge(entitysToMerge.get(i));
            if (i != 0 && i % 10 == 0 || i == max - 1) {
                getEm().flush();
            }
        }
    }

    /**
     * 批量删除对象
     *
     * @param entitysToDelete
     * @throws Exception
     */
    public void batchRemove(List entitysToDelete) throws Exception {
        if (CollectionUtils.isEmpty(entitysToDelete)) {
            return;
        }
        int max = entitysToDelete.size();
        for (int i = 0; i < max; i++) {
            getEm().remove(entitysToDelete.get(i));
            if (i != 0 && i % 10 == 0 || i == max - 1) {
                getEm().flush();
            }
        }
    }

    /**
     * 查询对象并返回对象集合
     *
     * @param entityClass
     * @return
     */
    public List finaAll(Class entityClass) throws Exception {
        String queryString = buildQueryString(entityClass, null);
        Query query = getEm().createQuery(queryString);
        return query.getResultList();
    }


    /**
     * 根据字段查询对象集合
     *
     * @param entityClass
     * @param field
     * @param value
     * @return
     */
    public List findListByField(Class entityClass, String field, Object value) throws Exception {
        String queryString = buildQueryString(entityClass, new String[] { field });
        Query query = getEm().createQuery(queryString);
        if (StringUtils.isNotBlank(field)) {
            query.setParameter(1, value);
        }
        return query.getResultList();
    }

    /**
     * 根据字段查询对象
     *
     * @param entityClass
     * @param params
     * @return
     */
    public Object findObjectByFields(Class entityClass, Map params) throws Exception {
        String queryString = buildQueryStringWithNamedParams(entityClass, params);
        Query query = getEm().createQuery(queryString);
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        Map.Entry<String, Object> entry = null;
        while (iterator.hasNext()) {
            entry = iterator.next();
            query.setParameter(entry.getKey(), entry.getValue());
        }
        int resultSize = query.getResultList().size();
        if (resultSize > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }

    /**
     * 根据字段查询单个对象
     *
     * @param entityClass
     * @param field
     * @param value
     * @return
     */
    public Object findObjectByField(Class entityClass, String field, Object value) throws Exception {
        String queryString = buildQueryString(entityClass, new String[] { field });
        Query query = getEm().createQuery(queryString);
        if (StringUtils.isNotBlank(field)) {
            query.setParameter(1, value);
        }
        Object obj;
        try {
            obj = query.getSingleResult();
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    /**
     * 根据类字段获得结果集（支持分页）
     *
     * @param entityClass
     * @param field
     * @param value
     * @param start
     * @param maxRows
     * @return
     * @throws Exception
     */
    public List findListByField(Class entityClass, String field, Object value, int start, int maxRows) throws Exception {
        String queryString = buildQueryString(entityClass, new String[] { field });
        Query query = getEm().createQuery(queryString);
        query.setParameter(1, value);
        if (maxRows >= 0) {
            query.setMaxResults(maxRows);
        }
        if (start >= 0) {
            query.setFirstResult(start);
        }
        return query.getResultList();
    }

    /**
     * 根据字段查询符合条件的记录集（支持分页）
     *
     * @param entityClass
     * @param params
     * @param start
     * @param maxRows
     * @return
     */
    public <T> List findListByFields(Class entityClass, Map params, int start, int maxRows) throws Exception {
        String queryString = buildQueryStringWithNamedParams(entityClass, params);
        Query query = getEm().createQuery(queryString);
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        Map.Entry<String, Object> entry = null;
        while (iterator.hasNext()) {
            entry = iterator.next();
            query.setParameter(entry.getKey(), entry.getValue());
        }
        if (maxRows >= 0) {
            query.setMaxResults(maxRows);
        }
        if (start >= 0) {
            query.setFirstResult(start);
        }
        return query.getResultList();
    }

    /**
     * 获得记录数
     *
     * @param entityClass
     * @param params
     * @return
     */
    public int countByFields(Class entityClass, Map params) throws Exception {
        String queryString = "select count(*) ";
        queryString += buildQueryStringWithNamedParams(entityClass, params);
        Query query = getEm().createQuery(queryString);
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        Map.Entry<String, Object> entry = null;
        while (iterator.hasNext()) {
            entry = iterator.next();
            query.setParameter(entry.getKey(), entry.getValue());
        }
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    /**
     * 通过数组字段集构建HQL语句
     *
     * @param entityClass
     * @param fields
     * @return
     */
    private String buildQueryString(Class entityClass, String fields[]) throws Exception {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("from ").append(entityClass.getName());
        if (fields != null && fields.length > 0) {
            queryBuilder.append(" where ");
            String as[];
            int j = (as = fields).length;
            for (int i = 0; i < j; i++) {
                String field = as[i];
                queryBuilder.append(field).append(" = ? and ");
            }

            if (queryBuilder.lastIndexOf(" and ") == queryBuilder.length() - 5) {
                queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
            }
        }
        return queryBuilder.toString();
    }

    /**
     * 通过Map字段集构建HQL语句
     *
     * @param entityClass
     * @param params
     * @return
     */
    private <T> String buildQueryStringWithNamedParams(Class entityClass, Map params) throws Exception {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("from ").append(entityClass.getName());
        if (!CollectionUtils.isEmpty(params)) {
            queryBuilder.append(" where ");
            Map.Entry entry;
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry) iterator.next();
                queryBuilder.append((String) entry.getKey()).append(" = :").append((String) entry.getKey())
                        .append(" and ");
            }

            if (queryBuilder.lastIndexOf(" and ") == queryBuilder.length() - 5) {
                queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
            }
        }
        return queryBuilder.toString();
    }

}
