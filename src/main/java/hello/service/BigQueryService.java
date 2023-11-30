package hello.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.bigquery.core.BigQueryTemplate;
import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.InsertAllRequest.Builder;
import com.google.cloud.bigquery.InsertAllResponse;

import hello.model.Employee;
import hello.utils.BigQueryUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BigQueryService {

	@Autowired
	BigQueryTemplate bigQueryTemplate;

	@Autowired
	BigQuery bigquery;

	@Autowired
	BigQueryUtils bigQueryUtils;

	@Value("${elm.gcp.bigquery.table-name}")
	private String tableName;

	@Value("${spring.cloud.gcp.bigquery.dataset-name}")
	private String datasetName;

	public String getDatasetName() throws IOException {
		return this.bigQueryTemplate.getDatasetName();
	}

	public String InsertAll() throws InterruptedException {

		Builder InsertAllRequestBuilder = InsertAllRequest.newBuilder(datasetName, tableName);
		List<Employee> employeelist = new ArrayList<>();
		employeelist.add(new Employee(1001, "Dev1", "Das1", "dev1@mail.com"));
		employeelist.add(new Employee(1001, "Dev2", "Das2", "dev2@mail.com"));
		for (Employee e : employeelist) {
			InsertAllRequestBuilder.addRow(bigQueryUtils.getRow(e));
		}
		InsertAllRequest insertAllRequest = InsertAllRequestBuilder.build();
		InsertAllResponse insertAllResponse = bigquery.insertAll(insertAllRequest);

		if (insertAllResponse.hasErrors()) {
			insertAllResponse.getInsertErrors().entrySet().forEach(e -> System.out
					.println("Error in entry with id-" + e.getKey() + " : Details>> " + e.getValue().toString()));
		}
		return null;
	}
}
