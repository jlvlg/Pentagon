//package com.jlvlg.pentagon.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
//import com.jlvlg.pentagon.exceptions.PageNotFoundException;
//import com.jlvlg.pentagon.exceptions.UserNotFoundException;
//import com.jlvlg.pentagon.facade.Pentagon;
//import com.jlvlg.pentagon.models.Page;
//import com.jlvlg.pentagon.models.User;
//
//@RestController
//@RequestMapping("pages")
//public class ControllerPage {
//	@Autowired
//	private Pentagon pentagon;
//
//	@PostMapping
//	public ResponseEntity<Page> save(@RequestBody Page page) throws InvalidPageNameException {
//		return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.savePage(page));
//	}
//
//	@GetMapping("/search/{user}")
//	public ResponseEntity<Page> findByUser(@PathVariable (value = "user") User user) throws PageNotFoundException {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findPage(user));
//	}
//
//	@GetMapping("/search/{username}")
//	public ResponseEntity<Page> findByUser(@PathVariable (value = "username") String username) throws PageNotFoundException, UserNotFoundException {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findPage(username));
//	}
//
//	@DeleteMapping("/delete/{page}")
//	public void delete(@PathVariable (value = "page") Page page) throws PageNotFoundException {
//		pentagon.deletePage(page);
//	}
//}
