package com.example.CinemaBooking;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.repo.CinemaRoomRepository;
import com.example.CinemaBooking.repo.SeatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Folosește H2 în memorie
@AutoConfigureMockMvc
class CinemaRoomControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    private SeatRepository seatRepository;

    @BeforeEach
    void setUp() {
        // Curăță baza de date înainte de fiecare test
        cinemaRoomRepository.deleteAll();
        seatRepository.deleteAll();
    }

//    @Test
//    void whenCreateValidCinemaRoom_thenReturnCreatedRoomWithSeats() throws Exception {
//        // Given
//        CinemaRoomDto dto = new CinemaRoomDto();
//        dto.setName("Sala Premium");
//        dto.setRowsCount(5);
//        dto.setColsCount(10);
//
//        // When
//        mockMvc.perform(post("/api/cinemaroom")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Sala Premium"))
//                .andExpect(jsonPath("$.rowsCount").value(5))
//                .andExpect(jsonPath("$.colsCount").value(10))
//                .andExpect(jsonPath("$.id").exists());
//
//        // Then - Verifică în baza de date
//        CinemaRoom savedRoom = cinemaRoomRepository.findAll().get(0);
//        assertEquals(1, savedRoom.getId());
//        assertEquals(50, seatRepository.count()); // 5 rows * 10 cols = 50 seats
//    }

//    @Test
//    void whenCreateCinemaRoomWithInvalidDto_thenReturnBadRequest() throws Exception {
//        // Given
//        CinemaRoomDto invalidDto = new CinemaRoomDto();
//        invalidDto.setName(""); // Nume gol
//        invalidDto.setRowsCount(0); // Rows invalide
//
//        // When & Then
//        mockMvc.perform(post("/api/cinemaroom")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(invalidDto)))
//                .andExpect(status().isBadRequest());
//
//        // Verifică că nimic nu s-a salvat
//        assertTrue(cinemaRoomRepository.findAll().isEmpty());
//        assertEquals(0, seatRepository.count());
//    }
}