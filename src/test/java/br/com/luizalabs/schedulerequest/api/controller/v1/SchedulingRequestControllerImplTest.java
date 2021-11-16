package br.com.luizalabs.schedulerequest.api.controller.v1;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.AddresseeForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.mapper.impl.EntityMapperImpl;
import br.com.luizalabs.schedulerequest.domain.service.v1.ScheduleRequestServiceImpl;
import br.com.luizalabs.schedulerequest.repository.AddresseeRepository;
import br.com.luizalabs.schedulerequest.repository.SchedulingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SchedulingRequestControllerImpl.class})
@ExtendWith(SpringExtension.class)
class SchedulingRequestControllerImplTest {

    @MockBean
    private ScheduleRequestServiceImpl scheduleRequestServiceImpl;

    @Autowired
    private SchedulingRequestControllerImpl schedulingRequestControllerImpl;

    @Test
    void testFindByStatusSchedulingRequest() {
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(new ArrayList<Scheduling>()));
        AddresseeRepository addresseeRepository = mock(AddresseeRepository.class);
        ResponseEntity<List<SchedulingDTO>> actualFindByStatusSchedulingRequestResult = (new SchedulingRequestControllerImpl(
                new ScheduleRequestServiceImpl(schedulingRepository, addresseeRepository, new EntityMapperImpl())))
                .findByStatusSchedulingRequest(StatusOfSchedule.PENDING, null);
        assertEquals("<200 OK OK,[],[totalElements:\"0\", Access-Control-Expose-Headers:\"totalElements\"]>",
                actualFindByStatusSchedulingRequestResult.toString());
        assertTrue(actualFindByStatusSchedulingRequestResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByStatusSchedulingRequestResult.getStatusCode());
        assertEquals(2, actualFindByStatusSchedulingRequestResult.getHeaders().size());
        verify(schedulingRepository).findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindByStatusSchedulingRequest2() {
        Addressee addressee = new Addressee();
        addressee.setEmail("andersonfreitas21@gmail.com");
        addressee.setSchedules(new ArrayList<Scheduling>());
        addressee.setContactNumber("(88)997128191");
        addressee.setCreatedAt(LocalDateTime.now());
        addressee.setUpdatedAt(LocalDateTime.now());
        addressee.setAddressee("Anderson Freitas");
        addressee.setUuid(UUID.randomUUID());

        Scheduling scheduling = new Scheduling();
        scheduling.setMessage("Not all who wander are lost");
        scheduling.setType(TypeToSend.WHATSAPP);
        scheduling.setAddressee(addressee);
        scheduling.setStatus(StatusOfSchedule.PENDING);
        scheduling.setCreatedAt(LocalDateTime.now());
        scheduling.setSendDate(LocalDateTime.now());
        scheduling.setUpdatedAt(LocalDateTime.now());
        scheduling.setUuid(UUID.randomUUID());

        ArrayList<Scheduling> schedulingList = new ArrayList<Scheduling>();
        schedulingList.add(scheduling);
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<Scheduling>(schedulingList));
        AddresseeRepository addresseeRepository = mock(AddresseeRepository.class);
        ResponseEntity<List<SchedulingDTO>> actualFindByStatusSchedulingRequestResult = (new SchedulingRequestControllerImpl(
                new ScheduleRequestServiceImpl(schedulingRepository, addresseeRepository, new EntityMapperImpl())))
                .findByStatusSchedulingRequest(StatusOfSchedule.PENDING, null);
        assertTrue(actualFindByStatusSchedulingRequestResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByStatusSchedulingRequestResult.getStatusCode());
        assertEquals(2, actualFindByStatusSchedulingRequestResult.getHeaders().size());
        verify(schedulingRepository).findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindByStatusSchedulingRequest3() {
        Addressee addressee = new Addressee();
        addressee.setEmail("andersonfreitas21@gmail.com");
        addressee.setSchedules(new ArrayList<Scheduling>());
        addressee.setContactNumber("(88)997128191");
        addressee.setCreatedAt(LocalDateTime.now());
        addressee.setUpdatedAt(LocalDateTime.now());
        addressee.setAddressee("Anderson Freitas");
        addressee.setUuid(UUID.randomUUID());

        Scheduling scheduling = new Scheduling();
        scheduling.setMessage("Not all who wander are lost");
        scheduling.setType(TypeToSend.WHATSAPP);
        scheduling.setAddressee(addressee);
        scheduling.setStatus(StatusOfSchedule.PENDING);
        scheduling.setCreatedAt(LocalDateTime.now());
        scheduling.setSendDate(LocalDateTime.now());
        scheduling.setUpdatedAt(LocalDateTime.now());
        scheduling.setUuid(UUID.randomUUID());

        Addressee addressee1 = new Addressee();
        addressee1.setEmail("andersonfreitas21@gmail.com");
        addressee1.setSchedules(new ArrayList<Scheduling>());
        addressee1.setContactNumber("(88)997128191");
        addressee1.setCreatedAt(LocalDateTime.now());
        addressee1.setUpdatedAt(LocalDateTime.now());
        addressee1.setAddressee("Anderson Freitas");
        addressee1.setUuid(UUID.randomUUID());

        Scheduling scheduling1 = new Scheduling();
        scheduling1.setMessage("Not all who wander are lost");
        scheduling1.setType(TypeToSend.WHATSAPP);
        scheduling1.setAddressee(addressee1);
        scheduling1.setStatus(StatusOfSchedule.PENDING);
        scheduling1.setCreatedAt(LocalDateTime.now());
        scheduling1.setSendDate(LocalDateTime.now());
        scheduling1.setUpdatedAt(LocalDateTime.now());
        scheduling1.setUuid(UUID.randomUUID());

        ArrayList<Scheduling> schedulingList = new ArrayList<Scheduling>();
        schedulingList.add(scheduling1);
        schedulingList.add(scheduling);
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<Scheduling>(schedulingList));
        AddresseeRepository addresseeRepository = mock(AddresseeRepository.class);
        ResponseEntity<List<SchedulingDTO>> actualFindByStatusSchedulingRequestResult = (new SchedulingRequestControllerImpl(
                new ScheduleRequestServiceImpl(schedulingRepository, addresseeRepository, new EntityMapperImpl())))
                .findByStatusSchedulingRequest(StatusOfSchedule.PENDING, null);
        assertTrue(actualFindByStatusSchedulingRequestResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByStatusSchedulingRequestResult.getStatusCode());
        assertEquals(2, actualFindByStatusSchedulingRequestResult.getHeaders().size());
        verify(schedulingRepository).findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindByStatusSchedulingRequest4() {
        ScheduleRequestServiceImpl scheduleRequestServiceImpl = mock(ScheduleRequestServiceImpl.class);
        when(scheduleRequestServiceImpl.find((StatusOfSchedule) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<SchedulingDTO>(new ArrayList<SchedulingDTO>()));
        ResponseEntity<List<SchedulingDTO>> actualFindByStatusSchedulingRequestResult = (new SchedulingRequestControllerImpl(
                scheduleRequestServiceImpl)).findByStatusSchedulingRequest(StatusOfSchedule.PENDING, null);
        assertEquals("<200 OK OK,[],[totalElements:\"0\", Access-Control-Expose-Headers:\"totalElements\"]>",
                actualFindByStatusSchedulingRequestResult.toString());
        assertTrue(actualFindByStatusSchedulingRequestResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByStatusSchedulingRequestResult.getStatusCode());
        assertEquals(2, actualFindByStatusSchedulingRequestResult.getHeaders().size());
        verify(scheduleRequestServiceImpl).find((StatusOfSchedule) any(), (org.springframework.data.domain.Pageable) any());
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(this.scheduleRequestServiceImpl).delete((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/schedule/{uuid}",
                "01234567-89AB-CDEF-FEDC-BA9876543210");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.schedulingRequestControllerImpl)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(this.scheduleRequestServiceImpl).delete((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/schedule/{uuid}",
                "01234567-89AB-CDEF-FEDC-BA9876543210");
        deleteResult.contentType("Not all who wander are lost");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.schedulingRequestControllerImpl)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testFilter() {
        SchedulingFilter schedulingFilter = new SchedulingFilter();
        assertNull(this.schedulingRequestControllerImpl.filter(schedulingFilter, true, 1, 3, new String[]{"Properties"},
                Sort.Direction.ASC,
                new DelegatingServerHttpResponse(new DelegatingServerHttpResponse(new DelegatingServerHttpResponse(
                        new DelegatingServerHttpResponse(new ServletServerHttpResponse(new Response())))))));
    }

    @Test
    void create() {
    }

    @Test
    void findByStatusSchedulingRequest() {
    }

    @Test
    void delete() {
    }

    @Test
    void testCreate() throws Exception {
        SchedulingForm schedulingForm = new SchedulingForm();
        schedulingForm.setAddressee(new AddresseeForm("Anderson Freitas", "andersonfreitas21@gmail.com", "(88)997128191"));
        schedulingForm.setMessage("Not all who wander are lost");
        schedulingForm.setSendDate(null);
        String content = (new ObjectMapper()).writeValueAsString(schedulingForm);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/v1/schedule");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("toSend", String.valueOf(TypeToSend.WHATSAPP))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.schedulingRequestControllerImpl)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }


}