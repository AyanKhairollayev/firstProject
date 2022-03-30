package com.web.simplewebapplication.service;

import com.web.simplewebapplication.dto.request.ItemCreateDtoRequest;
import com.web.simplewebapplication.models.Item;
import com.web.simplewebapplication.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public String createItem(ItemCreateDtoRequest itemCreateDtoRequest) {
        if (itemRepository.existsByName(itemCreateDtoRequest.getName())) {
            return "Item with this name already exists";
        }

        Item item = new Item(itemCreateDtoRequest.getName(), itemCreateDtoRequest.getPrice(),
                itemCreateDtoRequest.getDescription());

        itemRepository.save(item);

        return "Item " + item.getName() + " successfully created";
    }

    public List<Item> showAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> showById(Long id) {
        return itemRepository.findById(id);
    }

    public String updateItem(Long id, ItemCreateDtoRequest itemCreateDtoRequest) {
        Optional<Item> oItem = itemRepository.findById(id);
        if (oItem.isEmpty()) {
            return "Item not found";
        }
        Item item = oItem.get();

        if (!itemCreateDtoRequest.getName().isEmpty()) {
            item.setName(itemCreateDtoRequest.getName());
        }

        if(itemCreateDtoRequest.getPrice() != null) {
            item.setPrice(itemCreateDtoRequest.getPrice());
        }

        if(!itemCreateDtoRequest.getDescription().isEmpty()) {
            item.setDescription(itemCreateDtoRequest.getDescription());
        }

        itemRepository.save(item);

        return "Item successfully updated";
    }

    public String deleteItem(Long id) {
        itemRepository.deleteById(id);

        return "Item successfully deleted";
    }
}
