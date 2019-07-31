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
import com.aji.rumahsakit.rs.model.master.BiayaPelayananTambahan;
import com.aji.rumahsakit.rs.service.BiayaPelayananTambahanService;

@RestController
@Controller
public class BiayaPelayananTambahanController {

	@Autowired
	private BiayaPelayananTambahanService bptService;
	
	@RequestMapping(value = "/biayapelayanantambahan/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 BiayaPelayananTambahan bpt=bptService.findByKode(kode);

			 return ResponseEntity.ok(bpt);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/biayapelayanantambahan/jenis/{jenis}/harga/{harga}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveFilter(@PathVariable String jenis,@PathVariable String harga) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 List<BiayaPelayananTambahan> bpt=bptService.findByFilter(jenis, Long.valueOf(harga));

			 return ResponseEntity.ok(bpt);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/biayapelayanantambahan", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			List<BiayaPelayananTambahan> bptList=bptService.findAll();

			return ResponseEntity.ok(bptList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/biayapelayanantambahan", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody BiayaPelayananTambahan bpt) throws ValidationException{
		try {
			bptService.save(bpt);
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
	
	@RequestMapping(value =  "/biayapelayanantambahan", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody BiayaPelayananTambahan bpt) throws Exception
	{
		 try 
		 {
			 bptService.update(bpt);
			 MessageResponse mg = new MessageResponse("Update Success with id " + bpt.getId());
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
