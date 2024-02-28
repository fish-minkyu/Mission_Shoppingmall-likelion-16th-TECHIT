package com.example.shoppingmall.used;

import com.example.shoppingmall.auth.AuthenticationFacade;
import com.example.shoppingmall.auth.entity.UserEntity;
import com.example.shoppingmall.used.dto.ItemDto;
import com.example.shoppingmall.used.entity.ItemEntity;
import com.example.shoppingmall.used.entity.ItemStatus;
import com.example.shoppingmall.used.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;
  private final AuthenticationFacade auth;

  // Create
  public void createItem(ItemDto dto) {
    try {
      UserEntity user = auth.getAuth();

      log.info("UserEntity: {}", user);

      ItemEntity newItem = ItemEntity.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .postImage(dto.getPostImage())
        .price(dto.getPrice())
        .itemStatus(ItemStatus.SELLING)
        .user(user)
        .build();

      itemRepository.save(newItem);
    } catch (Exception e) {
      log.error("reason is {}", e);
      log.error("Failed Exception: {}", Exception.class);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Read

  // Update

  // Delete
}