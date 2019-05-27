package net.tcheltsou.springmvclearning.web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Controller
public class FileUploadController {

	@GetMapping("/upload")
	public String handleGetUpload(){
		return "upload_form";
	}

	@PostMapping("/upload")
	public String handlePostUpload(@RequestParam("file1") MultipartFile file1,
								   @RequestParam("file2") MultipartFile file2,
								   @RequestParam("entity") String entity, Model model) throws IOException {
		String path = getClass().getClassLoader().getResource("")
				.getPath().split("/WEB-INF/classes/")[0] + "/upload/" + entity + "/";
		System.out.println(path);
		//Files.createDirectories(Paths.get(path));

		model.addAttribute("message", "There are not any files");
		Stream.of(file1, file2)
				.filter(it -> !it.isEmpty())
				.peek(it -> {
					try {
						File file = FileUtils.getFile(path, it.getOriginalFilename());
						file.mkdirs();
						it.transferTo(file);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				})
				.findAny()
				.ifPresent(x -> model.addAttribute("message", "Files were uploaded"));
		return "greeting";
	}
}
