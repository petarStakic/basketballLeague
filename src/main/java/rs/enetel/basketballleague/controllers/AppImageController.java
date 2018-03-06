package rs.enetel.basketballleague.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.enetel.basketballleague.dao.AppImage;
import rs.enetel.basketballleague.repositories.AppImageRepository;

@Controller
@RequestMapping("/img")
public class AppImageController
{
	AppImageRepository appImgRepository;

	public AppImageController(AppImageRepository appImgRepository)
	{
		this.appImgRepository = appImgRepository;
	}

	@GetMapping("/{id}/show")
	public void showById(@PathVariable String id, HttpServletResponse response) throws IOException
	{

		AppImage image = appImgRepository.getById(Integer.valueOf(id));
		Byte[] imgContent = image.getContent();

		if (imgContent != null)
		{
			byte[] byteArray = new byte[imgContent.length];

			int i = 0;
			for (Byte wrappedByte : imgContent)
			{
				byteArray[i++] = wrappedByte;
			}

			response.setContentType("image/" + image.getTypeExtension());

			InputStream inputStream = new ByteArrayInputStream(byteArray);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
	}
}
