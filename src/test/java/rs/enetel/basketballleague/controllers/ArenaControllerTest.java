package rs.enetel.basketballleague.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.repositories.ArenaRepository;
import rs.enetel.basketballleague.services.ArenaService;

public class ArenaControllerTest
{

	ArenaController arenaController;

	@Mock
	ArenaRepository arenaRepository;
	
	@Mock
	ArenaService arenaService;
	
	@Mock
	Model model;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		arenaController = new ArenaController(arenaRepository, arenaService);
	}
	
	@Test
	public void showAllTestMockMvc() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(arenaController).build();
		
		mockMvc.perform(get("/arenas"))
				.andExpect(status().isOk())
				.andExpect(view().name("arenas/all"));
	}
	
	@Test
	public void showAllTest() throws Exception
	{
		// Given
		String templatePath;
		
		List<Arena> arenas = new ArrayList();
		arenas.add(new Arena());
		arenas.add(new Arena());
		
		when(arenaRepository.all()).thenReturn(arenas);
		
		ArgumentCaptor<List<Arena>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		
		// When
		templatePath = arenaController.showAll(model);
		
		// Then
		assertEquals("arenas/all", templatePath);
		verify(arenaRepository, times(1)).all();
		verify(model, times(1)).addAttribute(eq("arenas"), argumentCaptor.capture());
		List<Arena> listInController = argumentCaptor.getValue();
		assertEquals(2, listInController.size());
	}
	
	@Test
	public void showTestMockMvc() throws Exception {
		Arena arena = new Arena();
		arena.setId(1);
		
		when(arenaRepository.getById(anyInt())).thenReturn(arena);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(arenaController).build();
		
		mockMvc.perform(get("/arenas/show/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("arenas/show"));
	}
}
