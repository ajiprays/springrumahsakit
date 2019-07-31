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
import com.aji.rumahsakit.rs.model.master.Praktek;
import com.aji.rumahsakit.rs.service.PraktekService;

@RestController
@Controller
public class PraktekController {
	
	@Autowired
	private PraktekService praktekService;
	
	@RequestMapping(value = "/praktek/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 Praktek praktek=praktekService.findByKode(kode);

			 return ResponseEntity.ok(praktek);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}

	@RequestMapping(value = "/praktek/namadokter/{nama}/namapoli/{poli}/jam/{jam}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveFilters(@PathVariable String nama,@PathVariable String poli,@PathVariable String jam) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 List<Praktek> praktek=praktekService.findByFilter(nama, poli, jam);

			 return ResponseEntity.ok(praktek);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}

	
	@RequestMapping(value = "/praktek", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve() throws ValidationException
	{
		 try 
		 {
			List<Praktek> praktekList=praktekService.findByAll();

			return ResponseEntity.ok(praktekList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/praktek", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Praktek praktek) throws ValidationException{
		try {
			praktekService.save(praktek);
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
	
	@RequestMapping(value =  "/praktek", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Praktek praktek) throws Exception
	{
		 try 
		 {
			 praktekService.update(praktek);
			MessageResponse mg = new MessageResponse("Update Success with id " + praktek.getId());
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
