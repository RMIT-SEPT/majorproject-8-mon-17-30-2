package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.dto.AdminSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.service.AdminService;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://agmemonday2.com.s3-website-us-east-1.amazonaws.com")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
    @GetMapping("/api/admin")
	public Iterable<Admin> getAllAdmins(){
		return adminService.findAll();
	}

	@GetMapping("/api/admin/{adminId}")
	public ResponseEntity<?> getAdminById(@PathVariable Long adminId){
		AdminSummary admin = adminService.findByIdDTO(adminId);
		return new ResponseEntity<>(admin, admin != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
