package rs.enetel.basketballleague.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.repositories.ArenaRepository;

@Controller
@Slf4j
public class ArenaController
{
	ArenaRepository arenaRepository;

	public ArenaController(ArenaRepository arenaRepository)
	{
		this.arenaRepository = arenaRepository;
	}

	@RequestMapping("/arenas")
	public String showAll(Model model)
	{

		model.addAttribute("arenas", arenaRepository.all());

		return "arenas/all";
	}

	@RequestMapping("/arenas/show/{id}")
	public String showById(@PathVariable int id, Model model)
	{

		model.addAttribute("arena", arenaRepository.getById(id));

		return "arenas/show";
	}
}
