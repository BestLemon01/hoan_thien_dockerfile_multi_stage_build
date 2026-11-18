package com.quickbite.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Map<String, List<String>> userCarts = new HashMap<>();

    @GetMapping("/{userId}")
    public ResponseEntity<List<String>> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(userCarts.getOrDefault(userId, Collections.emptyList()));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Map<String, Object>> addItem(@PathVariable String userId, @RequestParam String item) {
        userCarts.computeIfAbsent(userId, k -> new ArrayList<>()).add(item);
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("userId", userId);
        res.put("items", userCarts.get(userId));
        return ResponseEntity.ok(res);
    }
}
