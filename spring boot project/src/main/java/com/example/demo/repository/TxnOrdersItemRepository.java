package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TxnOrdersItem;

public interface TxnOrdersItemRepository extends JpaRepository<TxnOrdersItem, String> {

}
