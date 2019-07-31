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
import com.aji.rumahsakit.rs.model.master.JenisKamar;
import com.aji.rumahsakit.rs.service.JenisKamarService;

@RestController
@Controller
public class JenisKamarController {

	@Autowired
	private JenisKamarService jenisKamarService;
	
	@RequestMapping(value = "/jeniskamar/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 JenisKamar jnsKamar=jenisKamarService.findByKode(kode);

			 return ResponseEntity.ok(jnsKamar);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/jeniskamar/nama/{nama}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String nama) throws ValidationException
	{
		 try 
		 {
			List<JenisKamar> jnsKamarList=jenisKamarService.findByFilter(nama);

			return ResponseEntity.ok(jnsKamarList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}

	@RequestMapping(value = "/jeniskamar", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			List<JenisKamar> jnsKamarList=jenisKamarService.findAll();

			return ResponseEntity.ok(jnsKamarList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}

	
	@RequestMapping(value = "/jeniskamar", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody JenisKamar jnsKamar) throws ValidationException{
		try {
			jenisKamarService.save(jnsKamar);
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
	
	@RequestMapping(value =  "/jeniskamar", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody JenisKamar jnsKamar) throws Exception
	{
		 try 
		 {
			 jenisKamarService.update(jnsKamar);
			 MessageResponse mg = new MessageResponse("Update Success with id " + jnsKamar.getId());
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
