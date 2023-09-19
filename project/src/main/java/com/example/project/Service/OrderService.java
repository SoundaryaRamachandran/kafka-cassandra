package com.example.project.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.example.project.DTO.ItemResponse;
import com.example.project.DTO.MessageResponse;
import com.example.project.DTO.OrderResponse;
import com.example.project.Entity.Customer;
import com.example.project.Repository.CustomerRepo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final CqlSession session;
    @Autowired
    CustomerRepo customerRepo;

    public OrderService(CqlSession session) {
        this.session = session;
    }

    public List<OrderResponse> orderResponse(int customerId) {

        String order = "Select orderstatus,Orderid,cost,itemqty from orders where customerid =?ALLOW FILTERING";
        PreparedStatement preparedStatement = session.prepare(order);
        BoundStatement boundStatement = preparedStatement.bind(customerId);
        ResultSet resultSet = session.execute(boundStatement);
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for (Row row1 : resultSet) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrder_status(row1.getString("orderStatus"));
            orderResponse.setOrderId(row1.getInt("OrderId"));
            orderResponse.setCost(row1.getInt("cost"));
            orderResponse.setNumberOfItems(row1.getInt("itemQty"));
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;

    }

    public ItemResponse getItemDetailsByItemId(int id) {
        ItemResponse itemResponse = new ItemResponse();

        String totalQtyOrderedQuery = "SELECT SUM(itemQty) FROM orders WHERE itemId = ? ALLOW FILTERING";
        PreparedStatement totalQtyOrderedPreparedStatement = session.prepare(totalQtyOrderedQuery);
        BoundStatement totalQtyOrderedBoundStatement = totalQtyOrderedPreparedStatement.bind(id);
        ResultSet totalQtyOrderedResultSet = session.execute(totalQtyOrderedBoundStatement);

        Row totalQtyOrderedRow = totalQtyOrderedResultSet.one();
        int totalQtyOrdered = totalQtyOrderedRow.getInt(0);

        String totalNumberOfOrdersQuery = "SELECT COUNT(*) FROM orders WHERE itemId = ?ALLOW FILTERING";
        PreparedStatement totalNumberOfOrdersPreparedStatement = session.prepare(totalNumberOfOrdersQuery);
        BoundStatement totalNumberOfOrdersBoundStatement = totalNumberOfOrdersPreparedStatement.bind(id);
        ResultSet totalNumberOfOrdersResultSet = session.execute(totalNumberOfOrdersBoundStatement);


        Row totalNumberOfOrdersRow = totalNumberOfOrdersResultSet.one();
        long totalNumberOfOrders = totalNumberOfOrdersRow.getLong(0);

        itemResponse.setTotalQtyOrdered(totalQtyOrdered);
        itemResponse.setTotalNumberOfOrders((int) totalNumberOfOrders);

        String findByQuery = "SELECT itemname FROM orders WHERE itemId = ? ALLOW FILTERING";
        PreparedStatement preparedStatement = session.prepare(findByQuery);
        BoundStatement boundStatement = preparedStatement.bind(id);
        ResultSet resultSet = session.execute(boundStatement);

        Row row = resultSet.one();
        if (row != null) {
            itemResponse.setItemName(row.getString("itemname"));
        }
        return itemResponse;
    }

    public MessageResponse getMsgDetaild(int id) {

        MessageResponse messageResponse1 = new MessageResponse();

        String msgOrder = "Select msgstring from message_logger where orderId=? ";
        PreparedStatement messageResponse = session.prepare(msgOrder);
        BoundStatement boundStatement = messageResponse.bind(id);
        ResultSet resultSet = session.execute(boundStatement);
        Row row = resultSet.one();
        if (row != null) {
            messageResponse1.setOrderdetails(row.getString("msgString"));

        }

        return messageResponse1;
    }


    public List<OrderResponse> orderResponseByEmail(String email) {
        List<OrderResponse> orderResponses = new ArrayList<>();


        Customer sqlCustomer = customerRepo.findCustomerByEmail(email);


        if (sqlCustomer == null) {
            throw new ServiceException("not found");
        }

        int customerId = sqlCustomer.getCustomer_id();

        String customerDetailsQuery = "SELECT orderid ,orderstatus ,cost,itemqty FROM orders WHERE customerid = ? ALLOW FILTERING";
        PreparedStatement customerDetailsPreparedStatement = session.prepare(customerDetailsQuery);
        BoundStatement customerDetailsBoundStatement = customerDetailsPreparedStatement.bind(customerId);
        ResultSet customerDetailsResultSet = session.execute(customerDetailsBoundStatement);

        Row customerDetailsRow = customerDetailsResultSet.one();

        if (customerDetailsRow != null) {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setOrderId(customerDetailsRow.getInt("orderId"));
            orderResponse.setOrder_status(customerDetailsRow.getString("orderStatus"));
            orderResponse.setCost(customerDetailsRow.getInt("cost"));
            orderResponse.setNumberOfItems(customerDetailsRow.getInt("itemQty"));
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    public List<OrderResponse> orderResponseByPhno(int phNo) {

        List<OrderResponse> orderResponses = new ArrayList<>();

        Customer sqlCustomer = customerRepo.findCustomerByPhn(phNo);

        if (sqlCustomer == null) {
            throw new ServiceException("not found");
        }

        int customerId = sqlCustomer.getCustomer_id();
        CqlSession session = CqlSession.builder().build();
        String customerDetailsQuery = "SELECT orderid ,orderstatus ,cost,itemqty FROM my_keyspace.orders WHERE customerid = ? ALLOW FILTERING";
        PreparedStatement customerDetailsPreparedStatement = session.prepare(customerDetailsQuery);
        BoundStatement customerDetailsBoundStatement = customerDetailsPreparedStatement.bind(customerId);

        ResultSet customerDetailsResultSet = session.execute(customerDetailsBoundStatement);

        Row customerDetailsRow = customerDetailsResultSet.one();

        if (customerDetailsRow != null) {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setOrderId(customerDetailsRow.getInt("orderId"));
            orderResponse.setOrder_status(customerDetailsRow.getString("orderStatus"));
            orderResponse.setCost(customerDetailsRow.getInt("cost"));
            orderResponse.setNumberOfItems(customerDetailsRow.getInt("itemQty"));
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    public MessageResponse getMsgDetailID(UUID id) {
        String msg = "select msgstring from message_logger where msgId = ?";
        ResultSet resultSet = session.execute(msg, id);
        Row row = resultSet.one();
        MessageResponse messageResponse = new MessageResponse();
        if (row != null) {

            messageResponse.setOrderdetails(row.getString("msgstring"));
        }
        return messageResponse;
    }
}


