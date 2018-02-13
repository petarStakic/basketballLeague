package rs.enetel.basketballleague.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

		try
		{
			model.addAttribute("arenas", arenaRepository.all());
		}
		catch (Exception e)
		{
			System.out.println("Error obtaining arrenas list: " + e.getMessage());
			e.printStackTrace();
			log.debug("Error in ArenaController: ", e);
		}

		return "arenas/all";
	}
}
