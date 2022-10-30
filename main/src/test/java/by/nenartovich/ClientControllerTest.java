package by.nenartovich;

import by.nenartovich.dto.ClientDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.ResourceUtils.getFile;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    private TypeReference<List<ClientDto>> typeReference = new TypeReference<>() {
    };
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void when_getAllClients_then_returnSuccess() throws Exception {
        List<ClientDto> actual = loadObjectFromString(loadFile("case01/clientDtoResponse.json"),typeReference);
                String contentAsString = mockMvc.perform(get("/get_all_client/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        List<ClientDto> expected = loadObjectFromString(contentAsString, typeReference);
        assertThat(actual).isEqualTo(expected);
    }

    private <T> List<T> loadObjectFromString(String fileAsString, TypeReference<List<T>> typeReference) throws JsonProcessingException {
        return objectMapper.readValue(fileAsString, typeReference);
    }
    private String loadFile(String fileName) throws IOException {
        return Files.readString(getFile("classpath:" +"client/"+ fileName).toPath());
    }
}
