package Lenko27.controllers;

import Lenko27.dtos.CatDto;
import Lenko27.responses.CatResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import Lenko27.service.CatService;

import java.util.List;

@RestController
@RequestMapping("/cats")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public ResponseEntity<CatResponse> getCatById(@PathVariable Long id){
        return ResponseEntity.ok(catService.getCatById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CatResponse>> getAllCats(){
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CatResponse>> getCatsByColor(@RequestParam String color) {
        return ResponseEntity.ok(catService.getCatsByCertainColor(color));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CatResponse> updateCat(@PathVariable Long id, @RequestBody CatDto catDto){
        return ResponseEntity.ok(catService.updateCat(catDto, id));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CatResponse> saveCat(@RequestBody CatDto catDto){
        return ResponseEntity.ok(catService.saveCat(catDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Long> deleteCat(@PathVariable Long id){
        return ResponseEntity.ok(catService.deleteCatById(id));
    }
}
