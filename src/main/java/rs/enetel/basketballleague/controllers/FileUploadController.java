package rs.enetel.basketballleague.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.common.UploadedFile;

@Slf4j
@Controller
@RequestMapping("/file-tmp")
public class FileUploadController
{
	private UploadedFile uploadedFile;

	public FileUploadController()
	{
		uploadedFile = new UploadedFile();
	}

	@GetMapping("/get/{value}")
	public void get(HttpServletResponse response, @PathVariable String value)
	{
		log.debug("Getting uploaded temp image...");
		try
		{
			response.setContentType(uploadedFile.getType());
			response.setContentLength(uploadedFile.getLength());
			FileCopyUtils.copy(uploadedFile.getBytes(), response.getOutputStream());
		}
		catch (IOException e)
		{
			log.error("Error fetching a temporary file: " + e.getMessage(), e);
		}
	}

	@PostMapping("/upload")
	public @ResponseBody String uoload(MultipartHttpServletRequest request, HttpServletResponse response)
	{
		Iterator<String> iterator = request.getFileNames();

		MultipartFile multipartFile = request.getFile(iterator.next());
		log.debug(multipartFile.getOriginalFilename() + " uploaded");

		try
		{
			uploadedFile.setLength(multipartFile.getBytes().length);
			uploadedFile.setBytes(multipartFile.getBytes());
			uploadedFile.setType(multipartFile.getContentType());
			uploadedFile.setName(multipartFile.getOriginalFilename());
		}
		catch (IOException e)
		{
			// TODO: uraditi mozda nesto bolje ovde
			log.error("Error uploading a file: " + e.getMessage(), e);
		}

		return "/file-tmp/get/" + Calendar.getInstance().getTimeInMillis();
	}
}
