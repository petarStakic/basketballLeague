package rs.enetel.basketballleague.controllers;

import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.dao.AppImage;
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

	@GetMapping({ "", "/" })
	public String showAll(Model model)
	{
		model.addAttribute("arenas", arenaRepository.all());

		return "arenas/all";
	}

	@GetMapping("/{id}/show")
	public String showById(@PathVariable int id, Model model)
	{

		model.addAttribute("arena", arenaRepository.getById(id));

		return "arenas/show";
	}

	@GetMapping("/new")
	public String showFormForNewObject(Model model)
	{
		ArenaCommand arenaCmd = new ArenaCommand();
		AppImage img = new AppImage();
		img.setId(1); // id default slike TODO: razmisliti jel postoji bolje resenje nego hardkodirati ovako
		arenaCmd.setImage(img);

		model.addAttribute("arena", arenaCmd);

		return "arenas/save";
	}

	@GetMapping("/{id}/edit")
	public String showFormToUpdateObject(@PathVariable int id, Model model)
	{

		model.addAttribute("arena", arenaService.getCommandById(id));

		return "arenas/save";
	}

	@PostMapping("/save")
	public String insertOrUpdateObject(@ModelAttribute ArenaCommand command,
			@RequestParam("img-file") MultipartFile imgFile)
	{
		ArenaCommand savedCommand = null;
		boolean insertImage = command.getImageChanged().equals("changed");

		if (insertImage)
		{
			AppImage appImage = new AppImage();

			try
			{
				appImage.setContent(ArrayUtils.toObject(imgFile.getBytes()));

				String contentType = imgFile.getContentType();
				if (contentType.startsWith("image/"))
				{
					appImage.setTypeExtension(contentType.substring(6));
				}
				else
				{
					throw new RuntimeException("Not a valid MIME type");
				}
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				log.debug("Error while storing an image", e);
			}
			
			command.setImage(appImage);
		}

		if (command.getId() == null)
		{
			savedCommand = arenaService.addNew(command);
		}
		else
		{
			savedCommand = arenaService.editExistng(command);
		}

		return "redirect:/arenas/" + savedCommand.getId() + "/show";
	}

	@GetMapping("/{id}/deactivate")
	public String deactivateObject(@PathVariable int id)
	{
		arenaService.deactivate(arenaService.getCommandById(id));

		return "redirect:/arenas";
	}
}
