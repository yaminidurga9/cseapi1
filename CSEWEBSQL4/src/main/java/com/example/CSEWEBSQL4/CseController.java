package com.example.CSEWEBSQL4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CseController {
	@Autowired
	StudentRepo repo1;
	@Autowired
	SubjectsRepo repo2;
	
	@PostMapping("/insert")
	public String savedata(@RequestBody CseStudent data) {
		repo1.save(data);
		return "success";
	}
	@GetMapping("/cse/display")
	public List<CseStudent> getdata(){
		return repo1.findAll();
	}
	@GetMapping("/cse/subname/{sub}")
	public List<CseStudent> getbysub(@PathVariable String sub){
		return repo1.FindBySubjectsSubName(sub);
		
	}
	@GetMapping("/rocks")
	@org.springframework.transaction.annotation.Transactional
	public String getRocks() {
	    List<CseStudent> students = repo1.findAll();
	    List<CseSubjects> subjects = repo2.findAll();

	    StringBuilder sb = new StringBuilder();

	    // Student → Subjects
	    for (CseStudent s : students) {
	        sb.append(s.getName()).append(": ");
	        if (s.getSubjects() == null || s.getSubjects().isEmpty()) {
	            sb.append("No Subjects,\n");
	            continue;
	        }
	        String subjectNames = s.getSubjects().stream()
	                .map(CseSubjects::getSubname)
	                .collect(Collectors.joining(", "));
	        sb.append(subjectNames).append(",\n");
	    }

	    sb.append("\n");

	    // Subject → Students (merged properly)
	    Map<String, List<String>> subjectToStudents = new LinkedHashMap<>();
	    for (CseSubjects sub : subjects) {
	        if (sub.getStudents() != null) {
	            for (CseStudent st : sub.getStudents()) {
	                subjectToStudents
	                    .computeIfAbsent(sub.getSubname(), k -> new ArrayList<>())
	                    .add(st.getName());
	            }
	        }
	    }

	    for (Map.Entry<String, List<String>> entry : subjectToStudents.entrySet()) {
	        String subjectName = entry.getKey();
	        String studentNames = String.join(", ", entry.getValue());
	        sb.append(subjectName).append(": ").append(studentNames).append(",\n");
	    }

	    return sb.toString();
	}
}