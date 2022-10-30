package by.nenartovich.rest;

import by.nenartovich.ClientService;
import by.nenartovich.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class ClientController {
    private static final Logger logger = LogManager.getLogger(ClientController.class);

    private static final int PAGE_SIZE = 10;
    private static final String SORT_FIELD = "email";

    private final ClientService clientService;

    @GetMapping("/get_all_client/{pageNumber}")
    public ResponseEntity<List<ClientDto>> getProductClient(@PathVariable("pageNumber") int pageNumber) {
        logger.info("Request : pageNumber = {}", pageNumber);
        List<ClientDto> clientsDto = clientService.findAllPaginated(pageNumber, PAGE_SIZE, SORT_FIELD).toList();
        logger.info("Response: {}", clientsDto);
        return ResponseEntity.ok(clientsDto);
    }

    @PostMapping("/save_client")
    public ResponseEntity<ClientDto> getQueryResult(@RequestBody ClientDto clientDto) {
        logger.info("Request : Client[{}]", clientDto);
        ClientDto savedClientDto = clientService.save(clientDto);
        logger.info("Response: {}", savedClientDto);
        return ResponseEntity.ok(savedClientDto);
    }
}
