package rs.enetel.basketballleague.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.repositories.ArenaRepository;
import rs.enetel.basketballleague.services.ArenaService;

@Controller
@RequestMapping("/arenas")
@Slf4j
public class ArenaController
{
	ArenaRepository arenaRepository;

	ArenaService arenaService;

	public ArenaController(ArenaRepository arenaRepository, ArenaService arenaService)
	{
		this.arenaRepository = arenaRepository;
		this.arenaService = arenaService;
	}

	@GetMapping
	@RequestMapping({"","/"})
	public String showAll(Model model)
	{
		model.addAttribute("arenas", arenaRepository.all());

		return "arenas/all";
	}

	@GetMapping
	@RequestMapping("/{id}/show")
	public String showById(@PathVariable int id, Model model)
	{

		model.addAttribute("arena", arenaRepository.getById(id));

		return "arenas/show";
	}

	@GetMapping
	@RequestMapping("/new")
	public String showFormForNewObject(Model model)
	{

		model.addAttribute("arena", new ArenaCommand());

		return "arenas/save";
	}

	@GetMapping
	@RequestMapping("/{id}/edit")
	public String showFormToUpdateObject(@PathVariable int id, Model model)
	{
		try
		{
			model.addAttribute("arena", arenaService.getCommandById(id));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "arenas/save";
	}

	@PostMapping
	@RequestMapping("/save")
	public String insertOrUpdateObject(@ModelAttribute ArenaCommand command)
	{
		ArenaCommand savedCommand = null;

		if (command.getId() == null)
		{
			try
			{
				savedCommand = arenaService.addNew(command);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				log.debug("Error in controller", e);
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				savedCommand = arenaService.editExistng(command);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				log.debug("Error in controller", e);
				e.printStackTrace();
			}
		}

		return "redirect:/arenas/" + savedCommand.getId() + "/show";
	}

	@GetMapping
	@RequestMapping("/{id}/deactivate")
	public String deactivateObject(@PathVariable int id)
	{
		try
		{
			arenaService.deactivate(arenaService.getCommandById(id));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/arenas";
	}
}
