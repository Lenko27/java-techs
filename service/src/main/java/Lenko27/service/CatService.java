package Lenko27.service;

import Lenko27.dtos.CatDto;
import Lenko27.entities.Cat;
import Lenko27.exceptions.NoEntityExistedException;
import Lenko27.exceptions.NullEntityException;
import Lenko27.repositories.OwnerRepository;
import Lenko27.responses.CatResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Lenko27.repositories.CatRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    public CatResponse getCatById(Long catId) throws NoEntityExistedException {
        Cat cat = catRepository.findById(catId).orElseThrow(NoEntityExistedException::new);

        return toResponse(toDto(cat));
    }

    public List<CatResponse> getAllCats() {
        List<Cat> catList = catRepository.findAll();

        return catList.stream().map(cat -> toResponse(toDto(cat))).collect(Collectors.toList());
    }

    public CatResponse saveCat(CatDto catDto) throws NullEntityException {
        if (catDto == null) {
            throw new NullEntityException();
        }

        return toResponse(toDto(catRepository.save(toModel(catDto))));
    }

    public CatResponse updateCat(CatDto catDto, Long id) throws NoEntityExistedException {
        Cat existingCat = catRepository.findById(id).orElseThrow(NoEntityExistedException::new);

        if (catDto.getName() != null) {
            existingCat.setName(catDto.getName());
        }

        if (catDto.getDob() != null) {
            existingCat.setDob(catDto.getDob());
        }

        if (catDto.getBreed() != null) {
            existingCat.setBreed(catDto.getBreed());
        }

        if (catDto.getColor() != null) {
            existingCat.setColor(catDto.getColor());
        }

        return toResponse(toDto(catRepository.save(existingCat)));
    }

    public Long deleteCatById(Long catId) throws NoEntityExistedException {
        Cat cat = catRepository.findById(catId).orElseThrow(NoEntityExistedException::new);
        catRepository.deleteById(catId);

        return cat.getId();
    }

    public List<CatResponse> getCatsByCertainColor(String color) {
        return catRepository.findAllByColor(color).stream()
                .map(cat -> toResponse(toDto(cat)))
                .collect(Collectors.toList());
    }

    private CatDto toDto(Cat cat) {
        CatDto catDto = new CatDto();
        catDto.setName(cat.getName());
        catDto.setBreed(cat.getBreed());
        catDto.setDob(cat.getDob());
        catDto.setColor(cat.getColor());
        catDto.setOwnerId(cat.getOwner().getId());

        return catDto;
    }

    private CatResponse toResponse(CatDto catDto) {
        CatResponse catResponse = new CatResponse();
        catResponse.setName(catDto.getName());
        catResponse.setBreed(catDto.getBreed());
        catResponse.setDob(catDto.getDob());
        catResponse.setColor(catDto.getColor());

        return catResponse;
    }

    private Cat toModel(CatDto catDto) {
        Cat cat = new Cat();
        cat.setName(catDto.getName());
        cat.setBreed(catDto.getBreed());
        cat.setDob(catDto.getDob());
        cat.setColor(catDto.getColor());
        cat.setOwner(ownerRepository.findById(catDto.getOwnerId()).orElseThrow());

        return cat;
    }
}
