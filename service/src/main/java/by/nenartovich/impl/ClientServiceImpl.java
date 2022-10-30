package by.nenartovich.impl;


import by.nenartovich.ClientService;
import by.nenartovich.dto.ClientDto;
import by.nenartovich.mappers.ClientMapper;
import by.nenartovich.repository.dao.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto save(ClientDto clientDto) {
        return clientMapper
                .clientToClientDto(clientRepository.save(clientMapper.clientDtoToClient(clientDto)));
    }

    @Override
    public Page<ClientDto> findAllPaginated(int pageNumber, int pageSize,String sortField) {
        Sort sort = Sort.by(sortField).ascending();
        Pageable paged = PageRequest.of(pageNumber-1, pageSize, sort);
        return clientRepository.findAll(paged).map(clientMapper::clientToClientDto);
    }
}
