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
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.repositories.ArenaRepository;
import rs.enetel.basketballleague.services.ArenaService;

public class ArenaControllerTest
{

	ArenaController arenaController;

	MockMvc mockMvc;

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

		mockMvc = MockMvcBuilders.standaloneSetup(arenaController).build();
	}

	@Test
	public void showAllTestMockMvc() throws Exception
	{
		mockMvc.perform(get("/arenas")).andExpect(status().isOk()).andExpect(view().name("arenas/all"))
				.andExpect(model().attributeExists("arenas"));
	}

	@Test
	public void showAllTest() throws Exception
	{
		// Given
		String templatePath;

		List<Arena> arenas = new ArrayList<>();
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
	public void showTestMockMvc() throws Exception
	{
		Arena arena = new Arena();
		arena.setId(1);

		when(arenaRepository.getById(anyInt())).thenReturn(arena);

		mockMvc.perform(get("/arenas/show/1")).andExpect(status().isOk()).andExpect(view().name("arenas/show"))
				.andExpect(model().attributeExists("arena"));
	}

	@Test
	public void showFormForNewObjectTestMockMvc() throws Exception
	{
		mockMvc.perform(get("/arenas/new")).andExpect(status().isOk()).andExpect(view().name("arenas/save"))
				.andExpect(model().attributeExists("arena"));
	}

	@Test
	public void showFormToUpdateObjectTestMockMvc() throws Exception
	{
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setId(1);

		when(arenaService.getCommandById(1)).thenReturn(arenaCmd);

		mockMvc.perform(get("/arenas/1/edit")).andExpect(status().isOk()).andExpect(view().name("arenas/save"))
				.andExpect(model().attributeExists("arena"));

	}

	@Test
	public void insertOrUpdateObjectTestUpdateMockMvc() throws Exception
	{
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setId(1);

		when(arenaService.editExistng(any())).thenReturn(arenaCmd);

		mockMvc.perform(post("/arenas/save").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/arenas/1/show"));
	}

	@Test
	public void insertOrUpdateObjectTestInsertMockMvc() throws Exception
	{

		ArenaCommand arenaCmdOut = new ArenaCommand();
		arenaCmdOut.setId(3);

		when(arenaService.addNew(any())).thenReturn(arenaCmdOut);

		mockMvc.perform(post("/arenas/save").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "")
				.param("name", "Some Arena")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/arenas/3/show"));
	}

	@Test
	public void deactivateObjectTestMockMvc() throws Exception
	{
		mockMvc.perform(get("/arenas/2/deactivate")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/arenas"));
		
		verify(arenaService, times(1)).getCommandById(2);
		verify(arenaService, times(1)).deactivate(any());
	}
}
