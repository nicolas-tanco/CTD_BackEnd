package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.util.Jsons;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OdontologoControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void buscarPorId() throws Exception {
        OdontologoDto od = new OdontologoDto();
        this.mvc.perform(MockMvcRequestBuilders.post("/odontologos/nuevo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(od)));
        MvcResult resp = this.mvc.perform(MockMvcRequestBuilders.get("/odontologos/10"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();


        Assert.assertEquals(resp.getResolvedException().getMessage(),"El id no existe.");

    }

    @Test
    void crear() throws Exception {
        OdontologoDto od = new OdontologoDto();
        MvcResult response = this.mvc.perform(MockMvcRequestBuilders.post("/odontologos/nuevo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(od)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}