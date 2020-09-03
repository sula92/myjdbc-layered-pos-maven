package com.dep.sula.dao.custom.impl;

import com.dep.sula.dao.CrudUtil;
import com.dep.sula.dao.custom.QueryDAO;
import com.dep.sula.entity.CustomEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public CustomEntity find(String s) throws Exception {
        return null;
    }

    @Override
    public int save(CustomEntity entity) throws Exception {
        return 0;
    }

    @Override
    public int update(CustomEntity entity) throws Exception {
        return 0;
    }

    @Override
    public int delete(String s) throws Exception {
        return 0;
    }

    @Override
    public ArrayList<CustomEntity> getALLOrderInformation() throws SQLException {
        ArrayList<CustomEntity> customEntities=new ArrayList<>();
        ResultSet r= CrudUtil.execute("select O.id, O.date, O.customerId, C.name, SUM(OD.qty*OD.unitPrice) from (`order` O INNER JOIN customer C on O.customerId = C.id INNER JOIN orderdetail OD on O.id = OD.orderId) group by o.id");
        while (r.next()) {
            String oid=r.getString(1);
            LocalDate date=r.getDate(2).toLocalDate();
            String cusid=r.getString(3);
            String cusnm=r.getString(4);
            Double tot=r.getDouble(5);

            CustomEntity customEntity=new CustomEntity(oid,date,cusid,cusnm,tot);

            customEntities.add(customEntity);
        }
        return customEntities;
    }
}
