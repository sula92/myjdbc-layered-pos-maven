package com.dep.sula.dao.custom;

import com.dep.sula.dao.CrudDAO;
import com.dep.sula.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends CrudDAO<CustomEntity,String> {

    public ArrayList<CustomEntity> getALLOrderInformation() throws SQLException;

}
