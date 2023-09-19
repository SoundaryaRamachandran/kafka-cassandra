package com.example.conshumer.DTO;

import com.example.conshumer.Entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemReq {
    private List<ItemDto> itemList;
}
