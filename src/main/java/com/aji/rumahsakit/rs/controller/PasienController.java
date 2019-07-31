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
import com.aji.rumahsakit.rs.model.master.Pasien;
import com.aji.rumahsakit.rs.service.PasienService;

@RestController
@Controller
public class PasienController {

	@Autowired
	private PasienService pasienService;
	
	@RequestMapping(value = "/pasien/nama/{nama}/alamat/{alamat}/tgl-lahir/{tglLahir}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String nama,@PathVariable String alamat,@PathVariable String tglLahir) throws ValidationException
	{
		 try 
		 {
	
			 System.out.println("try find");
			 List<Pasien> pasien=pasienService.findByFilter(nama, alamat, tglLahir);

			 return ResponseEntity.ok(pasien);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/pasien/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			Pasien pasien=pasienService.findByKode(kode);

			return ResponseEntity.ok(pasien);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/pasien", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			 List<Pasien> pasien=pasienService.findAll();

			return ResponseEntity.ok(pasien);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/pasien", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Pasien pasien) throws ValidationException{
		try {
			pasienService.save(pasien);
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
	
	@RequestMapping(value =  "/pasien", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Pasien pasien) throws Exception
	{
		 try 
		 {
			 pasienService.update(pasien);
			 MessageResponse mg = new MessageResponse("Update Success with id " + pasien.getId());
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
