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
import com.aji.rumahsakit.rs.model.master.ListKamar;
import com.aji.rumahsakit.rs.service.ListKamarService;

@RestController
@Controller
public class ListKamarController {

	@Autowired
	private ListKamarService listKamarService;
	
	@RequestMapping(value = "/listkamar/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 ListKamar listKamar=listKamarService.findByKode(kode);

			 return ResponseEntity.ok(listKamar);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/listkamar/nama/{nama}/lantai/{lantai}/jenis/{jenis}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String nama,@PathVariable String lantai,@PathVariable String jenis) throws ValidationException
	{
		 try 
		 {
			List<ListKamar> listKamarList=listKamarService.findByFilter(nama, lantai, jenis);

			return ResponseEntity.ok(listKamarList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/listkamar", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			List<ListKamar> listKamarList=listKamarService.findAll();

			return ResponseEntity.ok(listKamarList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/listkamar", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody ListKamar listKamar) throws ValidationException{
		try {
			listKamarService.save(listKamar);
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
	
	@RequestMapping(value =  "/listkamar", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody ListKamar listKamar) throws Exception
	{
		 try 
		 {
			 listKamarService.update(listKamar);
			 MessageResponse mg = new MessageResponse("Update Success with id " + listKamar.getId());
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
