package com.ecommerce.user.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/customer")
  @PreAuthorize("hasRole('CUSTOMER')")
  public String userAccess() {
    return "Customer Content.";
  }

  @GetMapping("/seller")
  @PreAuthorize("hasRole('SELLER')")
  public String moderatorAccess() {
    return "Seller Board.";
  }
}
