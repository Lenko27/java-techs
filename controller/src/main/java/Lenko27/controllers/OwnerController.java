package Lenko27.controllers;

import Lenko27.dtos.OwnerDto;
import Lenko27.responses.OwnerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import Lenko27.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owners")
@AllArgsConstructor
public class OwnerController{

    private final OwnerService ownerService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OwnerResponse>> getAllOwners(){
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable Long id){
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable Long id, @RequestBody OwnerDto ownerDto){
        return ResponseEntity.ok(ownerService.updateOwner(ownerDto, id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OwnerResponse> saveOwner(@RequestBody OwnerDto ownerDto){
        return ResponseEntity.ok(ownerService.saveOwner(ownerDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Long> deleteOwner(@PathVariable Long id){
        return ResponseEntity.ok(ownerService.deleteOwnerById(id));
    }
}
