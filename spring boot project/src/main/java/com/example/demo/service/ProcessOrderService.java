package com.example.demo.service;

import java.util.List;

import org.apache.activemq.store.memory.MemoryTransactionStore.Tx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.ApproveOrderReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.entity.TxnOrdersItem;
import com.example.demo.repository.TxnOrdersItemRepository;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.transaction.Transactional;
@Service
public class ProcessOrderService {

    @Autowired
    private TxnOrdersItemRepository txnOrdersItemRepository;

    @JmsListener(destination = "${spring.activemq.queue-name}")
    @SuppressWarnings("unchecked")
    @Transactional
    public void processOrder(Message message) throws JMSException {
        if(message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            if(objectMessage.getObject() != null) {
                Object object = objectMessage.getObject();
                List<TxnOrdersItem> txnOrdersItemList = (List<TxnOrdersItem>) object;
                System.out.println("txnOrdersItems"+txnOrdersItemList);

                txnOrdersItemList.forEach(txnOrdersItem -> {
                    txnOrdersItem.setStatus("WAITING_OWNER_APPROVAL");
                });
                txnOrdersItemRepository.saveAll(txnOrdersItemList);
            }

        }
       
    }

    public BaseResponse ApproveOrder(ApproveOrderReq req) {
        List<TxnOrdersItem> txnOrdersItemList = txnOrdersItemRepository.findAllById(req.getOrderIds());
        if(!txnOrdersItemList.isEmpty()){
            txnOrdersItemList.forEach(txnOrdersItem -> {
                txnOrdersItem.setStatus("APPROVED");
            });
            txnOrdersItemRepository.saveAll(txnOrdersItemList);
            return new BaseResponse("SUCCESS");
        }
        return null ;
    }

    public BaseResponse findOrderRequest(String  ownerId) {
        // List<TxnOrdersItem> txnOrdersItemList = txnOrdersItemRepository.findAllById(req.getOrderIds());
        // if(!txnOrdersItemList.isEmpty()){
        //     txnOrdersItemList.forEach(txnOrdersItem -> {
        //         txnOrdersItem.setStatus("APPROVED");
        //     });
        //     txnOrdersItemRepository.saveAll(txnOrdersItemList);
        //     return new BaseResponse("SUCCESS");
        // }
        return null ;
    }
}
