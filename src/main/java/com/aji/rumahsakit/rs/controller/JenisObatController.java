package com.aji.rumahsakit.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aji.rumahsakit.rs.exception.MessageResponse;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.JenisObat;
import com.aji.rumahsakit.rs.service.JenisObatService;

@RestController
@Controller
public class JenisObatController {

	@Autowired
	private JenisObatService jnsObatService;
	
	@RequestMapping(value = "/jenisobat/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 JenisObat jnsObat=jnsObatService.findByKode(kode);

			 return ResponseEntity.ok(jnsObat);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/jenisobat", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters() throws ValidationException
	{
		 try 
		 {
			List<JenisObat> jnsObat=jnsObatService.findByFilter();

			return ResponseEntity.ok(jnsObat);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/jenisobat", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody JenisObat jnsObat) throws ValidationException{
		try {
			jnsObatService.save(jnsObat);
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value =  "/jenisobat", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody JenisObat jnsObat) throws Exception
	{
		 try 
		 {
			 jnsObatService.update(jnsObat);
			 MessageResponse mg = new MessageResponse("Update Success with id " + jnsObat.getId());
			 return ResponseEntity.ok(mg);
		 }
		 		 
		 catch(ValidationException val) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
				
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
}
