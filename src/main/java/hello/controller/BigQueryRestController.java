package hello.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.service.BigQueryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class BigQueryRestController {
	
	@Autowired
	BigQueryService bigQueryService;

	@PostMapping("/writeToBigQuery")
	  public String publishMessage() throws IOException, InterruptedException {
		return bigQueryService.InsertAll();
	  }
	
	@GetMapping("/getDataSetName")
	  public String getDataSetName() throws IOException {
		return bigQueryService.getDatasetName();
	  }
}
