package com.github.andre10dias.sbootexp_security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public route ok!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication auth) {
        return ResponseEntity.ok("Usuario autenticado: " + auth.getName() + ".\nPrivate route ok!");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminRoute(Authentication auth) {
        return ResponseEntity.ok("Usuario autenticado: " + auth.getName() + ".\nAdmin route ok!");
    }

}
