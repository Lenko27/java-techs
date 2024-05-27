package Lenko27.service;

import Lenko27.dtos.OwnerDto;
import Lenko27.entities.Owner;
import Lenko27.exceptions.NoEntityExistedException;
import Lenko27.exceptions.NullEntityException;
import Lenko27.responses.OwnerResponse;
import Lenko27.tools.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import Lenko27.repositories.OwnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OwnerService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    public OwnerResponse getOwnerById(Long ownerId) throws NoEntityExistedException {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(NoEntityExistedException::new);

        return toResponse(toDto(owner));
    }

    public List<OwnerResponse> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll();

        return ownerList.stream().map(owner -> toResponse(toDto(owner))).collect(Collectors.toList());
    }

    public OwnerResponse saveOwner(OwnerDto ownerDto) throws NullEntityException {
        if (ownerDto == null) {
            throw new NullEntityException();
        }

        return toResponse(toDto(ownerRepository.save(toModel(ownerDto))));
    }

    public OwnerResponse updateOwner(OwnerDto ownerDto, Long id) throws NoEntityExistedException {
        Owner existingOwner = ownerRepository.findById(id).orElseThrow(NoEntityExistedException::new);
        if (ownerDto.getName() != null) {
            existingOwner.setName(ownerDto.getName());
        }
        if (ownerDto.getDob() != null) {
            existingOwner.setDob(ownerDto.getDob());
        }

        return toResponse(toDto(ownerRepository.save(existingOwner)));
    }

    public Long deleteOwnerById(Long ownerId) throws NoEntityExistedException {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(NoEntityExistedException::new);
        ownerRepository.deleteById(ownerId);

        return owner.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> owner = ownerRepository.findByName(username);

        return owner
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found."));
    }

    public static OwnerDto toDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setName(owner.getName());
        ownerDto.setDob(owner.getDob());
        ownerDto.setPassword(owner.getPassword());

        return ownerDto;
    }

    private OwnerResponse toResponse(OwnerDto ownerDto) {
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setName(ownerDto.getName());
        ownerResponse.setDob(ownerDto.getDob());
        return ownerResponse;
    }

    public static Owner toModel(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setDob(ownerDto.getDob());
        owner.setPassword(ownerDto.getPassword());
        owner.setRole("ROLE_USER");
        return owner;
    }
}
