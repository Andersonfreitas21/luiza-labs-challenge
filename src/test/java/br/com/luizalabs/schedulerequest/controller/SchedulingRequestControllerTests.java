package br.com.luizalabs.schedulerequest.controller;

import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.repository.AddresseeRepository;
import br.com.luizalabs.schedulerequest.repository.SchedulingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class SchedulingRequestControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private AddresseeRepository addresseeRepository;

    private MockMvc mockMvc;

    private String request;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        this.request =
                "{\n" +
                        "  \"addressee\": {\n" +
                        "    \"addresseeName\": \"Luiz Felipe\",\n" +
                        "    \"contactNumber\": \"(88)997128192\",\n" +
                        "    \"email\": \"fepus@gmail.com\"\n" +
                        "  },\n" +
                        "  \"message\": \"Mensagem a ser enviada\",\n" +
                        "  \"send_date\": \"2021-11-11 22:18:01\"\n" +
                        "}";
    }

    @Test
    @Order(1)
    void shouldCreateSchedulingRequest() throws Exception {
        this.mockMvc
                .perform(post("/api/v1/schedule").content(this.request)
                        .param("Type", "SMS")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void shouldFindByStatusSchedulingRequest() throws Exception {

        MultiValueMap<String, String> requestParamsPage = new LinkedMultiValueMap<>();
        requestParamsPage.add("page", String.valueOf(0));
        requestParamsPage.add("size", String.valueOf(5));

        mockMvc.perform(get("/api/v1/schedule")
                        .param("StatusOfSchedule", "PENDING")
                        .params(requestParamsPage)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content[0].uuid", is("6317c601-f842-4e15-ba2e-00d436bd30bb")))
                .andExpect(jsonPath("$.content[0].sendDate", is("2021-11-11T22:18:01")))
                .andExpect(jsonPath("$.content[0].addressee.addressee", is("Luiz Felipe")))
                .andExpect(jsonPath("$.content[0].addressee.email", is("fepus@gmail.com")))
                .andExpect(jsonPath("$.content[0].addressee.contactNumber", is("(88)997128192")))
                .andExpect(
                        jsonPath("$.content[0].message", is("Mensagem a ser enviada")))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void shouldDeleteSchedule() throws Exception {
        Scheduling schedule = schedulingRepository.findAll().get(0);
        assertNotNull(schedule);

        String uuid = schedule.getUuid().toString();

        this.mockMvc
                .perform(delete("/api/v1/schedule/" + uuid).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/schedule/deleted").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content[0].recipient.recipient", is("muriloalvesdev.ti@gmail.com")))
                .andExpect(
                        jsonPath("$.content[0].message", is("VOCÊ FOI APROVADO EM NOSSO PROCESSO SELETIVO!")))
                .andExpect(jsonPath("$.content[0].type", is("EMAIL")))
                .andExpect(jsonPath("$.content[0].status", is("DELETED")))
                .andExpect(jsonPath("$.content[0].send_date", is("2020-11-01 23:59:59")))
                .andExpect(status().isOk());

        schedulingRepository.deleteAll();
        addresseeRepository.deleteAll();
    }
}
