package by.nenartovich;


import by.nenartovich.dto.ClientDto;
import org.springframework.data.domain.Page;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    Page<ClientDto> findAllPaginated(int pageNumber, int pageSize, String sortField);
}
